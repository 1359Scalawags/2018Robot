package org.usfirst.frc.team1359.robot;

public class Constants {

	// TODO Verify that these are not reversed with no motor power
	public static final boolean pressed = false;
	public static final boolean notPressed = true;
	public static final boolean locked = false;
	public static final boolean unLocked = true;

	/***
	 * US Digital E4P Quadrature Encoder 100 to 360 cycles per revolution (CPR) 400
	 * to 1440 pulses per revolution (PPR)
	 */

	// TODO Verify calculations for feetPerPulse and fullDriveSpeed
	public static final double feetPerPulse = 0.0044;
	public static final double maxMotorSpeed = 0.95;
	public static final double pulsesPerFoot = 229.183118052;
	public static final double fullDriveSpeed = 8; // 8.8634 reduced for PID
	public static final double autoDriveSpeed = .8; // not accurate value
	public static final double autoDriveShortSpeed = .6;

	public static final double cubeArmSpeed = 0.75;
	public static final double shooterPullSpeed = 0.75;
	public static final double elevatorSpeed = 0.75;

	// conversion for potentiometer angles
	public static final double anglePerValue = 200; // this is a random guess
	public static final int samplesToAverage = 2;

	// gameController
	public static final double controllerDeadZone = .1;
	public static final double angleTolerance = 5.0;
	public static final double ROTATE_TOLERANCE = 0.5;
	
	public static final double drivePID_P = 1.0;
	public static final double drivePID_I = 0.0;
	public static final double drivePID_D = 0.0;
	
	public static final double gyroPID_P = 0.35; // was .45
	public static final double gyroPID_I = 0.05; // was 0
	public static final double gyroPID_D = 0.05;
	
	// autonomous
	public static final double distanceToSwitchCenterline = 12; //14 ft
	public static final double distanceToScaleCenterline = 20;
	public static final double distanceToFirstLine = 10;
	public static final double approachScaleEnd = 5;
	public static final double distanceToSwitchWall = 5; 
	public static final double approachSwitchEnd = 2.0;
	public static final double avoidSwitchDistanceShort = 3.0;
	public static final double avoidSwitchDistanceLong = 5.0;
	public static final double avoidCubesMiddle = 5;
	// MoveToMiddle autonomous
	public static final double moveToMiddleArmTime = 2.25;
	public static final double moveToMiddleArmSpeed = .50; // original value .5
	
	public static final String autoModeAuto = "Auto";
	public static final String autoModeLeft = "Left";
	public static final String autoModeMiddle = "Middle";
	public static final String autoModeRight = "Right";
	

}
