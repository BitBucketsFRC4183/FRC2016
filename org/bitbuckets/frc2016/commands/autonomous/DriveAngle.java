package org.bitbuckets.frc2016.commands.autonomous;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveAngle extends Command {
	private int distance;
	private double radius;
	private long timeInit;

	public DriveAngle(int dist, int rad) {
		distance = dist;
		radius = rad / Constants.Autonomous.DRIVE_ANGLE_OPERATOR;
	}

	@Override
	protected void initialize() {
		Robot.drivey.drive(Constants.Autonomous.DRIVE_VEL, radius);
	}

	@Override
	protected void execute() {
		Robot.drivey.drive(Constants.Autonomous.DRIVE_VEL, radius);
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - timeInit >= distance * Constants.Autonomous.DRIVE_VEL;
	}

	@Override
	protected void end() {
		Robot.drivey.drive(0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.drivey.drive(0, 0);
	}
}
