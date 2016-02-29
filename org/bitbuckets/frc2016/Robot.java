
package org.bitbuckets.frc2016;

import org.bitbuckets.frc2016.commands.startShoot;
import org.bitbuckets.frc2016.commands.stopShoot;
import org.bitbuckets.frc2016.commands.autonomous.DriveStraight;
import org.bitbuckets.frc2016.subsystems.Drivey;
import org.bitbuckets.frc2016.subsystems.Shooty;
import org.bitbuckets.frc2016.subsystems.Sucky;
import org.bitbuckets.frc2016.subsystems.Winchy;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
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
	
	//public static TeensyIMU teensyIMU = new TeensyIMU();
	
	public static PowerDistributionPanel pdp;
	
	public static OI oi;
	
	//Talons for arcade drive
	public CANTalon right1 = new CANTalon(RobotMap.rightMotor1);
	public CANTalon right2 = new CANTalon(RobotMap.rightMotor2);
	public CANTalon left1 = new CANTalon(RobotMap.leftMotor1);
	public CANTalon left2 = new CANTalon(RobotMap.leftMotor2);

	Command autonomousCommand;
	SendableChooser chooser;
	RobotDrive rDrive;
	
	public AnalogInput lightSensor = new AnalogInput(1);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("initializing robot");
		oi = new OI();
		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", new DriveStraight(20));
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		rDrive = new RobotDrive(left1, left2, right1, right2);
		pdp = new PowerDistributionPanel();
//		teensyIMU.init();
//		teensyIMU.startData();
		
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
		long timeInit = System.currentTimeMillis();
//		if (System.currentTimeMillis() - timeInit >= 4000) {
//			drivey.driveWRPM(0.6, 0.0);
//		}
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
		
		//oi.engageButt.whenPressed(new SwarmyLatch());
		//oi.disengageButt.whenPressed(new SwarmyUnlatch());
		
		oi.shootButt.whenPressed(new startShoot());
		oi.unShootButt.whenPressed(new stopShoot());
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		//Driving stuff
		//drivey.driveCheez(oi.driver.getAxis(AxisType.kY), oi.driver.getAxis(AxisType.kZ));
		rDrive.arcadeDrive(oi.driver.getAxis(AxisType.kY), oi.driver.getAxis(AxisType.kZ));
		
		//Operator stuff
		if(sucky.getCurrentCommand()==null){
			if(oi.operator.getAxis(AxisType.kY)>0.5){
				sucky.intakeIn();
			}else if(oi.operator.getAxis(AxisType.kY)<-0.5){
				sucky.intakeOut();
			}else{
				sucky.intakeOff();
			}
		}
		
		//Manual winch motor control
		winchy.setSpeed(oi.operator.getAxis(AxisType.kZ));
		
		if(winchy.getLowSwitch()){
			winchy.zeroEnc();
		}
		
		//SmartDashboard.putNumber("Winch motor 1 current", Robot.pdp.getCurrent(Constants.WINCH_POWER_ONE));
		//SmartDashboard.putNumber("Winch motor 2 current", Robot.pdp.getCurrent(Constants.WINCH_POWER_TWO));
		SmartDashboard.putNumber("Winch motor 1 current", winchy.getCurrent1());
		SmartDashboard.putNumber("Winch motor 2 current", winchy.getCurrent2());
		
		SmartDashboard.putNumber("Intake current", sucky.getCurrent());
		
		SmartDashboard.putNumber("Winch motor 1 enc", winchy.motor1Enc());
		
		SmartDashboard.putBoolean("Lower switch", winchy.getLowSwitch());
		
		SmartDashboard.putNumber("Ball sensor?", lightSensor.getVoltage());
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}

