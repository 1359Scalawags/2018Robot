/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1359.deprecated.DriveSystem;
import org.usfirst.frc.team1359.deprecated.ExampleCommand;
import org.usfirst.frc.team1359.deprecated.ExampleSubsystem;
import org.usfirst.frc.team1359.robot.commands.arm.CubeGrab;
import org.usfirst.frc.team1359.robot.commands.arm.MoveToMiddle;
import org.usfirst.frc.team1359.robot.commands.autonomous.AutonomousCommandDispatch;
import org.usfirst.frc.team1359.robot.commands.autonomous.DriveStraightDistance;
import org.usfirst.frc.team1359.robot.commands.autonomous.TestGroup;
import org.usfirst.frc.team1359.robot.commands.autonomous.TurnByAngle;
import org.usfirst.frc.team1359.robot.subsystems.Aesthetics;
import org.usfirst.frc.team1359.robot.subsystems.Camera;
import org.usfirst.frc.team1359.robot.subsystems.Climber;
import org.usfirst.frc.team1359.robot.subsystems.CubeLoader;
import org.usfirst.frc.team1359.robot.subsystems.CubeShooter;
import org.usfirst.frc.team1359.robot.subsystems.PIDDriveSystem;
import org.usfirst.frc.team1359.robot.subsystems.PneumaticsSystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
/*
 * Author: Destin Team: 1359 Scalawags Date: 1/10/18
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	//public static final DriveSystem kDriveSystem = new DriveSystem();
	//public static final PIDDriveSystem kPIDDriveSystem = new PIDDriveSystem();
	public static final PIDDriveSystem kPIDDriveSystem = new PIDDriveSystem();
	public static final Aesthetics kAesthetics = new Aesthetics();
	public static OI kOI;
	public static final Climber kClimber = new Climber();
	public static final CubeLoader kCubeLoader = new CubeLoader();
	public static final CubeShooter kCubeShooter = new CubeShooter();
	public static final PneumaticsSystem kPneumatics = new PneumaticsSystem();
	public static final Camera kcamera = new Camera();
	public static String AutonomousLeftOrRightPriority = "None";
	public static String AutonomousMiddlePriority = "None";
//	public static boolean AutonomousInput1;
//	public static boolean AutonomousInput2;
	Command m_autonomousCommand;
	//SendableChooser<Command> m_chooser = new SendableChooser<>();
	SendableChooser<String> m_priority = new SendableChooser<String>();
	SendableChooser<String> m_priorityMiddle = new SendableChooser<String>();
	SendableChooser<String> m_override = new SendableChooser<String>();

	DriverStation driverStation;

	public Robot() {
		super();
		this.setPeriod(.025);
	}

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		kOI = new OI();
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		//m_chooser.addObject("Turn", new AutonomousCommandDispatch());
		
		m_priority.addDefault("Switch", "Switch");
		m_priority.addObject("Scale", "Scale");
		
		m_priority.addObject("Cross Line Only", "Neither");
		
		m_priorityMiddle.addDefault("Yes", "Yes");
		m_priorityMiddle.addObject("No", "No");
		
		m_override.addDefault("Auto", Constants.autoModeAuto);
		m_override.addObject("Override Left", Constants.autoModeLeft);
		m_override.addObject("Override Middle", Constants.autoModeMiddle);
		m_override.addObject("Override Right", Constants.autoModeRight);
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Auto priority", m_priority);
		SmartDashboard.putData("Auto Middle", m_priorityMiddle);
		SmartDashboard.putData("Auton Override", m_override);
		

		System.out.println("====The 1359 Scalawags are ready to set sail!====");
		System.out.println("The 1359 Scalawags can win this match!  ");
		CameraServer.getInstance().startAutomaticCapture();
		driverStation = DriverStation.getInstance();
		//LiveWindow.add(new Talon(1));
		//LiveWindow.add(new Talon(2));
		
		Robot.kCubeShooter.setReadyToFire(true);
		
       
	}

	@Override
	public void robotPeriodic() {
		//SmartDashboard.putNumber("Oh No It's Match Time!", driverStation.getMatchTime());

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = m_chooser.getSelected();
		//m_autonomousCommand = new DriveStraightDistance(10);
		//m_autonomousCommand = new TurnByAngle(90);
		this.AutonomousLeftOrRightPriority = m_priority.getSelected();
		this.AutonomousMiddlePriority = m_priorityMiddle.getSelected();
		m_autonomousCommand = new AutonomousCommandDispatch(m_override.getSelected());
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Oh No It's Match Time!", driverStation.getMatchTime());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		SmartDashboard.putString("Shooter", "Ready to Fire");
		SmartDashboard.putBoolean("Climber Locked", true);
		kPIDDriveSystem.resetEncoders();
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */ 
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Oh No It's Match Time!", driverStation.getMatchTime());
	}

	/**
	 * This function is called once at the beginning of test mode.
	 */ 
	private Command test;
	
	@Override
	public void testInit() {
		Scheduler.getInstance().add(new TurnByAngle(90));
//		test = new TurnByAngle(90);
//		this.test.start();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
		
	}
}
