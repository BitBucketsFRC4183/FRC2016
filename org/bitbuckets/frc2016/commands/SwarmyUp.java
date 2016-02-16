package org.bitbuckets.frc2016.commands;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Lets the winch up.
 * 
 * @author James
 *
 */
public class SwarmyUp extends Command {

	/**
	 * Constructor. Tells the robot what subsystems it needs.
	 */
	public SwarmyUp() {
		requires(Robot.winchy);
	}

	/**
	 * Initializes the command. Starts running the winch up.
	 */
	@Override
	protected void initialize() {
		Robot.winchy.setServo(false);
		Robot.winchy.setSpeed(Constants.WINCH_SPEED);
	}

	/**
	 * Runs periodically while the command is running. Keeps the winch going
	 * up at the same speed.
	 */
	@Override
	protected void execute() {
		Robot.winchy.setSpeed(Constants.WINCH_SPEED);
	}

	/**
	 * Tells the robot when the command is finished so that it can move on with
	 * its life.
	 * 
	 * @return True when the command is finished and needs to end.
	 */
	@Override
	protected boolean isFinished() {
		return !Robot.oi.winchUpButt.get();
	}

	/**
	 * Called when <code>isFinished()</code> returns <code>True</code>. Stops
	 * the winch motor and latches the ratchet.
	 */
	@Override
	protected void end() {
		Robot.winchy.setSpeed(0);
		Robot.winchy.endMove();
	}

	/**
	 * Called when the command is interrupted for some reason. Stops
	 * the winch motor and latches the ratchet.
	 */
	@Override
	protected void interrupted() {
		Robot.winchy.setSpeed(0);
		Robot.winchy.endMove();
	}

}
