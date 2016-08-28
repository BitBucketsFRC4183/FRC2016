package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartShoot extends Command {
	private long timeInit;
	
    public StartShoot() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooty);
        requires(Robot.sucky);
        requires(Robot.shooty);
    }
                                                                                  
    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    	Robot.sucky.setIntake(1.0);
    	Robot.shooty.setBreak(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()>timeInit+Constants.SHOOT_DELAY_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooty.setBreak(false);
    	Robot.shooty.setMotor(Constants.SHOOTER_SPEED);
    	Robot.sucky.intakeOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.sucky.intakeOff();
    	Robot.shooty.setMotor(0);
    }
}
