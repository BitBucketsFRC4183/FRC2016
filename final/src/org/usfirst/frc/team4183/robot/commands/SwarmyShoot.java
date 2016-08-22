package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwarmyShoot extends Command {
	
	private long initTime;
	
    public SwarmyShoot() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.sucky);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime = System.currentTimeMillis();
    	Robot.sucky.setIntake(-1.0);;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()-initTime>=500;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.sucky.intakeOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.sucky.intakeOff();
    }
}
