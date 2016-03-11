package org.bitbuckets.frc2016.commands.autonomous;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveAuto extends Command {
	private double speed;
	private double turn;
	private double time;
	private long timeInit;

	public DriveAuto(double speed, double turn, double time) {
		this.speed = speed;
		this.turn = turn;
		this.time = time;
	}

	@Override
	protected void initialize() {
		timeInit = System.currentTimeMillis();
		Robot.drivey.arcadeDrive(speed, turn);
	}

	@Override
	protected void execute() {
		Robot.drivey.arcadeDrive(speed, turn);
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - timeInit >= time;
	}

	@Override
	protected void end() {
		Robot.drivey.arcadeDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.drivey.arcadeDrive(0, 0);
	}
}
