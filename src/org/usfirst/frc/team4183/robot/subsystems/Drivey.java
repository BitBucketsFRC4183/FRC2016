
package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivey extends Subsystem {
//	public int topRight = 1;
//	public int backRight = 2;
//	public int topLeft = 3;
//	public int backLeft = 4;
	
//	public Talon left = new Talon(1);
//	public Talon right = new Talon(2);
	
	public CANTalon left = new CANTalon(RobotMap.LEFTDRIVE);
	public CANTalon right = new CANTalon(RobotMap.RIGHTDRIVE);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveTank(double left, double right){

    		this.left.set(left);
    		this.right.set(right); 
    	
    }
    

}

