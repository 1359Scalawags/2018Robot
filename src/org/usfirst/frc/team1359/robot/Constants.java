package org.usfirst.frc.team1359.robot;

public class Constants {
	
	
	// TODO Verify that these are not reversed with no motor power
	public static final boolean pressed=true;
	public static final boolean notPressed=false;
	public static final boolean locked = true;
	public static final boolean unLocked = false;
	
    /***
     * US Digital E4P Quadrature Encoder
     * 100 to 360 cycles per revolution (CPR)
	 * 400 to 1440 pulses per revolution (PPR)
     */
	
	// TODO Verify calculations for feetPerPulse and fullDriveSpeed
	public static final double feetPerPulse = 0.0044;
	public static final double maxMotorSpeed = 0.95;
	public static final double pulsesPerFoot = 229.183118052;
	public static final double fullDriveSpeed = 8; //8.8634 reduced for PID
	public static final double autoDriveSpeed = 1; // not accurate value
		
	public static final double cubeArmSpeed = 25;
	public static final double shooterArmSpeed = 25;
	

	// conversion for potentiometer angles
	public static final double anglePerValue = 200; //this is a random guess
	public static final int samplesToAverage = 4;
}
