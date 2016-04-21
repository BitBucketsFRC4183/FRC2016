package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.Robot;
import org.usfirst.frc.team4183.robot.PracticeRobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Winchy extends Subsystem {

	private CANTalon motor1 = new CANTalon(PracticeRobotMap.winchMotor1);
	private CANTalon motor2 = new CANTalon(PracticeRobotMap.winchMotor2);
	private PIDController pidControl;
	private double setPoint;
	private Servo lock = new Servo(PracticeRobotMap.winchServo);
	private DigitalInput lowLimit = new DigitalInput(PracticeRobotMap.lowLimit);
	//private DigitalInput highLimit = new DigitalInput(1);
	private DigitalInput servoSwitch = new DigitalInput(PracticeRobotMap.servoSwitch);
	//private boolean isSafe = false;
	
	public Winchy(){
		motor1.configMaxOutputVoltage(Constants.MAX_WINCH_VOLTAGE);
		motor2.configMaxOutputVoltage(Constants.MAX_WINCH_VOLTAGE);
		
		pidControl = new PIDController(Constants.WINCH_P, 
				Constants.WINCH_I, Constants.WINCH_D, motor1, motor1, Constants.WINCH_PID_PERIOD);
		
		pidControl.setOutputRange(-0.15, 0.4);
	}
	
	@Override
	protected void initDefaultCommand() {
		//setvBusMode();
	}
	
	/**
	 * 
	 * @param speed
	 */
	public void setSpeed(double speed) {
		setvBusMode();
		enableMotors();
	
		motor1.set(speed);
		motor2.set(speed);
		
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
	
	/**
	 * Sets motors to v Bus mode
	 */
	public void setvBusMode(){
		motor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		motor2.changeControlMode(CANTalon.TalonControlMode.Follower);
		motor2.set(motor1.getDeviceID());
	}
	
	/**
	 * 
	 * @param pos The angle to set the servo to
	 */
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
	
	/**
	 * 
	 * @return The current angle of the servo
	 */
	public double getServoAngle(){
		return lock.getAngle();
	}
	
	/**
	 * 
	 * @return Returns true if arm is zeroed
	 */
	public boolean getLowSwitch(){
		return !lowLimit.get();
	}
	
	/**
	 * 
	 * @return Returns true if latched
	 */
	public boolean getLatchSwitch(){
		return servoSwitch.get();
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
	
	public double getVoltage1(){
		return motor1.getOutputVoltage();
	}
	
	public boolean isEnabled(){
		return motor1.isEnabled();
	}
	
	public double getError(){
		return pidControl.getError();
	}
	
	public double getPIDOutput(){
		return pidControl.get();
	}
	
	public void setSetpoint(double setPoint){	
		if(setPoint>1000 && setPoint<500000){
			this.setPoint = setPoint;
			pidControl.setSetpoint(setPoint);
		}	
	}
	
	public double getSetpoint(){
		return setPoint;
	}
	
	public void setPID(double P, double I, double D, int setPoint){
//		motor1.changeControlMode(CANTalon.TalonControlMode.Position);
//		motor2.changeControlMode(CANTalon.TalonControlMode.Follower);
//		motor2.set(motor1.getDeviceID());
		pidControl.setPID(P, I, D);
		pidControl.setSetpoint(setPoint);
	}
	
	public void enablePID(){
		pidControl.enable();
		motor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		motor2.changeControlMode(CANTalon.TalonControlMode.Follower);
		motor2.set(motor1.getDeviceID());
	}
	
	public void enableMotors(){
		motor1.enable();
		motor2.enable();
	}
	
	public void disablePID(){
		pidControl.disable();
		setvBusMode();
	}
	
	public void disableMotors(){
		motor1.disable();
		motor2.disable();
	}
	
}
