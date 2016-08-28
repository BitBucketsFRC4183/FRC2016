package org.usfirst.frc.team4183.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class PracticeRobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	public static int leftMotor1 = 2;
	public static int leftMotor2 = 1;
	public static int rightMotor1 = 9;
	public static int rightMotor2 = 10;

	public static int rollMotor = 6;
	
	public static int portMotor = 5;
	
	public static int shootMotor1 = 8;
	public static int shootMotor2 = 7;
	
	public static int winchMotor1 = 3;
	public static int winchMotor2 = 4;
	public static int winchServo = 3;
	
	public static int climbMotor1 = 22;
	public static int climbServo = 4;
	
	public static int lowLimit = 0;
	public static int servoSwitch = 1;
	public static int laserCannon = 4;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
