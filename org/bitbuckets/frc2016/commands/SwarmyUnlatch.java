package org.bitbuckets.frc2016.commands;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Unlatches the ratchet on the winch so that it can be let up.
 * 
 * @author James
 *
 */
public class SwarmyUnlatch extends Command {
	/** The time when the command initializes. **/
	private long timeInit = 0;

	/**
	 * Constructor. Tells the robot what subsystems it needs.
	 */
	public SwarmyUnlatch() {
		requires(Robot.winchy);
	}

	/**
	 * Initializes the command. Starts running the winch down and sets timeInit.
	 */
	@Override
	protected void initialize() {
		Robot.winchy.setSpeed(-1 * Constants.WINCH_SPEED);
		timeInit = System.currentTimeMillis();
	}

	/**
	 * Runs periodically while the command is running. Keeps the winch going
	 * down at the same speed.
	 */
	@Override
	protected void execute() {
		Robot.winchy.setSpeed(-1 * Constants.WINCH_SPEED);
	}

	/**
	 * Tells the robot when the command is finished so that it can move on with
	 * its life.
	 * 
	 * @return True when the command is finished and needs to end.
	 */
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - timeInit) >= Constants.WINCH_UNLAT_TIME;
	}

	/**
	 * Called when <code>isFinished()</code> returns <code>True</code>. Stops
	 * the winch motor and unlatches the ratchet.
	 */
	@Override
	protected void end() {
		Robot.winchy.startMove(true);
		Robot.winchy.setSpeed(0);
	}

	/**
	 * Called when the command is interrupted for some reason, and it just stops
	 * the winch motor.
	 */
	@Override
	protected void interrupted() {
		Robot.winchy.setSpeed(0);
	}

}
