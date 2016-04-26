package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.PracticeRobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climby extends Subsystem {
    
	private CANTalon climbMotor1 = new CANTalon(PracticeRobotMap.climbMotor1);
	private Servo climbServo = new Servo(PracticeRobotMap.climbServo);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void engageServo(boolean engaged){
    	if(engaged){
    		climbServo.set(180.0);
    	}else{
    		climbServo.set(0);
    	}
    }
    
    public void setSpeed(double speed){
    	climbMotor1.set(speed);
    }
}

