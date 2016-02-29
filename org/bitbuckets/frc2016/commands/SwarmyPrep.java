package org.bitbuckets.frc2016.commands;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwarmyPrep extends Command {
	
	public long timeInit;
	
    public SwarmyPrep() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.winchy);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    	Robot.winchy.setSpeed(-Constants.WINCH_PREP_SPEED);
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()-timeInit>Constants.WINCH_UNLAT_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winchy.endMove();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winchy.endMove();
    }
}
