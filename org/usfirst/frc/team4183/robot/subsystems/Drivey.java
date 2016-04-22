
package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.PIDCamera;
import org.usfirst.frc.team4183.robot.PIDGyro;
import org.usfirst.frc.team4183.robot.PIDTrackingOut;
import org.usfirst.frc.team4183.robot.PIDYaw;
import org.usfirst.frc.team4183.robot.PracticeRobotMap;
import org.usfirst.frc.team4183.robot.PracticeRobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivey extends Subsystem {
	private CANTalon right1;
	private CANTalon right2;
	private CANTalon left1;
	private CANTalon left2;
	
	RobotDrive robotDrive;
	
	PIDYaw pidYaw;
	PIDTrackingOut pidTrackingOut;
	PIDGyro pidGyro;
	PIDCamera pidCamera;
	public PIDController yawController;
	public PIDController camController;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Drivey() {
		right1 = new CANTalon(PracticeRobotMap.rightMotor1);
		right2 = new CANTalon(PracticeRobotMap.rightMotor2);
		left1 = new CANTalon(PracticeRobotMap.leftMotor1);
		left2 = new CANTalon(PracticeRobotMap.leftMotor2);
		
		robotDrive = new RobotDrive(left1,left2,right1,right2);
		pidGyro = new PIDGyro();
		pidCamera = new PIDCamera();
		pidYaw = new PIDYaw();
		pidTrackingOut = new PIDTrackingOut();
		
		yawController = new PIDController(0,0,0,pidGyro, pidYaw);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void setGyroPID(double P, double I, double D, double setpoint){
		yawController.setPID(P, I, D);
		yawController.setSetpoint(setpoint);
	}
	
	public void setVisionPID(double P, double I, double D, double setpoint){
		camController = new PIDController(P, I, D, pidCamera, pidTrackingOut);
		camController.setOutputRange(-0.67, 0.67);
		camController.setSetpoint(setpoint);
	}
	
	public void enableGyroPID(){
		yawController.enable();
	}
	
	public void disableGyroPID(){
		yawController.disable();
	}
	
	public void resetGyro(){
		pidGyro.resetGyro();
	}
	
	public void enableTrackingPID(){
		camController.enable();
	}
	
	
	
	public void disableTrackingPID(){
		camController.disable();
	}
	
	/**
	 * Drives the robot at a speed in MMS with a specified turn radius in MM.
	 * 
	 * @param speed
	 *            The tangential speed in MMS
	 * @param radius
	 *            The turn radius in MM
	 */
	public void driveMMS(int speed, int radius) {
		if(radius == 0){
			driveWRPM(speed * Constants.DRIVE_VEL_MMS_TO_WRPM, 0);
		}else{
			driveWRPM(speed * Constants.DRIVE_VEL_MMS_TO_WRPM, (speed / radius) * 60);
		}
	}

	/**
	 * Drives the motor at a speed to get the desired wheel RPM.
	 * 
	 * Equation: left speed = omega (speed/omega - wheel width/2), right speed =
	 * omega (speed/omega + wheel width/2)
	 * 
	 * 
	 * @param d
	 *            The tangential velocity of the robot in RPM. Forward is
	 *            positive.
	 * @param omega
	 *            The angular velocity of the robot in RAD per MIN. 0 for
	 *            straight. Anticlockwise is positive.
	 */
	public void driveWRPM(double d, double omega) {
		d = (int) (d * Constants.WRPM_TO_RPM);
		if (omega == 0) {
			right1.set(d);
			left1.set(d);
		} else {
			left1.set(omega * (d / omega - Constants.WHEEL_WIDTH_REV / 2));
			right1.set(omega * (d / omega + Constants.WHEEL_WIDTH_REV / 2));
		}
	}

	public void arcadeDrive(double speed, double turn) {
		robotDrive.arcadeDrive(speed, turn);
	}
	
	public void toggleBrake(boolean brake){
		left1.enableBrakeMode(brake);
		right1.enableBrakeMode(brake);
		left2.enableBrakeMode(brake);
		right2.enableBrakeMode(brake);
	}
	
}
