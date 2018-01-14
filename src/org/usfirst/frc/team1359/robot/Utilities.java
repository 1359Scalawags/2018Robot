package org.usfirst.frc.team1359.robot;

public class Utilities {
	public Utilities() {}
	
	public static double NormalizeAngle(double angle) {
		while(angle > 180) {
			angle = angle - 360;
		}
		while(angle < -180) {
			angle = angle + 360;
		}
		return angle;
	}
}
