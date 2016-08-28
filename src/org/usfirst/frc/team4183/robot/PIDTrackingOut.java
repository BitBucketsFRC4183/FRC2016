package org.usfirst.frc.team4183.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDTrackingOut implements PIDOutput {

	private double output;
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.output = output;
	}
	
	public double getOutput(){
		return output;
	}

}
