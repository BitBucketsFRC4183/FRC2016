package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winchy extends Subsystem {

	private CANTalon motor1;
	private CANTalon motor2;
	private Servo lock;

	@Override
	protected void initDefaultCommand() {
		motor1 = new CANTalon(RobotMap.winchMotor1);
		motor2 = new CANTalon(RobotMap.winchMotor2);
		lock = new Servo(RobotMap.winchServo);

		motor2.changeControlMode(TalonControlMode.Follower);
		motor2.set(RobotMap.winchMotor1);
	}

	public void setSpeed(double speed) {
		motor1.set(speed);
	}

	public void startMove(boolean up) {
		if (up) {
			lock.setAngle(Constants.WINCH_SERVO_UNLOCK_ANGLE);
		} else{
			
		}
	}
	
	public void endMove(){
		lock.setAngle(Constants.WINCH_SERVO_LOCK_ANGLE);
	}

}
