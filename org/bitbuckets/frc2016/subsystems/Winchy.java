package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Winchy extends Subsystem {

	private CANTalon motor1 = new CANTalon(RobotMap.winchMotor1);
	private CANTalon motor2 = new CANTalon(RobotMap.winchMotor2);
	private Servo lock = new Servo(RobotMap.winchServo);
	private DigitalInput lowLimit = new DigitalInput(0);
	private DigitalInput highLimit = new DigitalInput(1);
	//private boolean isSafe = false;
	
	public Winchy(){
		motor1.configMaxOutputVoltage(Constants.MAX_WINCH_VOLTAGE);
		motor2.configMaxOutputVoltage(Constants.MAX_WINCH_VOLTAGE);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

	public void setSpeed(double speed) {
//		if(Robot.oi.toggleSafeMode.get()){
//			isSafe = true;
//		}else if(Robot.oi.unToggleSafeMode.get()){
//			isSafe = false;
//		}
//		if(motor1.getOutputCurrent() <= Constants.WINCH_MAX_CURRENT && !isSafe){
//			motor1.set(speed);
//		} else {
//			isSafe = true;
//			motor1.set(speed * Constants.SAFE_MODE);
//		}
		
		if(speed<0){
			motor1.set(0.75*speed);
			motor2.set(0.75*speed);
		}
		
		if(speed>0){
			motor1.set(0.50*speed);
			motor2.set(0.50*speed);
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
	
	public void setLock(double pos){
		lock.setAngle(pos);
	}
	
	/**
	 * 
	 * @return The motor's encoder position
	 */
	public int motor1Enc(){
		return motor1.getEncPosition();
	}
	
	public boolean getLowSwitch(){
		return !lowLimit.get();
	}
	
	/**
	 * Sets the current encoder position to 0
	 */
	public void zeroEnc(){
		motor1.setPosition(0);
	}
	
	public double getCurrent1(){
		return motor1.getOutputCurrent();
	}
	
	public double getCurrent2(){
		return motor2.getOutputCurrent();
	}
	
	public void setPID(double P, double I, double D, int setPoint){
		motor1.changeControlMode(TalonControlMode.Position);
		motor2.changeControlMode(TalonControlMode.Follower);
		motor2.set(RobotMap.winchMotor1);
		motor1.setP(P);
		motor1.setI(I);
		motor1.setD(D);
		
		motor1.setSetpoint(setPoint);
		motor1.setAllowableClosedLoopErr((int)Constants.WINCH_ACCEPTANCE_RANGE);
	}
	
	public void enablePID(){
		motor1.enable();
	}
	
	public void endMove(){
		motor1.disable();
		//setSpeed(0);
		setServo(true);
	}

}
