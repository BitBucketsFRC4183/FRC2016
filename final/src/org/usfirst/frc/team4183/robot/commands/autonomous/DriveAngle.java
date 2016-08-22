package org.usfirst.frc.team4183.robot.commands.autonomous;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveAngle extends Command {
	private int distance;
	private int radius;
	private long timeInit;

	public DriveAngle(int dist, int rad) {
		distance = dist;
		radius = rad;
	}

	@Override
	protected void initialize() {
		timeInit = System.currentTimeMillis();
		Robot.drivey.driveMMS(Constants.Autonomous.DRIVE_VEL, radius);
	}

	@Override
	protected void execute() {
		Robot.drivey.driveMMS(Constants.Autonomous.DRIVE_VEL, radius);
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - timeInit >= distance / Constants.Autonomous.DRIVE_VEL;
	}

	@Override
	protected void end() {
		Robot.drivey.driveMMS(0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.drivey.driveMMS(0, 0);
	}
}
