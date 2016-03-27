package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwarmyOI extends Command {

    public SwarmyOI() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.winchy);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    			Robot.winchy.setSpeed(-(Constants.WINCH_SPEED*Robot.oi.operator.getAxis(AxisType.kZ))+0.08);
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
