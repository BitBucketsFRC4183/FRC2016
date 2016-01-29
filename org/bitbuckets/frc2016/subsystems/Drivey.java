
package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
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
        //setDefaultCommand(new MySpecialCommand());
    }

    public void drive(double speed,double angle){
    	
    	if(angle==0){
    		right.set(speed);
    		left.set(speed);
    	} else if(angle==45) {
    		left.set((angle*kAngle)+speed);
    	}
    }
}

