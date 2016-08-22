package org.usfirst.frc.team4183.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDCamera implements PIDSource {

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@SuppressWarnings("deprecation")
	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 180-Robot.cameraTable.getNumber("goalx");
	}

}
