
package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivey extends Subsystem {
	private CANTalon right = new CANTalon(RobotMap.rightMotor);
	private CANTalon left = new CANTalon(RobotMap.leftMotor);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private double kAngle = 0.0005;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void driveM(double speed, double radius) {
		drive(speed * Constants.DRIVE_VEL_TO_POWER, radius * Constants.DRIVE_RAD_TO_POWER);
	}

	public void drive(double speed, double radius) {
		if (radius == 0) {
			right.set(speed);
			left.set(speed);
		} else if (radius == 45) {
			left.set((radius * kAngle) + speed);
		}
	}
}
