/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

import org.usfirst.frc.team1359.robot.commands.SwitchDriveDirection;
import org.usfirst.frc.team1359.robot.commands.arm.CubeGrab;
import org.usfirst.frc.team1359.robot.commands.arm.CubeRelease;
import org.usfirst.frc.team1359.robot.commands.autonomous.TurnByAngle;
import org.usfirst.frc.team1359.robot.commands.climber.ClimberStrap;
//import org.usfirst.frc.team1359.robot.commands.climber.ExtendClimberArm;
//import org.usfirst.frc.team1359.robot.commands.climber.RetractClimberArm;
import org.usfirst.frc.team1359.robot.commands.climber.RockClimberArmBackward;
import org.usfirst.frc.team1359.robot.commands.climber.RockClimberArmForward;
import org.usfirst.frc.team1359.robot.commands.climber.UnLockCLimber;
import org.usfirst.frc.team1359.robot.commands.drive.EnableDriveStraight;
import org.usfirst.frc.team1359.robot.commands.shooter.ForceLockShooter;
import org.usfirst.frc.team1359.robot.commands.shooter.ForceUnlockShooter;
//import org.usfirst.frc.team1359.robot.commands.shooter.LaunchShooter;
import org.usfirst.frc.team1359.robot.commands.shooter.LockShooter;
import org.usfirst.frc.team1359.robot.commands.shooter.PrepareToLaunchShooter;
import org.usfirst.frc.team1359.robot.commands.shooter.PullShooter;
import org.usfirst.frc.team1359.robot.commands.shooter.ReleaseShooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	// Joystick stick1 = new Joystick(RobotMap.joystick1);
	// Joystick stick2 = new Joystick(RobotMap.joystick2);
	// Joystick stick3 = new Joystick(RobotMap.joystick3);

	XboxController mainPad = new XboxController(RobotMap.mainController);
	XboxController assistPad = new XboxController(RobotMap.assistController);

	Button grabCubeButton = new JoystickButton(assistPad, RobotMap.xboxX);
	Button releaseCubeButton = new JoystickButton(assistPad, RobotMap.xboxB);
