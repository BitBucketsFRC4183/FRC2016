package org.usfirst.frc.team4183.robot;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class TeensyIMU {
	private SerialPort serialPort;
	//public NetworkTable imuData;	
	private final int IMUMESSAGELEN = 39;
	
	public double yaw = 0.0;
	private double yawRate = 0.0;
	private double prevYaw;
	private double prevTime;
	
	private double biasBuffer[] = new double[100];
	private int biasBufferIdx = 0;
	private boolean calcBias = false;
	private int bufferLen = 0;
	
	PrintWriter pw;

	private double calYawRateBias = 0.0;
	private double accumulatedYawError = 0.0;
	
	// Cal bias is a small running average of the 
	public void setcalYawRateBias(double biasValue) {
		calYawRateBias = biasValue;
	}
	
	public double getYawAngle() {
		return yaw;
	}
	
	public void enableBiasSampling(boolean enable) {
		calcBias = enable;
		
		if(calcBias) {
			bufferLen = 0;
		} else {
			calYawRateBias = 0;
			for(int i = 0; i < bufferLen; i++) {
				calYawRateBias += biasBuffer[i];
			}
			calYawRateBias /= bufferLen;
		}
	}
	
	public void incrementBuffer(double val) {
		biasBuffer[biasBufferIdx] = val;
		
		biasBufferIdx++;
		bufferLen = bufferLen >= biasBuffer.length ? biasBuffer.length : bufferLen++;
		
		if(biasBufferIdx >= biasBuffer.length) {
			biasBufferIdx = 0;
		}
	}
	
	public TeensyIMU(){
		System.out.println("Starting teeeeeeeeeeensy");
		serialPort = new SerialPort(SerialPortList.getPortNames()[0]);
		//System.out.println(serialPort.getPortName());
		try {
			pw = new PrintWriter("imutest-"+System.currentTimeMillis()+".txt", "UTF-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			//Open serial port
			serialPort.openPort();
			//Set params.
		    serialPort.setParams(serialPort.BAUDRATE_115200, serialPort.DATABITS_8, 
		        						serialPort.STOPBITS_1, serialPort.PARITY_NONE);
		}catch (SerialPortException ex) {
		        System.out.println(ex);
		}
		//Thread for reading in serial data
		new Thread(new Runnable() {
			@Override
			public void run() {
				//Used as a bottleneck for raw data
				String inBuffer = "";
				//Raw data
				String rawIn = "";
				long timePrev = System.currentTimeMillis();
				long timeCurrent = System.currentTimeMillis();
				while(true){
					
					try {
						if((rawIn=serialPort.readString())!=null){
							//System.out.println(rawIn);
							inBuffer+=rawIn;
							for(String line : inBuffer.split("\n")){
								//New line counted as character
								line=line.substring(0, line.length()-1);
								if(line.length()==IMUMESSAGELEN){
									String[]poseData = line.split(",");
									if(poseData.length>=5){
										timeCurrent = System.currentTimeMillis();

										long imutime = hexToLong(poseData[0]);
																		
										double timeDelta = (imutime - prevTime)/1000000.0;
										
										// Be absolutely certain the accumulated error is NOT
										// used while bias calibration is in progress, this also
										// resets the accumulated error if we re-enter the calibration
										// state... not the best toggle, but good enought for now
										if ( ! calcBias)
										{
											accumulatedYawError += calYawRateBias * timeDelta;
										}
										else
										{
											accumulatedYawError = 0.0;
										}
										yaw = hexToDouble(poseData[4])*(180.0/Math.PI); //- accumulatedYawError;
										yawRate = (yaw - prevYaw)/timeDelta;
										
										if(calcBias) 
										{
											incrementBuffer(yawRate);
										}
										
										Robot.IMUTable.putNumber("IMU time", imutime);
										Robot.IMUTable.putNumber("Yaw", yaw);
										Robot.IMUTable.putNumber("Pitch", hexToDouble(poseData[3])*(180.0/Math.PI));
										Robot.IMUTable.putNumber("Roll", hexToDouble(poseData[2])*(180.0/Math.PI));
										Robot.IMUTable.putNumber("Update rate", (double)(timeCurrent-timePrev));
										
										SmartDashboard.putNumber("IMU time", imutime);
										SmartDashboard.putNumber("IMU rate", timeDelta);
										SmartDashboard.putNumber("IMU yaw rate", yawRate);
										
										//System.out.println((double)(timeCurrent-timePrev));
										System.out.println("IMU Time: \t" + imutime + "\tYaw: \t" + yaw);
										
										pw.println("Time: " + imutime + "\tYaw Rate:" + yawRate);
										
										timePrev = timeCurrent;
										prevTime = imutime;
										prevYaw = yaw;
									}
//									for(String datum:line.split(",")){
//										//First number is yaw angle
//										float yaw = hexToDouble(datum);
//										
//										//Puts yaw in public network table
//										Robot.IMUTable.putNumber("Yaw", yaw*(180.0/Math.PI));
//									}
									//Resets buffer
									inBuffer="";
								}else {
									inBuffer="";
								}
							}
						}		
					} catch (SerialPortException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Thread timeout
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
			}
		}).start();		 
	}
	private float hexToDouble(String str){		
		//Parses string as decimal
	    Long i = Long.parseLong(str, 16);
	    //Converts newly created decimals to floating point    
	    return Float.intBitsToFloat(i.intValue());
	}
		
	private long hexToLong(String str){
		return Long.parseLong(str,16);		
	}

}
