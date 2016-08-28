package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.CompRobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooty extends Subsystem {
    private CANTalon motor1 = new CANTalon(CompRobotMap.shootMotor1);
    private CANTalon motor2 = new CANTalon(CompRobotMap.shootMotor2);
    
    private DigitalOutput laserCannon = new DigitalOutput(CompRobotMap.laserCannon);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Shooty(){
    	laserCannon.set(false);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setMotor(double speed){
    	motor1.set(-speed);
    	motor2.set(speed);
    }
    
    public void setBreak(boolean enable){
    	motor1.enableBrakeMode(enable);
    	motor2.enableBrakeMode(enable);
    }
    
    public void shootPhotons(boolean enable){
    	laserCannon.set(enable);
    }
}

