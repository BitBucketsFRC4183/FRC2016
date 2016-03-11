package org.bitbuckets.frc2016.commands.autonomous;

import org.bitbuckets.frc2016.commands.PrepUnlatch;
import org.bitbuckets.frc2016.commands.SwarmyDelay;
import org.bitbuckets.frc2016.commands.SwarmyDown;
import org.bitbuckets.frc2016.commands.SwarmyLatch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class zeroArm extends CommandGroup {
    
    public  zeroArm(long delay) {
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
