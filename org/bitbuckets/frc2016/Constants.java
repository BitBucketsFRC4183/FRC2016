package org.bitbuckets.frc2016;

/**
 * The format for the conversion constants is <code>[INPUT]_TO_[OUTPUT]</code>. When you multiply something with the <code>[INPUT]</code> units by the constant, you get a number in the <code>[OUTPUT]</code> units.
 * units are as follows:
 * MMS	= millimetres per second
 * IN	= inches
 * MM	= millimetres
 * FT	= feet
 * REV	= revolutions of the encoder shaft
 * RPM	= REV per minute
 * WRPM	= wheel RPM
 * K	= constant
 * 
 * @author James Wyeth
 *
 */
public class Constants {
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////UNIT CONVERTERS///////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** Inch to mm converter so that low level programming can be done in mm**/
	public static final double IN_TO_MM = 25.4;
	/** Inch to foot converter **/
	public static final int FT_TO_IN = 12;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////DIMENSIONS/////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** The wheel circumference in inches (in/rev) **/
	public static final double WHEEL_CIRCUMFERENCE_IN = 10 * Math.PI;
	/** The wheel circumference in mm (mm/rev) **/
	public static final int WHEEL_CIRCUMFERENCE_MM = (int) Math.round(WHEEL_CIRCUMFERENCE_IN * IN_TO_MM);
	/** The number of teeth on the driving sprocket from the gearbox. **/
	public static final int DRIVE_SPROCKET_TEETH = 12;
	/** The number of teeth on the sprockets that are attached to the wheels. **/
	public static final int WHEEL_SPROCKET_TEETH = 36;
	/** The conversion from the wheel RPM to the drive RPM**/
	public static final double WRPM_TO_RPM = DRIVE_SPROCKET_TEETH/WHEEL_SPROCKET_TEETH;
	/** Maximum RPM for the wheels **/
	public static final int MAX_WRPM = 275;
	/** Maximum RPM for the motors **/
	public static final int MAX_RPM = (int)(MAX_WRPM*WRPM_TO_RPM);
	
	/** The width between the centre of the left centre wheel and the right centre wheel in IN. **/
	public static final double WHEEL_WIDTH_IN = 18.5;
	/** The width betweeen the centre of the left centre wheel and the right centre wheel in REV. **/
	public static final double WHEEL_WIDTH_REV = WHEEL_WIDTH_IN * WRPM_TO_RPM / WHEEL_CIRCUMFERENCE_IN;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static final double INTAKE_SPEED = 0.5;
	/** Multiply with a velocity in mm/s to get RPM of the wheels **/
	public static final int DRIVE_VEL_MMS_TO_WRPM = (1/WHEEL_CIRCUMFERENCE_MM) * 60;
	
	/** arbitrary value atm **/
	public static final double DRIVE_RAD_TO_WRPM = 0.25;
	/** The counts per revolution of the encoders on the drive motor **/
	public static final int DRIVE_ENC_CPR = 1000;

	public static final double DRIVE_KANGLE = 1;

	public class Autonomous {
		/** The driving velocity in MMS **/
		public static final int DRIVE_VEL = 25;

		public static final double DRIVE_ANGLE_COEFFICIENT = 180;
	}

}
