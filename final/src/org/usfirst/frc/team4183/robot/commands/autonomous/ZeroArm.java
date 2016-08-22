package org.usfirst.frc.team4183.robot.commands.autonomous;

import org.usfirst.frc.team4183.robot.commands.PrepUnlatch;
import org.usfirst.frc.team4183.robot.commands.SwarmyDelay;
import org.usfirst.frc.team4183.robot.commands.SwarmyDown;
import org.usfirst.frc.team4183.robot.commands.SwarmyLatch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ZeroArm extends CommandGroup {
    
    public  ZeroArm(long delay) {
        // Add Commands here:
        addSequential(new PrepUnlatch());
        addSequential(new SwarmyDown());
        addSequential(new SwarmyDelay(delay));
        addSequential(new SwarmyLatch());
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
