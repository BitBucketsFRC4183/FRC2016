package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;
import org.usfirst.frc.team4183.robot.CompRobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sucky extends Subsystem {
	private CANTalon roll = new CANTalon(CompRobotMap.rollMotor);
	private CANTalon port = new CANTalon(CompRobotMap.portMotor);
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
				roll.set(-Constants.INTAKE_SPEED);
			}
		} else{
			roll.set(0);
			
			currentTimeout = System.currentTimeMillis()+Constants.INTAKE_TIMEOUT_TIME;
		}
	}

	public void intakeOut() {
		roll.set(Constants.INTAKE_SPEED);
	}
		
	
	
	public void setIntake(double speed){
		roll.set(speed);
	}
	
	public void enablePort(double speed){
		port.set(speed);
	}
	
	public double getCurrent(){
		return roll.getOutputCurrent();
	}

	public void intakeOff() {
		roll.set(0);
	}

}
