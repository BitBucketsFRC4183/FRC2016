package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExceedSetpoint extends Command {

	private double setpoint;
	private long timeInit;
	
    public ExceedSetpoint(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.winchy);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winchy.setVBusModeBoth();
    	Robot.winchy.enableMotors();
    	timeInit = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.winchy.motor1Enc()<setpoint){
    		Robot.winchy.setSpeed(0.3);
    	}	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Robot.winchy.motor1Enc()>=setpoint) || (System.currentTimeMillis()-timeInit>=1000));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winchy.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winchy.setSpeed(0);
    }
}
