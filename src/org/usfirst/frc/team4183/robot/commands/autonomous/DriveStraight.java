package org.usfirst.frc.team4183.robot.commands.autonomous;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	private int distance;
	private long timeInit;
	private double time;
	private double speed;

	public DriveStraight(double speed, double time) {
		requires(Robot.drivey);
		this.speed = speed;
		this.time = time;
	}

	@Override
	protected void initialize() {
		timeInit = System.currentTimeMillis();
		Robot.drivey.resetGyro();
		Robot.drivey.enableGyroPID();
	}

	@Override
	protected void execute() {
		Robot.drivey.arcadeDrive(speed, Robot.drivey.yawController.get());
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - timeInit >= time;
	}

	@Override
	protected void end() {
		Robot.drivey.disableGyroPID();
		Robot.drivey.arcadeDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.drivey.disableGyroPID();
	}
}
