package org.bitbuckets.frc2016.commands;

import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwarmyUnlatch extends Command {

    public SwarmyUnlatch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.winchy);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winchy.setServo(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
