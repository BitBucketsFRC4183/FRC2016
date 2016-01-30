
package org.bitbuckets.frc2016.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.bitbuckets.frc2016.Robot;

/**
 *
 */
public class SwarmyPewPew extends Command {

    public SwarmyPewPew() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(double input) {
    	Robot.sucky.twist.set(input);
    	
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

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}
}
