package org.bitbuckets.frc2016;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

//import java.nio.ByteBuffer;

//import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.SPI.Port;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class TeensyIMU {
	
	//private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
	//private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
	private SerialPort serialPort;
	public NetworkTable imuData;
	private final int IMUMESSAGELEN = 39;
	
	public TeensyIMU(){
		serialPort = new SerialPort(SerialPortList.getPortNames()[0]);
		imuData = NetworkTable.getTable("IMU Data 2");
		NetworkTable.globalDeleteAll();

	    try {
	    	System.out.println("Opening serial port...");
	        serialPort.openPort();//Open serial portx
	        
	        System.out.println("Setting serial port params");
	        serialPort.setParams(serialPort.BAUDRATE_115200, serialPort.DATABITS_8, 
	        						serialPort.STOPBITS_1, serialPort.PARITY_NONE);//Set params.
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
	
	/**
	 * Sends Teensy a command to start sending data
	 */
	public void init(){
		System.out.println("Initiating pose data transfer");
        setBucketData(true);
        System.out.println("Setting verbose to false");
        setVerbose(false);
	}
	
	/**
	 * Sends Teensy a command to turn off the magnetometer
	 */
	public void magnoOff(){
		try {
			serialPort.writeByte((byte)'m');
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Starts thread for reading in Teensy data
	 */
	public void startData(){
		//Thread for reading in serial data
		new Thread(new Runnable() {
			@Override
			public void run() {
				String inBuffer = "";
				String rawIn = "";	
				
				while(true){
					try {
						if((rawIn=serialPort.readString())!=null){
							inBuffer+=rawIn;
							//New data separated by new lines
							for(String line : inBuffer.split("\n")){
								//New line counted as character
								line=line.substring(0, line.length()-1);
								//Only parses data from a line if the line length matches how long data should be
								if(line.length()==IMUMESSAGELEN	){
									//Pose x, y, and z separated by commas in a line
									String[]data = line.split(",");
									imuData.putNumber("Time stamp", hexToLong(data[0]));
									imuData.putNumber("Fusion Status", hexToByte(data[1]));
									imuData.putNumber("Roll", hexToDouble(data[2]));
									imuData.putNumber("Pitch", hexToDouble(data[3]));
									imuData.putNumber("Yaw", hexToDouble(data[4]));
								}
								
								inBuffer="";
							}
						}
					} catch (SerialPortException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Delay in thread set to 100 ms
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//end of while loop
			}//end of run function
		}).start();//end of thread
	}//end of startData function
	
	/**
	 * 
	 * @param state 
	 */
	private void setBucketData(boolean state){
		if(state){
			try {
				serialPort.writeByte((byte)'B');
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param state
	 */
	private void setVerbose(boolean state){
		if(state){
			try {
				serialPort.writeByte((byte)'V');
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				serialPort.writeByte((byte)'v');
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Turns hex into double
	 * @param str A double represented as a hex string
	 * @return Returns the double in the hex
	 */
	private float hexToDouble(String str){
		
        //Parses string as decimal
        Long i = Long.parseLong(str, 16);
        //Converts newly created decimals to floating point
        
        return Float.intBitsToFloat(i.intValue());

    }
	
	/**
	 * Turns hex into a long
	 * @param str A long represented as a hex string
	 * @return Returns the long number represented by the original hex string
	 */
	private long hexToLong(String str){
		return Long.parseLong(str,16);
		
	}
	
	/**
	 * Turns hex to byte
	 * @param str
	 * @return Returns the first and only byte in the string
	 */
	private byte hexToByte(String str){
		byte[]bytes = str.getBytes();
		
		return bytes[0];	
	}
}