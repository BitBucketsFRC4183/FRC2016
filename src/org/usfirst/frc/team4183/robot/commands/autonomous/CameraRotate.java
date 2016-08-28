package org.usfirst.frc.team4183.robot.commands.autonomous;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;
import org.usfirst.frc.team4183.robot.commands.SwarmyShootyLol;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class CameraRotate extends Command {
	
	private long timeInit;
	private long timeOut;
	private boolean endFire;
	
    public CameraRotate(long timeOut, boolean endFire) {
    	this.timeOut = timeOut;
    	this.endFire = endFire;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
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
        return Robot.cameraTable.getBoolean("present", false) 
        		|| System.currentTimeMillis()-timeInit>=timeOut;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(System.currentTimeMillis()-timeInit<timeOut){
	    	double targetX = Robot.cameraTable.getNumber("goalx", 0)*Constants.Autonomous.HFOV/2;
	    	System.out.println("Target X " + targetX);
	    	if(endFire){
	    		Scheduler.getInstance().add(new SwarmyShootyLol());
	    	}else{
	    		Scheduler.getInstance().add(new RelativeRotate(targetX));
	    	}
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