//	Button moveCubeMiddleButton = new JoystickButton(assistPad, RobotMap.xboxA);
//	Button moveCubeTopButton = new JoystickButton(assistPad, RobotMap.xboxY);
	Button drawShooter = new JoystickButton(assistPad, RobotMap.lBumber);
	Button releaseShooter = new JoystickButton(assistPad, RobotMap.rBumber);
	Button enableClimberButton = new JoystickButton(assistPad, RobotMap.backBtn);
	Button rockForwardButton = new JoystickButton(assistPad, RobotMap.xboxY);
	Button rockBackwardButton = new JoystickButton(assistPad, RobotMap.xboxA);
	
	Button reverseDriverButton = new JoystickButton(mainPad, RobotMap.startBtn);
	Button driveStraightEnable = new JoystickButton(mainPad, RobotMap.xboxX);
	//Button driveStraightDisable = new JoystickButton(mainPad, RobotMap.xboxB);
	Button extendClimberButton = new JoystickButton(mainPad, RobotMap.xboxY);
	Button retractClimberButton = new JoystickButton(mainPad, RobotMap.xboxA);
	//Button forceSullinoide = new JoystickButton(assistPad, RobotMap.xboxA);
	// Button button = new JoystickButton(stick, buttonNumber);
	// Button lightButton = new JoystickButton(stick2, RobotMap.lightbutton);
	// Button turnButton = new JoystickButton(stick1, RobotMap.turnbutton);
	// Button extendClimberButton = new JoystickButton(stick3,
	// RobotMap.extendbutton);
	// Button retractClimberButton = new JoystickButton(stick3,
	// RobotMap.retractbutton);
	// Button enableClimberButton = new JoystickButton(stick3,
	// RobotMap.climberbutton);
	// Button grabCubeButton = new JoystickButton(stick3, RobotMap.grabcube);
	// Button releaseCubeButton = new JoystickButton(stick3, RobotMap.releasecube);
	// Button liftCube90Button = new JoystickButton(stick3, RobotMap.liftcube90);
	// Button liftCube180Button = new JoystickButton(stick3, RobotMap.liftcube180);
	// Button lowerCubeButton = new JoystickButton(stick3, RobotMap.lowercube);
	// Button drawShooter = new JoystickButton(stick3, RobotMap.drawShooter);
	// Button releaseShooter = new JoystickButton(stick3, RobotMap.releaseShooter);
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
	public OI() {
		// turnButton.whenPressed(new TurnByAngle(90));
		//SmartDashboard.putData("Turn by 90", new TurnByAngle(90));

		// extendClimberButton.whenPressed(new ExtendClimberArm());
		// retractClimberButton.whenPressed(new RetractClimberArm());
		drawShooter.whenPressed(new PrepareToLaunchShooter());
		//releaseShooter.whenPressed(new ReleaseShooter());
		grabCubeButton.whenPressed(new CubeGrab());
		releaseCubeButton.whenPressed(new CubeRelease());
//		moveCubeMiddleButton.whenPressed(new CubeAtMiddle());
//		moveCubeTopButton.whenPressed(new CubeAtTop());
//		moveCubeBottomButton.whenPressed(new CubeAtBottom());
		drawShooter.whenPressed(new PullShooter());
		releaseShooter.whenPressed(new ReleaseShooter());
		enableClimberButton.whenPressed(new UnLockCLimber());
//		extendClimberButton.whenPressed(new ExtendClimberArm());
//		retractClimberButton.whenPressed(new RetractClimberArm());
		rockForwardButton.whenPressed(new RockClimberArmForward());
		rockBackwardButton.whenPressed(new RockClimberArmBackward());
		reverseDriverButton.whenPressed(new SwitchDriveDirection());
		
		driveStraightEnable.whenPressed(new EnableDriveStraight());
		//driveStraightEnable.whenReleased(command);
		
//		forceSullinoide.whenPressed(new ForceUnlockShooter());
//		forceSullinoide.whenReleased(new ForceLockShooter());
	}

	public double getMainTriggers() {
		return Math.max(mainPad.getTriggerAxis(Hand.kLeft), mainPad.getTriggerAxis(Hand.kRight));
	}
	public double getAssistTriggerLeft() { // move climber Strap
		return assistPad.getTriggerAxis(Hand.kLeft);
	}
	public double getAssistTriggerRight() {
		return  assistPad.getTriggerAxis(Hand.kRight);
	}
	
	public double getLStickY() {
		if (Math.abs(mainPad.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
			return -(mainPad.getY(Hand.kLeft) * (.3 * getMainTriggers() + .7));
		} else {
			return 0;
		}
	}

	public double getRStickY() {
		if (Math.abs(mainPad.getY(Hand.kRight)) > Constants.controllerDeadZone) {
			return -(mainPad.getY(Hand.kRight) * (.3 * getMainTriggers() + .7));
		} else {
			return 0;
		}
	}

	public double getArmStick() { // move climber Arm
		return assistPad.getY(Hand.kLeft);
	}
	
	public double getGrabberStick() {
		return assistPad.getY(Hand.kRight);
	}
	
	public boolean getDriveStraightButton() {
		return driveStraightEnable.get();
	}
	// public Joystick getJoystick1() {
	// return stick1;
	// }
	// public Joystick getJoystick2() {
	// return stick2;
	// }
	// public Joystick getJoystick3() {
	// return stick3;
	// }
	// public double getLeftSpeed() {
	// SmartDashboard.putNumber("Left Motor", -stick1.getY());
	// return stick1.getY();
	// }
	// public double getRightSpeed() {
	// SmartDashboard.putNumber("Right Motor", -stick2.getY());
	// return stick2.getY();
	// }
}
