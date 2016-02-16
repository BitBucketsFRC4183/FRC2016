package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooty extends Subsystem {
    private CANTalon shoot1 = new CANTalon(RobotMap.shootyMotor1);
    private CANTalon shoot2 = new CANTalon(RobotMap.shootyMotor2);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void shootOut() {
    	shoot1.set(Constants.SHOOTER1_SPEED);
    	shoot2.set(Constants.SHOOTER2_SPEED);
    }
    public void shooterOff() {
    	shoot1.set(0);
    	shoot2.set(0);
    }
}

