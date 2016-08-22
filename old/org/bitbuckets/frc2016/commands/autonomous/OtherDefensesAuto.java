package org.bitbuckets.frc2016.commands.autonomous;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.commands.SwarmyMoveToPos;
import org.bitbuckets.frc2016.commands.SwarmyUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OtherDefensesAuto extends CommandGroup {
    
    public  OtherDefensesAuto() {
    	addSequential(new zeroArm(400));
    	addSequential(new DriveAuto(0.75,0,4000));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
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
