package org.usfirst.frc.team4183.robot.commands;

import org.usfirst.frc.team4183.robot.Constants;
import org.usfirst.frc.team4183.robot.commands.autonomous.CameraRotate;
import org.usfirst.frc.team4183.robot.commands.autonomous.DriveAuto;
import org.usfirst.frc.team4183.robot.commands.autonomous.ZeroArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleVision extends CommandGroup {
    
    public  SimpleVision(){
        // Add Commands here:
    	addSequential(new ZeroArm(400));
    	addSequential(new SwarmyMoveToPos(Constants.WINCH_SHOOT_BATTER));
    	addSequential(new SwarmyMoveToPos(Constants.WINCH_SHOOT_OUTERWORKS));
    	addSequential(new CameraRotate(4000, false));
    	addSequential(new CameraRotate(4000, false));
    	addSequential(new CameraRotate(4000, false));
    	addSequential(new EnablePhoton(true));
    	addSequential(new DriveAuto(0, 0, 2000));
    	addSequential(new EnablePhoton(false));

        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
