package org.bitbuckets.frc2016.commands;

import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class goJuanAidy extends Command {

    public goJuanAidy() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	long initTime = System.currentTimeMillis();
    	while(System.currentTimeMillis()-initTime<=2000){
    		Robot.drivey.driveWRPM(1, 180.0);
    	}
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
