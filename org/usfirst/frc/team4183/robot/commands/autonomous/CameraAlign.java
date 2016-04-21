package org.usfirst.frc.team4183.robot.commands.autonomous;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraAlign extends Command {

    public CameraAlign() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivey.setVisionPID(0.04, 0.002, 0, 0);
    	Robot.drivey.camController.setAbsoluteTolerance(15.0);
    	Robot.drivey.enableTrackingPID();
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("deprecation")
	protected void execute() {
    	SmartDashboard.putNumber("cam control out", Robot.drivey.camController.get());
    	SmartDashboard.putNumber("Cam control error", Robot.drivey.camController.getError());
    	
    	if(!Robot.cameraTable.getBoolean("present")){
    		Robot.drivey.camController.disable();
    		Robot.drivey.arcadeDrive(0, 0);
    	}else{
    		Robot.drivey.camController.enable();
    	}
    	
    	Robot.drivey.arcadeDrive(0, Robot.drivey.camController.get());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivey.camController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivey.disableTrackingPID();
    	Robot.drivey.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivey.disableTrackingPID();
    	Robot.drivey.arcadeDrive(0, 0);
    }
}
