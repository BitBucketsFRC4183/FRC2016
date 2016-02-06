package org.bitbuckets.frc2016;

import java.util.HashMap;

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
	//public HashMap<String, Double> imuData;
	public NetworkTable imuData;
	
	private final int IMUMESSAGELEN = 26; 
	
	public TeensyIMU(){
		
		serialPort = new SerialPort(SerialPortList.getPortNames()[0]);
	    //imuData = new HashMap();
		imuData = NetworkTable.getTable("IMU Data");
		
		
	    try {
	        serialPort.openPort();//Open serial port
	        
	        serialPort.setParams(serialPort.BAUDRATE_115200, serialPort.DATABITS_8, 
	        						serialPort.STOPBITS_1, serialPort.PARITY_NONE);//Set params.
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	    //Thread for reading in serial data
		new Thread(new Runnable() {
			@Override
			public void run() {
				String inBuffer = "";
				String rawIn = "";
				// TODO Auto-generated method stub
				while(true){
						try {
							//System.out.println(serialPort.readString());
							if((rawIn=serialPort.readString())!=null){
								inBuffer+=rawIn;
								//System.out.println(inBuffer.split("\n"));
								for(String line : inBuffer.split("\n")){
									//New line counted as character (Really dumb)
									line=line.substring(0, line.length()-1);
									//System.out.println(line.length());
									//for(int i =0; i<line.length(); i++){
									//	//System.out.print((int)line.charAt(i) + " ");
										
									//}
									if(line.length()==IMUMESSAGELEN){
										//System.out.println(line);
										//imuData.putString("blah", line);
										
										for(String datum:line.split(",")){
											//System.out.println(datum);
											float f = hexToDouble(datum);
											//System.out.println(hexToDouble(Integer.toHexString(Float.floatToIntBits(1.0f))));
											imuData.putNumber("Value", f);
										}
										
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
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
			}
			}).start();
		 
		}
	
	
/**It's the port we deserve, but not the port we need right now
public SPI spi;
		spi = new SPI(port);
		spi.setClockActiveLow();
		spi.setChipSelectActiveLow();
		spi.read(initiate, dataReceived, size);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					System.out.println("Characters read: " + spi.read(true, readBuffer, 1024));
					System.out.println(spi.transaction(writeBuffer, readBuffer, 1024));
					while(readBuffer.hasRemaining()){
						System.out.println(readBuffer.getChar());
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
*/

	private float hexToDouble(String str){
		
        //Parses string as decimal
        Long i = Long.parseLong(str, 16);
        //Converts newly created decimals to floating point
        
        return Float.intBitsToFloat(i.intValue());

    }
	
	private long hexToLong(String str){
		return Long.parseLong(str,16);
		
	}
	
//	private byte hexToByte(String str){
//		byte[]bytes = str.getBytes();
//		
//		
//			
//		}
//	}
}
	
