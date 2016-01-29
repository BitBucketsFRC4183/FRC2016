package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sucky extends Subsystem {
	public CANTalon roll = new CANTalon(RobotMap.rollMotor);
	public CANTalon twist = new CANTalon(RobotMap.twistMotor);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
