package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraRotate extends Command {

	private boolean dir;
	private long timeOut;
	private long timeInit;
    public CameraRotate(boolean dir, long timeOut) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
        this.dir = dir;
        this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    	if(dir){
    		Robot.drivey.arcadeDrive(0, 0.7);
    	}else{
    		Robot.drivey.arcadeDrive(0, -0.7);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cameraTable.getBoolean("present")
        		|| System.currentTimeMillis()-timeInit>=timeOut;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivey.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivey.arcadeDrive(0, 0);
    }
}
