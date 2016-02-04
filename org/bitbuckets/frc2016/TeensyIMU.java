package org.bitbuckets.frc2016;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.ByteBuffer;

//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class TeensyIMU {
	
	private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
	private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
	private SerialPort serialPort;
	private String RAW;
	private String[]lines;
	
	public TeensyIMU(){
				
		serialPort = new SerialPort(SerialPortList.getPortNames()[0]);
	    
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
				// TODO Auto-generated method stub
				while(true){
						try {
							
							RAW = serialPort.readString();
							lines = RAW.split(System.getProperty("line.separator"));
							//System.out.println(serialPort.readHexString());
							//serialPort.closePort();
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
	
	public float getGyro(){
		
		if(!serialPort.isOpened())
		return 0;
		else{
			return hexToDouble(lines[0]);
		}
		
	}
	
	public float getAccel(){
		
		if(!serialPort.isOpened())
			return 0;
			else{
				return hexToDouble(lines[1]);
			}
	}
	
	public float getMag(){
		if(!serialPort.isOpened())
			return 0;
			else{
				return hexToDouble(lines[2]);
			}
	}
	
	public float getPose(){
		if(!serialPort.isOpened())
			return 0;
			else{
				return hexToDouble(lines[3]);
			}
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
        Float f = Float.intBitsToFloat(i.intValue());

        while(f<(float)4294967295.0) {
            return f;
        }
        //Prints original hex number
        //System.out.println(Integer.toHexString(Float.floatToIntBits(f)));
        //Integer.parseInt("099FA", 16);
        return 0;
    }	
}
	
