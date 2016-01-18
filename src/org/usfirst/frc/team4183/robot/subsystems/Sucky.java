package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sucky extends Subsystem {
    public CANTalon roll;
    public CANTalon tilt;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Sucky(){
    	roll = new CANTalon(RobotMap.ROLL);
    	tilt = new CANTalon(RobotMap.TILT);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double roll, double tilt){
    	this.roll.set(roll);
    	this.tilt.set(tilt);
    	
    }
}

