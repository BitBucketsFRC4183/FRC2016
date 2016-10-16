
package org.usfirst.frc.team4183.robot;

import org.usfirst.frc.team4183.robot.commands.ClimberPrep2;
import org.usfirst.frc.team4183.robot.commands.ClosePort;
import org.usfirst.frc.team4183.robot.commands.EnablePhoton;
import org.usfirst.frc.team4183.robot.commands.EngageClimber;
import org.usfirst.frc.team4183.robot.commands.ExceedSetpoint;
import org.usfirst.frc.team4183.robot.commands.OpenPort;
import org.usfirst.frc.team4183.robot.commands.PrepUnlatch;
import org.usfirst.frc.team4183.robot.commands.ReverseShoot;
import org.usfirst.frc.team4183.robot.commands.SimpleVision;
import org.usfirst.frc.team4183.robot.commands.SwarmyIntakeIn;
import org.usfirst.frc.team4183.robot.commands.SwarmyIntakeOff;
import org.usfirst.frc.team4183.robot.commands.SwarmyIntakeOut;
import org.usfirst.frc.team4183.robot.commands.SwarmyLatch;
import org.usfirst.frc.team4183.robot.commands.SwarmyMoveToPos;
import org.usfirst.frc.team4183.robot.commands.SwarmyShoot;
import org.usfirst.frc.team4183.robot.commands.SwarmyShootyLol;
import org.usfirst.frc.team4183.robot.commands.SwarmyTeleop;
import org.usfirst.frc.team4183.robot.commands.ToggleBrakeMode;
import org.usfirst.frc.team4183.robot.commands.ToggleDefenseMode;
import org.usfirst.frc.team4183.robot.commands.StartShoot;
import org.usfirst.frc.team4183.robot.commands.StopShoot;
import org.usfirst.frc.team4183.robot.commands.autonomous.CameraRotate;
import org.usfirst.frc.team4183.robot.commands.autonomous.LowBarAuto;
import org.usfirst.frc.team4183.robot.commands.autonomous.OtherDefensesAuto;
import org.usfirst.frc.team4183.robot.commands.autonomous.OuterDefenseAutoAlign;
import org.usfirst.frc.team4183.robot.commands.autonomous.RelativeRotate;
import org.usfirst.frc.team4183.robot.commands.autonomous.RockWallAuto;
import org.usfirst.frc.team4183.robot.commands.autonomous.ZeroArm;
import org.usfirst.frc.team4183.robot.subsystems.Climby;
import org.usfirst.frc.team4183.robot.subsystems.Drivey;
import org.usfirst.frc.team4183.robot.subsystems.Shooty;
import org.usfirst.frc.team4183.robot.subsystems.Sucky;
import org.usfirst.frc.team4183.robot.subsystems.Winchy;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Drivey drivey = new Drivey();
	
	public static final Sucky sucky = new Sucky();
	public static final Winchy winchy = new Winchy();
	public static final Shooty shooty = new Shooty();
	public static final Climby climby = new Climby();
	
	public static TeensyIMU teensyIMU;
	
	public static PowerDistributionPanel pdp;
	
	public static OI oi;
	
	public static NetworkTable IMUTable; 
	public static NetworkTable cameraTable;
	
//	//Talons for arcade drive
//	public CANTalon right1;
//	public CANTalon right2;
//	public CANTalon left1;
//	public CANTalon left2;

	Command autonomousCommand;
	SendableChooser chooser;
