package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwarmyDown extends Command {
	
	private long timeInit;
	
    public SwarmyDown() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.winchy);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    	Robot.winchy.disablePID();
    	Robot.winchy.setVBusModeBoth();
    	Robot.winchy.enableMotors();
    	Robot.winchy.setSpeed(0.4);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.winchy.getLowSwitch() || System.currentTimeMillis()-timeInit>=Constants.SWARMY_DOWN_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winchy.disableMotors();
    	Robot.winchy.zeroEnc();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
