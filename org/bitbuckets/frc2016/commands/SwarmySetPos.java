package org.bitbuckets.frc2016.commands;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwarmySetPos extends Command {
	int pos;
	
	public SwarmySetPos(int pos) {
		this.pos = pos;
		requires(Robot.winchy);
	}

	@Override
	protected void initialize() {
		//Robot.winchy.setSpeed(-1 * Constants.WINCH_SPEED);
		Robot.winchy.setPID(Constants.WINCH_P, Constants.WINCH_I, Constants.WINCH_D, pos);
	}

	@Override
	protected void execute() {
		//Robot.winchy.setSpeed(-1 * Constants.WINCH_SPEED);
		Robot.winchy.enablePID();
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(pos-Robot.winchy.motor1Enc())<Constants.WINCH_ACCEPTANCE_RANGE;
	}

	@Override
	protected void end() {
		Robot.winchy.endMove();
	}

	@Override
	protected void interrupted() {
		Robot.winchy.setSpeed(0);
	}

}
