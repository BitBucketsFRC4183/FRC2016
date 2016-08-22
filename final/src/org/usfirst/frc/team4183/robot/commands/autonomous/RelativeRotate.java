package org.usfirst.frc.team4183.robot.commands.autonomous;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RelativeRotate extends Command {
	
	public double heading;
	
    public RelativeRotate(double heading) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
        this.heading = heading;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivey.resetGyro();
    	Robot.drivey.setGyroPID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, heading);
    	Robot.drivey.yawController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(Robot.drivey.yawController.get());
    	Robot.drivey.arcadeDrive(0, Robot.drivey.yawController.get()+
    			Math.signum(Robot.drivey.yawController.get())*0.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.drivey.yawController.get()-heading)<=5.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivey.yawController.disable();
    	Robot.drivey.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivey.disableGyroPID();
    }
}
