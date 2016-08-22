package org.usfirst.frc.team4183.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickAxisButton extends Button {
	private Joystick joystick;
	private AxisType axis;
	private double sign;
	
	public JoystickAxisButton(Joystick joystick, AxisType axis, boolean positive) {
		this.joystick = joystick;
		this.axis = axis;
		sign = positive ? 1.0 : -1.0;
	}
	
	@Override
	public boolean get() {
		return joystick.getAxis(axis)*sign > 0.75;
	}

}
