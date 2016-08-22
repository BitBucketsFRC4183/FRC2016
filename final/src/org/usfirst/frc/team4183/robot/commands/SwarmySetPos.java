package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwarmySetPos extends Command {
	int pos;
	
	public SwarmySetPos(int pos) {
		this.pos = pos;
		requires(Robot.winchy);
	}

	@Override
	protected void initialize() {
//		if(!Robot.winchy.getLatchSwitch()){
			Robot.winchy.setPID(Constants.WINCH_P, Constants.WINCH_I, Constants.WINCH_D, pos);
			Robot.winchy.enableMotors();
			Robot.winchy.enablePID();
//		}
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
//		return Math.abs(pos-Robot.winchy.motor1Enc())<Constants.WINCH_ACCEPTANCE_RANGE 
//				|| Robot.winchy.getLatchSwitch();
		return Math.abs(pos-Robot.winchy.motor1Enc())<Constants.WINCH_ACCEPTANCE_RANGE;
	}

	@Override
	protected void end() {
		//Robot.winchy.disablePID();
		System.out.println("Finished");
	}

	@Override
	protected void interrupted() {
		//Robot.winchy.setSpeed(0);
	}

}
