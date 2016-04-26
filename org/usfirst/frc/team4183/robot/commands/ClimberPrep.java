package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberPrep extends Command {

    public ClimberPrep() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.climby);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climby.engageServo(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climby.setSpeed(0.6);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.liftButt.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climby.engageServo(false);
    	Robot.climby.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
