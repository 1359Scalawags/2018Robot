package org.usfirst.frc.team1359.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {

    private static boolean hasStarted = false;
    public void start() {
    	hasStarted = false;
    	if(hasStarted == false) {
    		CameraServer.getInstance().startAutomaticCapture();
    		hasStarted = true;
    	}
    }
    
    public boolean isStarted() {
    	return hasStarted;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

