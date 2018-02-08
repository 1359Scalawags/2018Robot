/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

import org.usfirst.frc.team1359.robot.commands.ClimbCommand;
import org.usfirst.frc.team1359.robot.commands.PrepareToLaunchShooter;
import org.usfirst.frc.team1359.robot.commands.ExtendClimberArm;
import org.usfirst.frc.team1359.robot.commands.PullShooter;
import org.usfirst.frc.team1359.robot.commands.ReleaseShooter;
import org.usfirst.frc.team1359.robot.commands.RetractClimberArm;
import org.usfirst.frc.team1359.robot.commands.TurnByAngle;

import edu.wpi.first.wpilibj.Joystick;
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
	Joystick stick1 = new Joystick(RobotMap.joystick1);
	Joystick stick2 = new Joystick(RobotMap.joystick2);
	Joystick stick3 = new Joystick(RobotMap.joystick3);
	
	
	// Button button = new JoystickButton(stick, buttonNumber);
	Button lightButton = new JoystickButton(stick2, RobotMap.lightbutton);
	Button turnButton = new JoystickButton(stick1, RobotMap.turnbutton);
	Button extendClimberButton = new JoystickButton(stick3, RobotMap.extendbutton);
	Button retractClimberButton = new JoystickButton(stick3, RobotMap.retractbutton);
	Button enableClimberButton = new JoystickButton(stick3, RobotMap.climberbutton);
	Button grabCubeButton = new JoystickButton(stick3, RobotMap.grabcube);
	Button releaseCubeButton = new JoystickButton(stick3, RobotMap.releasecube);
	Button liftCube90Button = new JoystickButton(stick3, RobotMap.liftcube90);
	Button liftCube180Button = new JoystickButton(stick3, RobotMap.liftcube180);
	Button lowerCubeButton = new JoystickButton(stick3, RobotMap.lowercube);
	Button drawShooter = new JoystickButton(stick3, RobotMap.drawShooter);
	Button releaseShooter = new JoystickButton(stick3, RobotMap.releaseShooter);
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
		turnButton.whenPressed(new TurnByAngle(90));
		SmartDashboard.putData("Turn by 90", new TurnByAngle(90));
		
		
		
		enableClimberButton.whenPressed(new ClimbCommand());
		
		extendClimberButton.whenPressed(new ExtendClimberArm());
		retractClimberButton.whenPressed(new RetractClimberArm());
		drawShooter.whenPressed(new PrepareToLaunchShooter());
		releaseShooter.whenPressed(new ReleaseShooter());
	}
	public Joystick getJoystick1() {
		return stick1;
	}
	public Joystick getJoystick2() {
		return stick2;
	}
	public Joystick getJoystick3() {
		return stick3;
	}
	public double getLeftSpeed() {
   	SmartDashboard.putNumber("Left Motor", -stick1.getY());		
		return stick1.getY();
	}
	public double getRightSpeed() {
    	SmartDashboard.putNumber("Right Motor", -stick2.getY());		
		return stick2.getY();
	}
}
