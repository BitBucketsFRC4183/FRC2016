package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sucky extends Subsystem {
	private CANTalon roll = new CANTalon(RobotMap.rollMotor);
	private CANTalon lift = new CANTalon(RobotMap.liftMotor);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void intakeIn() {
		roll.set(Constants.INTAKE_SPEED);
	}

	public void intakeOut() {
		roll.set(-1 * Constants.INTAKE_SPEED);
	}

	public void intakeOff() {
		roll.set(0);
	}

	public void setLifterMotor(double speed) {
		lift.set(speed);
	}
}
