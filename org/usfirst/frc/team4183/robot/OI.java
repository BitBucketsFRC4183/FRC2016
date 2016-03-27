package org.usfirst.frc.team4183.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	public Joystick driver = new Joystick(0);
	public Joystick operator = new Joystick(1);

	public Button intakeOutButt = new JoystickAxisButton(operator, AxisType.kY, true);
	public Button intakeInButt = new JoystickAxisButton(operator, AxisType.kY, false);
	
	public Button spoolButt = new JoystickButton(operator,5);
	public Button unShootButt = new JoystickButton(operator,6);
	public Button shootButt = new JoystickButton(operator,2);
	
	public Button winchLiftButt = new JoystickButton(operator, 3);
	public Button winchShootButt = new JoystickButton(operator, 4);
	public Button winchIntakeButt = new JoystickButton(operator, 1);
	
	//public Button prepButt = new JoystickButton(operator, 1);
	//public Button zeroButt = new JoystickButton(operator, 14);
	public Button portButton = new JoystickButton(operator, 14);
	
	//public Button engageButt = new JoystickButton(operator, 9);
	public Button engageButt = new JoystickAxisButton(operator, AxisType.kThrottle, true);
	
	public Button disengageButt = new JoystickButton(operator, 10);
	
	public Button slowMoButt = new JoystickButton(driver, 6);
	public Button coastButt = new JoystickButton(driver, 3);
	public Button breakButt = new JoystickButton(driver, 2);
	
	//public Button winchPIDButt = new JoystickButton(operator,9);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
