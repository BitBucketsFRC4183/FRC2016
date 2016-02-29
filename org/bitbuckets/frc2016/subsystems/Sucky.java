package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sucky extends Subsystem {
	private CANTalon roll = new CANTalon(RobotMap.rollMotor);
	private long currentTimeout = 0;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void intakeIn() {
		
		if(getCurrent()<Constants.INTAKE_MAX_CURRENT){
			if(System.currentTimeMillis()>currentTimeout){
				roll.set(Constants.INTAKE_SPEED);
			}
		} else{
			roll.set(0);
			
			currentTimeout = System.currentTimeMillis()+Constants.INTAKE_TIMEOUT_TIME;
		}
	}

	public void intakeOut() {
		if(getCurrent()<Constants.INTAKE_MAX_CURRENT){
			roll.set(-1 * Constants.INTAKE_SPEED);
		}else{
			roll.set(0);
		}
	}
		
	
	
	public void setIntake(double speed){
		roll.set(speed);
	}
	
	public double getCurrent(){
		return roll.getOutputCurrent();
	}

	public void intakeOff() {
		roll.set(0);
	}

}
