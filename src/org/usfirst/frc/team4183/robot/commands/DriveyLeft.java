package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveyLeft extends Command {

	private long timeInit;
	private double currentSpeed;
	private double prevSpeed;
	private double targetSpeed;
	
	
	private double acc;
	
    public DriveyLeft(double speed, double acc) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
        targetSpeed = speed;
        this.acc = acc;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	prevSpeed = Robot.drivey.left.getSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timeInit = System.currentTimeMillis();
    	currentSpeed = Robot.drivey.left.getSpeed();
    	
    	if(targetSpeed>currentSpeed){
    		Robot.drivey.driveTank(0.0, 
    							currentSpeed+(acc*(System.currentTimeMillis()-timeInit)));
    		
    	}
    	
    	timeInit = System.currentTimeMillis();
    	if(targetSpeed<currentSpeed){
    		Robot.drivey.driveTank(0.0, 
								currentSpeed-(acc*(System.currentTimeMillis()-timeInit)));
    	}
    	prevSpeed = currentSpeed;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
