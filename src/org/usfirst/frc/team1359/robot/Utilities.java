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
	
	public static double Clamp(double value, double min, double max) {  // clamp values of PID
		return Math.max(min, Math.min(max, value));
	}
	
	//public static double normalizeDistance(double distance) {
		
		
		//return distance;
	//}
}
