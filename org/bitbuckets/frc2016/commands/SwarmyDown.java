package org.bitbuckets.frc2016.commands;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwarmyDown extends Command {

	public SwarmyDown() {
		requires(Robot.winchy);
	}

	@Override
	protected void initialize() {
		Robot.winchy.startMove(false);
		Robot.winchy.setSpeed(-1 * Constants.WINCH_SPEED);
	}

	@Override
	protected void execute() {
		Robot.winchy.setSpeed(-1 * Constants.WINCH_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return !Robot.oi.winchDownButt.get();
	}

	@Override
	protected void end() {
		Robot.winchy.setSpeed(0);
	}

	@Override
	protected void interrupted() {
		Robot.winchy.setSpeed(0);
	}

}
