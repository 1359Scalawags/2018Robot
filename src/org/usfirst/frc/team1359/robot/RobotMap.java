/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	//Motors
	public static final int frontleftMotor = 0;
	public static final int rearLeftMotor = 1;
	public static final int frontRightMotor = 2;
	public static final int rearRightMotor = 3;
	
	public static final int climbMotor = 4;
	public static final int elevatorMotor = 0;
	public static final int lowerLimit = 0;
	public static final int upperLimit = 1;
	public static final int rocker = 0;
	//Control
	
	
	
	//JoyStick1
	public static final int joystick1 = 0;
	public static final int turnbutton = 3;

	//JoyStick2
	public static final int joystick2 = 1;
	public static final int lightbutton = 3;
	
	//JoyStick3
	public static final int joystick3 = 2;
	public static final int extendbutton = 6;
	public static final int retractbutton = 7;
	public static final int climberbutton = 12;
	
	//Extra
	public static final int leonardo = 0;
	public static final double ROTATE_TOLERANCE = 0.5;
	
	
	//Pneumatics
	public static final int compressor = 0;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
