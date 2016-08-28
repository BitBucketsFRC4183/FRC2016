package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwarmyDelay extends Command {
	long timeInit;
	long delay = 0;
    public SwarmyDelay(long delay) {
    	this.delay = delay;
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.winchy);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()-timeInit>=delay;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
