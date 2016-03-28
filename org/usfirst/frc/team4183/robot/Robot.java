
package org.usfirst.frc.team4183.robot;

import org.usfirst.frc.team4183.robot.commands.ClosePort;
import org.usfirst.frc.team4183.robot.commands.OpenPort;
import org.usfirst.frc.team4183.robot.commands.PrepUnlatch;
import org.usfirst.frc.team4183.robot.commands.SwarmyIntakeIn;
import org.usfirst.frc.team4183.robot.commands.SwarmyIntakeOff;
import org.usfirst.frc.team4183.robot.commands.SwarmyIntakeOut;
import org.usfirst.frc.team4183.robot.commands.SwarmyLatch;
import org.usfirst.frc.team4183.robot.commands.SwarmyMoveToPos;
import org.usfirst.frc.team4183.robot.commands.SwarmyShoot;
import org.usfirst.frc.team4183.robot.commands.SwarmyTeleop;
import org.usfirst.frc.team4183.robot.commands.ToggleBrakeMode;
import org.usfirst.frc.team4183.robot.commands.startShoot;
import org.usfirst.frc.team4183.robot.commands.stopShoot;
import org.usfirst.frc.team4183.robot.commands.autonomous.LowBarAuto;
import org.usfirst.frc.team4183.robot.commands.autonomous.OtherDefensesAuto;
import org.usfirst.frc.team4183.robot.commands.autonomous.zeroArm;
import org.usfirst.frc.team4183.robot.subsystems.Drivey;
import org.usfirst.frc.team4183.robot.subsystems.Shooty;
import org.usfirst.frc.team4183.robot.subsystems.Sucky;
import org.usfirst.frc.team4183.robot.subsystems.Winchy;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	
	//public static TeensyIMU teensyIMU;
	
	public static PowerDistributionPanel pdp;
	
	public static OI oi;
	
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
		System.out.println("initializing robot");
//		teensyIMU = new TeensyIMU();
		oi = new OI();
		chooser = new SendableChooser();
		chooser.addDefault("Low bar auto", new LowBarAuto());
		chooser.addObject("OtherDefensesAuto", new OtherDefensesAuto());
		SmartDashboard.putData("Auto mode", chooser);
		
//		right1 = new CANTalon(RobotMap.rightMotor1);
//		right2 = new CANTalon(RobotMap.rightMotor1);
//		left1 = new CANTalon(RobotMap.leftMotor1);
//		left2 = new CANTalon(RobotMap.leftMotor1);
//		
//		rDrive = new RobotDrive(left1, left2, right1, right2);
		pdp = new PowerDistributionPanel();
//		teensyIMU.init();
//		teensyIMU.startData();
		
		server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		
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
		
		//teensyIMU.magnoOff();
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		//teensyIMU.magnoOff();
		
		//oi.prepButt.whenPressed(new SwarmyPrep());
		
//		oi.engageButt.whenPressed(new SwarmyLatch());
		oi.disengageButt.whenPressed(new PrepUnlatch());
		oi.engageButt.whenPressed(new SwarmyTeleop());
		oi.engageButt.whenReleased(new SwarmyLatch());
		
		//oi.prepButt.whenPressed(new PrepUnlatch());
		oi.portButton.whenPressed(new OpenPort());
		oi.portButton.whenReleased(new ClosePort());
		
		oi.spoolButt.whenPressed(new startShoot());
		oi.unShootButt.whenPressed(new stopShoot());
		
		oi.shootButt.whenPressed(new SwarmyShoot());
		
		oi.breakButt.whenPressed(new ToggleBrakeMode(true));
		oi.coastButt.whenPressed(new ToggleBrakeMode(false));
		
		oi.intakeInButt.whenPressed(new SwarmyIntakeIn());
		oi.intakeOutButt.whenPressed(new SwarmyIntakeOut());
		oi.intakeInButt.whenReleased(new SwarmyIntakeOff());
		oi.intakeOutButt.whenReleased(new SwarmyIntakeOff());
		
		oi.winchShootButt.whenPressed(new SwarmyMoveToPos(Constants.WINCH_SHOOT_POS));
		oi.winchIntakeButt.whenPressed(new zeroArm(0));
		oi.winchLiftButt.whenPressed(new SwarmyMoveToPos(Constants.WINCH_LIFT_POS));
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		//Driving stuff
		drivey.arcadeDrive(oi.driver.getAxis(AxisType.kY) * (oi.slowMoButt.get() ? 0.5 : 1.0),
				Math.pow(oi.driver.getAxis(AxisType.kZ), 2)*Math.signum(oi.driver.getAxis(AxisType.kZ))*(oi.slowMoButt.get() ? 0.5 : 1.0));
		
		//Operator stuff
//		if(sucky.getCurrentCommand()==null){
//			if(oi.operator.getAxis(AxisType.kY)>0.5){
//				sucky.intakeIn();
//			}else if(oi.operator.getAxis(AxisType.kY)<-0.5){
//				sucky.intakeOut();
//			}else{
//				sucky.intakeOff();
//			}
//		}
		//Manual winch motor control
			//winchy.setSetpoint(winchy.getSetpoint()+300.0*oi.operator.getAxis(AxisType.kZ));
			//winchy.setSpeed(-oi.operator.getAxis(AxisType.kZ));	
		
		if(winchy.getLowSwitch()){
			winchy.zeroEnc();
		}
		
		SmartDashboard.putNumber("Winch motor 1 current", Robot.pdp.getCurrent(Constants.WINCH_POWER_ONE));
		SmartDashboard.putNumber("Winch motor 1 voltage", winchy.getVoltage1());
		SmartDashboard.putNumber("Intake current", sucky.getCurrent());
		
		SmartDashboard.putNumber("Winch motor 1 enc", winchy.motor1Enc());
		SmartDashboard.putBoolean("Lower switch", winchy.getLowSwitch());
		SmartDashboard.putBoolean("Latch switch", winchy.getLatchSwitch());
		
		if(winchy.getCurrentCommand()!=null)
		SmartDashboard.putString("Current Command", winchy.getCurrentCommand().toString());
		
		SmartDashboard.putNumber("Servo angle", winchy.getServoAngle());
		
		SmartDashboard.putBoolean("Motor control", winchy.isEnabled());
		SmartDashboard.putNumber("Winch joystick", oi.operator.getAxis(AxisType.kZ));
		SmartDashboard.putNumber("PID Error", winchy.getError());
		SmartDashboard.putNumber("PID Output", winchy.getPIDOutput());
		SmartDashboard.putNumber("PID Setpoint", winchy.getSetpoint());
		
		SmartDashboard.putNumber("KTwist", oi.operator.getAxis(AxisType.kTwist));
		SmartDashboard.putNumber("KThrottle", oi.operator.getAxis(AxisType.kThrottle));
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}

