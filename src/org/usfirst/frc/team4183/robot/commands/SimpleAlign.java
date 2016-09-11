package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SimpleAlign extends Command {

	private final double fastTurn = 0.70;
	private final double slowTurn = 0.65;
	
    public SimpleAlign() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = Robot.cameraTable.getNumber("goalx")-180;	// TODO: Deprecation warning requires that default return value be added
    	if(Robot.cameraTable.getBoolean("present")){			// TODO: Deprecation warning requires that default return value be added
    		if(x>60.0){
    			Robot.drivey.arcadeDrive(0, fastTurn);
    		}else if(x>0 && x<=60.0){
    			Robot.drivey.arcadeDrive(0, slowTurn);
    		}else if(x<-60.0){
    			Robot.drivey.arcadeDrive(0, -fastTurn);
    		}else if(x<0 && x>=-60.0){
    			Robot.drivey.arcadeDrive(0, -slowTurn);
    		}	
    	}else{
    		Robot.drivey.arcadeDrive(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Math.abs(Robot.cameraTable.getNumber("goalx")-180)<15){	// TODO: Deprecation warning requires that default return value be added
        	Robot.drivey.arcadeDrive(0, 0);
        	return true;
        }else{
        	return false;
        }
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
