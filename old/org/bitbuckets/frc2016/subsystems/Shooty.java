package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooty extends Subsystem {
    private CANTalon motor1 = new CANTalon(RobotMap.shootMotor1);
    private CANTalon motor2 = new CANTalon(RobotMap.shootMotor2);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setMotor(double speed){
    	motor1.set(-speed);
    	motor2.set(speed);
    }
}

