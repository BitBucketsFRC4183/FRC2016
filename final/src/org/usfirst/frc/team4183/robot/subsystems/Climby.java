package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.CompRobotMap;
import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.PracticeRobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climby extends Subsystem {
    
	private Talon climbMotor1 = new Talon(CompRobotMap.climbMotor1);
	private Servo climbServo = new Servo(CompRobotMap.climbServo);


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Climby(){
		engageServo(Constants.SERVO_CLOSE_POS);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void engageServo(double pos){
    		climbServo.set(pos);

    }
    
    public void setServo(double angle){
    	climbServo.set(angle);
    }
    
    public void setSpeed(double speed){
    	climbMotor1.set(speed);
    }
}