//	RobotDrive rDrive;
	
	CameraServer server;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		teensyIMU = new TeensyIMU();
		System.out.println("initializing robot");
		IMUTable = NetworkTable.getTable("IMU Data");
		cameraTable = NetworkTable.getTable("BucketVision");
		pdp = new PowerDistributionPanel();
		oi = new OI();
		chooser = new SendableChooser();
		chooser.addDefault("Low bar auto", new LowBarAuto());
		chooser.addObject("OtherDefensesAuto", new OtherDefensesAuto());
		chooser.addObject("Rock wall auto", new RockWallAuto());
		chooser.addObject("Goal Align + Shoot", new OuterDefenseAutoAlign());
		chooser.addObject("Goal Align Test Only", new SimpleVision());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		Command disablePhoton = new EnablePhoton(false);
		disablePhoton.setRunWhenDisabled(true);
		Scheduler.getInstance().add(disablePhoton);
		teensyIMU.enableBiasSampling(true);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		teensyIMU.enableBiasSampling(false);
		
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		if(drivey.getCurrentCommand()!=null)
			SmartDashboard.putString("Current Command", drivey.getCurrentCommand().toString());
	}

	public void teleopInit() {
		teensyIMU.enableBiasSampling(false);
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	//	oi.disengageButt.whenPressed(new PrepUnlatch());
		oi.engageButt.whenPressed(new SwarmyTeleop());
		oi.engageButt.whenReleased(new SwarmyLatch());
		
		oi.portButton.whenPressed(new OpenPort());
		oi.portButton.whenReleased(new ClosePort());
		
		oi.liftServoButt1.whenPressed(new ClimberPrep2(Constants.SERVO_OPEN_POS));
		oi.liftServoButt1.whenReleased(new ClimberPrep2(Constants.SERVO_CLOSE_POS));
		
		oi.spoolButt.whenPressed(new StartShoot());
		oi.unShootButt.whenPressed(new StopShoot());
		oi.shootButt.whenPressed(new SwarmyShoot());
		oi.autoShootButt.whenPressed(new SwarmyShootyLol());
		
		oi.spinShoot.whenPressed(new ReverseShoot());
		oi.spinShoot.whenReleased(new StopShoot());
		
		oi.breakButt.whenPressed(new ToggleBrakeMode(true));
		
		
		oi.intakeInButt.whenPressed(new SwarmyIntakeIn());
		oi.intakeOutButt.whenPressed(new SwarmyIntakeOut());
		oi.intakeInButt.whenReleased(new SwarmyIntakeOff());
		oi.intakeOutButt.whenReleased(new SwarmyIntakeOff());
		
		oi.winchShootButt.whenPressed(new SwarmyMoveToPos(Constants.WINCH_SHOOT_BATTER));
		oi.winchZeroButt.whenPressed(new ZeroArm(0));
		oi.winchLiftButt.whenPressed(new SwarmyMoveToPos(Constants.WINCH_SHOOT_OUTERWORKS));
		oi.winchIntakeButt.whenPressed(new SwarmyMoveToPos(Constants.WINCH_INTAKE_POS));
		
		oi.liftMotorButt.whenPressed(new EngageClimber(1.0));
		oi.liftMotorButt.whenReleased(new EngageClimber(0));
		
		oi.defenseModeButt.whenPressed(new ToggleDefenseMode(true));
		oi.defenseModeButt.whenReleased(new ToggleDefenseMode(false));
		
		oi.togglePhoto.whenPressed(new EnablePhoton(true));
		oi.togglePhoto.whenReleased(new EnablePhoton(false));
		
		
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		//Driving stuff
		drivey.arcadeDrive(oi.driver.getAxis(AxisType.kY) * (oi.slowMoButt.get() ? 0.5 : 1.0),
				Math.pow(oi.driver.getAxis(AxisType.kZ), 2)*Math.signum(oi.driver.getAxis(AxisType.kZ))*(oi.slowMoButt.get() ? 0.5 : 1.0));
		
		if(winchy.getLowSwitch()){
			winchy.zeroEnc();
		}
		
//		drivey.setDefenseServo(oi.driver.getAxis(AxisType.kThrottle));
		
		SmartDashboard.putNumber("Winch motor 1 current", Robot.pdp.getCurrent(Constants.WINCH_POWER_ONE));
		SmartDashboard.putNumber("Winch motor 1 voltage", winchy.getVoltage1());
		SmartDashboard.putNumber("Intake current", sucky.getCurrent());
		
		SmartDashboard.putNumber("Winch motor 1 enc", winchy.motor1Enc());
		SmartDashboard.putBoolean("Lower switch", winchy.getLowSwitch());
		SmartDashboard.putBoolean("Latch switch", winchy.getLatchSwitch());
		
		if(winchy.getCurrentCommand()!=null)
		SmartDashboard.putString("Current Command", winchy.getCurrentCommand().toString());
		
		//SmartDashboard.putNumber("Servo angle", winchy.getServoAngle());
		
		SmartDashboard.putBoolean("Motor control", winchy.isEnabled());
		SmartDashboard.putNumber(" Winch joystick", oi.operator.getAxis(AxisType.kZ));
		SmartDashboard.putNumber("PID Error", winchy.getError());
		SmartDashboard.putNumber("PID Output", winchy.getPIDOutput());
		SmartDashboard.putNumber("PID Setpoint", winchy.getSetpoint());
		
		SmartDashboard.putNumber("KTwist", oi.operator.getAxis(AxisType.kTwist));
		SmartDashboard.putNumber("KThrottle", oi.operator.getAxis(AxisType.kThrottle));
		
		SmartDashboard.putNumber("Defense Servo", drivey.getDefenseServo());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}

