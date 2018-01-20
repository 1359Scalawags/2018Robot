/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

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
