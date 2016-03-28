
package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.PracticeRobotMap;
import org.usfirst.frc.team4183.robot.PracticeRobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
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
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Drivey() {
		right1 = new CANTalon(PracticeRobotMap.rightMotor1);
		right2 = new CANTalon(PracticeRobotMap.rightMotor2);
		left1 = new CANTalon(PracticeRobotMap.leftMotor1);
		left2 = new CANTalon(PracticeRobotMap.leftMotor2);
		
		robotDrive = new RobotDrive(left1,left2,right1,right2);
		//position = NetworkTable.getTable("Enc Values");
		
//		right1.changeControlMode(TalonControlMode.Speed);
//		left1.changeControlMode(TalonControlMode.Speed);
//		right1.configEncoderCodesPerRev(4 * Constants.DRIVE_ENC_CPR);
//		left1.configEncoderCodesPerRev(4 * Constants.DRIVE_ENC_CPR);

//		right2.changeControlMode(TalonControlMode.Follower);
//		left2.changeControlMode(TalonControlMode.Follower);
//		right2.set(RobotMap.rightMotor1);
//		left2.set(RobotMap.leftMotor1);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	/**
	 * 
	 * @param speed
	 * @param radius
	 */
//	public void driveCheez(double speed, double radius) {
//		if(radius==0){
//			driveWRPM((int) (speed * Constants.MAX_WRPM), (speed) * 60);
//		}
//		driveWRPM((int) (speed * Constants.MAX_WRPM), (speed / radius) * 60);
//	}
	
	public double skim(double v) {
		  // gain determines how much to skim off the top
		if (v > 1.0)
			return -((v - 1.0) * Constants.CHEZ_GAIN);
		else if (v < -1.0)
		    return -((v + 1.0) * Constants.CHEZ_GAIN);
		return 0;
		}
	
	public void driveCheez(double speed, double radius){
		double t_left = speed + radius;
		double t_right = speed - radius;

		double left = t_left + skim(t_right);
		double right = t_right + skim(t_left);
		
		if(speed==0){
			left=1;
			right=1;
		}
		
		left1.set(left);
		left2.set(left);
		right1.set(right);
		right2.set(right);
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
