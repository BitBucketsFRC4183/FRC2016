package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.Robot;
import org.bitbuckets.frc2016.RobotMap;

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

	private CANTalon motor1 = new CANTalon(RobotMap.winchMotor1);
	private CANTalon motor2 = new CANTalon(RobotMap.winchMotor2);
	private PIDController pidControl;
	private double setPoint;
	private Servo lock = new Servo(RobotMap.winchServo);
	private DigitalInput lowLimit = new DigitalInput(0);
	private DigitalInput highLimit = new DigitalInput(1);
	private DigitalInput servoSwitch = new DigitalInput(2);
	//private boolean isSafe = false;
	
	public Winchy(){
		motor1.configMaxOutputVoltage(Constants.MAX_WINCH_VOLTAGE);
		motor2.configMaxOutputVoltage(Constants.MAX_WINCH_VOLTAGE);
		
		motor2.changeControlMode(TalonControlMode.Follower);
		motor2.set(RobotMap.winchMotor1);
		
		pidControl = new PIDController(Constants.WINCH_P, 
				Constants.WINCH_I, Constants.WINCH_D, motor1, motor1, Constants.WINCH_PID_PERIOD);
		
		pidControl.setOutputRange(-0.2, 0.5);
	}
	
	@Override
	protected void initDefaultCommand() {
		//setvBusMode();
	}

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
	
	public void setvBusMode(){
		motor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		motor2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
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
	
	public double getServoAngle(){
		return lock.getAngle();
	}
	
	public boolean getLowSwitch(){
		return !lowLimit.get();
	}
	
	//True if winch is engaged
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
		pidControl.setPID(P, I, D);
		pidControl.setSetpoint(setPoint);
	}
	
	public void enablePID(){
		motor1.changeControlMode(CANTalon.TalonControlMode.Position);
		motor2.changeControlMode(CANTalon.TalonControlMode.Follower);
		motor2.set(motor1.getDeviceID());
		pidControl.enable();
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
	
	public class TalonEncoder implements PIDSource{
		private CANTalon talon;
		
		public TalonEncoder(CANTalon talon){
			this.talon = talon;
		}
		
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return talon.getEncPosition();
		}
		
	}
	
}
