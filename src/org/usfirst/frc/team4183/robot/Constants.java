package org.usfirst.frc.team4183.robot;

/**
 * The format for the conversion constants is <code>[INPUT]_TO_[OUTPUT]</code>.
 * When you multiply something with the <code>[INPUT]</code> units by the
 * constant, you get a number in the <code>[OUTPUT]</code> units. units are as
 * follows: MMS = millimetres per second IN = inches MM = millimetres FT = feet
 * REV = revolutions of the encoder shaft RPM = REV per minute WRPM = wheel RPM
 * K = constant
 * 
 * @author James Wyeth
 *
 */
public class Constants {
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////// UNIT
	///////////////////////////////////////////////////////////////////////////////////////////////////////////// CONVERTERS///////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** Inch to mm converter so that low level programming can be done in mm **/
	public static final double IN_TO_MM = 25.4;
	/** Inch to foot converter **/
	public static final int FT_TO_IN = 12;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// DIMENSIONS/////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** The wheel circumference in inches (in/rev) **/
	public static final double WHEEL_CIRCUMFERENCE_IN = 10 * Math.PI;
	/** The wheel circumference in mm (mm/rev) **/
	public static final int WHEEL_CIRCUMFERENCE_MM = (int) Math.round(WHEEL_CIRCUMFERENCE_IN * IN_TO_MM);
	/** The number of teeth on the driving sprocket from the gearbox. **/
	public static final int DRIVE_SPROCKET_TEETH = 12;
	/**
	 * The number of teeth on the sprockets that are attached to the wheels.
	 **/
	public static final int WHEEL_SPROCKET_TEETH = 36;
	/** The conversion from the wheel RPM to the drive RPM **/
	public static final double WRPM_TO_RPM = DRIVE_SPROCKET_TEETH / WHEEL_SPROCKET_TEETH;
	/** Maximum RPM for the wheels **/
	public static final int MAX_WRPM = 275;
	/** Maximum RPM for the motors **/
	public static final int MAX_RPM = (int) (MAX_WRPM * WRPM_TO_RPM);

	/**
	 * The width between the centre of the left centre wheel and the right
	 * centre wheel in IN.
	 **/
	public static final double WHEEL_WIDTH_IN = 18.5;
	/**
	 * The width betweeen the centre of the left centre wheel and the right
	 * centre wheel in REV.
	 **/
	public static final double WHEEL_WIDTH_REV = WHEEL_WIDTH_IN * WRPM_TO_RPM / WHEEL_CIRCUMFERENCE_IN;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static final double INTAKE_SPEED = 0.75;
	public static final double SHOOTER_SPEED = -1.0;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** Multiply with a velocity in mm/s to get RPM of the wheels **/
	public static final int DRIVE_VEL_MMS_TO_WRPM = (1 / WHEEL_CIRCUMFERENCE_MM) * 60;

	/** arbitrary value atm **/
	public static final double DRIVE_RAD_TO_WRPM = 0.25;
	/** The counts per revolution of the encoders on the drive motor **/
	public static final int DRIVE_ENC_CPR = 1000;

	public static final double DRIVE_KANGLE = 0.005;
	
	public static final double DESIRED_ANGLE = 0;
           
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** The angle to set the servo to to unlock the ratchet **/
	public static final int WINCH_SERVO_UNLOCK_ANGLE = 70;
	/** The angle to set the servo to to lock the ratchet **/
	public static final int WINCH_SERVO_LOCK_ANGLE = 140;
	
	public static final double SERVO_OPEN_POS = 0.25;
	public static final double SERVO_CLOSE_POS = 0.0;
	
	/** Shooting from batter setpoint **/
	//public static final int WINCH_SHOOT_BATTER = -18378;
	public static final int WINCH_SHOOT_BATTER = -20800;
	/** Shooting from outerworks setpoint**/
	public static final int WINCH_SHOOT_OUTERWORKS = -39400;
	/** Lift setpoint **/
	public static final int WINCH_LIFT_POS = -35000;
	/** Ball sepoint **/
	public static final int WINCH_INTAKE_POS = -1000;
	
	/** The speed to run the winch at **/
	public static final double WINCH_SPEED = 0.55;
	/**The speed to run the winch before unlatching**/
	public static final double WINCH_PREP_SPEED = 0.85;
	
	/** Time in ms to unlatch the winch ratchet **/
	public static final int WINCH_UNLAT_TIME = 50;
	/**Time in ms to get to the ball grabbing position**/
	public static final int SWARMY_DOWN_TIME = 2000;
	/**Time in ms to get to the up position**/
	public static final int SWARMY_UP_TIME = 300;
	/**Time in ms to wait after ball is detected on intake**/
	public static final int INTAKE_TIMEOUT_TIME = 1000;
	/**Time in ms for the ball to cease contact with the shooter**/
	public static final int SHOOT_DELAY_TIME = 150;
	
	/** Max current for winch motor **/
	public static final int WINCH_MAX_CURRENT = 50;
	/** Max current for intake motor **/
	public static final int INTAKE_MAX_CURRENT = 55;
	/** Safe mode constant **/
	public static final double SAFE_MODE = .5;
	
	/**Max voltage for winch motor**/
	public static final int MAX_WINCH_VOLTAGE = 2;
	
/////////////////////////////////////////////Winch PID Values///////////////// /////////////////////////////////////////////
	public static final double WINCH_P = 17.5/50000.0;
	//public static final double WINCH_I = 0.001/50000.0;
	public static final double WINCH_I = 0.1/50000.0;
	public static final double WINCH_D = 0.0/50000.0;
	public static final double WINCH_ACCEPTANCE_RANGE = 300;
	public static final double WINCH_PID_PERIOD = 0.01;
/////////////////////////////////////////////Drive PID Values//////////////////////////////////////////////
	public static final double DRIVE_P = 0.0150;
	public static final double DRIVE_I = 0.00045;
	public static final double DRIVE_D = 0;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////// POWER DISTRIBUTION BOARD CHANNELS/////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**Winch motor power channel one**/
	public static final int WINCH_POWER_ONE = 4;
	/**Winch motor power channel two**/
	public static final int WINCH_POWER_TWO = 1;
			
	public class Autonomous {
		/** The driving velocity in MMS **/
		public static final int DRIVE_VEL = 25;
		
		public static final int DRIVE_AUTO_TIME = 4000;
		
		public static final double HFOV = 145.0;
		
		public static final double DRIVE_ANGLE_COEFFICIENT = 0.005;
	}

}
