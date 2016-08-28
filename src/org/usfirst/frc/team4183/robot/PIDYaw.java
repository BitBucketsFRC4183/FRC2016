package org.usfirst.frc.team4183.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDYaw implements PIDOutput {
	
	private double yaw;
	
	public PIDYaw(){
		yaw = 0;
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		yaw = output;
	}
	
	public double getYaw(){
		return yaw;
	}

}
