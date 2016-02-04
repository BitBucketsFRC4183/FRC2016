package org.bitbuckets.frc2016;

import java.nio.ByteBuffer;

//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class TeensyIMU {
	
	public ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
	public ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);	
	
	public TeensyIMU(){
				
		final SerialPort serialPort = new SerialPort(SerialPortList.getPortNames()[0]);
	    
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
							//System.out.println(serialPort.readString());
							System.out.println(serialPort.readHexString());
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
}
	
