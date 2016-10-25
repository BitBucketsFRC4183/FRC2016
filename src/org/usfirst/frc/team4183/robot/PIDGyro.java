package org.usfirst.frc.team4183.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDGyro implements PIDSource {

	private double zeroPoint = 0;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@SuppressWarnings("deprecation")
	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		//System.out.println("PID Gyro" + Robot.networkTable.getNumber("Yaw"));
		double corrected = correctYaw(Robot.teensyIMU.getYawAngle());
		
		//Robot.IMUTable.putNumber("Yaw Corrected", corrected); //IMUTable.getNumber("Yaw")));
		return corrected;
	}
	
	@SuppressWarnings("deprecation")
	public void resetGyro(){
		zeroPoint = Robot.IMUTable.getNumber("Yaw");
	}
	
	public double correctYaw(double yaw){
		return (yaw+540-zeroPoint)%360-180;
	}
	
}
