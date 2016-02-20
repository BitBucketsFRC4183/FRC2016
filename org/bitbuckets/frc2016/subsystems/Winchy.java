package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Winchy extends Subsystem {

	private CANTalon motor1;
	private CANTalon motor2;
	private Servo lock;
	private boolean isSafe = false;
	
	@Override
	protected void initDefaultCommand() {
		motor1 = new CANTalon(RobotMap.winchMotor1);
		motor2 = new CANTalon(RobotMap.winchMotor2);
		lock = new Servo(RobotMap.winchServo);
	}

	public void setSpeed(double speed) {
		if(Robot.oi.toggleSafeMode.get()){
			isSafe = true;
		}else if(Robot.oi.unToggleSafeMode.get()){
			isSafe = false;
		}
		if(motor1.getOutputCurrent() <= Constants.WINCH_MAX_CURRENT && !isSafe){
			motor1.set(speed);
		} else {
			isSafe = true;
			motor1.set(speed * Constants.SAFE_MODE);
		}
	}
	
	/**
	 * Sets servo to lock or unlock angle 
	 * @param engaged If servo needs to be locked or unlocked
	 */
	public void setServo(boolean engaged) {
		if (engaged) {
			lock.setAngle(Constants.WINCH_SERVO_LOCK_ANGLE);
		} else{
			lock.setAngle(Constants.WINCH_SERVO_UNLOCK_ANGLE);
		}
	}
	
	public int motor1Enc(){
		return motor1.getEncPosition();
	}
	
	public void setPID(double P, double I, double D, int setPoint ){
		motor1.changeControlMode(TalonControlMode.Position);
		motor1.configEncoderCodesPerRev(Constants.DRIVE_ENC_CPR);
		motor2.changeControlMode(TalonControlMode.Follower);
		motor2.set(RobotMap.winchMotor1);
		motor1.setP(P);
		motor1.setI(I);
		motor1.setD(D);
		
		motor1.setSetpoint(setPoint);
		//motor1.setFeedbackDevice(CANTalon.);
		SmartDashboard.putNumber("Pos", motor1.getEncPosition());
	}
	
	public void enablePID(){
		motor1.enable();
	}
	
	public void endMove(){
		setServo(true);
	}

}
