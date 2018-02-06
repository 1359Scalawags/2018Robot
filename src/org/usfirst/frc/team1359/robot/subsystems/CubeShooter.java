package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeShooter extends Subsystem {
	
	Talon shooterPull;
	Solenoid lockValve;
	DigitalInput shooterDownLimit;
	DigitalInput strapUnwound;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	shooterPull = new Talon(RobotMap.shooterPull);
    	lockValve = new Solenoid(RobotMap.shooterLock);
    	shooterDownLimit = new DigitalInput(RobotMap.shooterDownLimit);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void lockShooter() {
    	lockValve.set(true);
    }
    public void unlockShooter() {
    	lockValve.set(false);
    }
    public boolean isLocked() {
    	return lockValve.get();
    }
    public void pullShooter() {
    	shooterPull.set(Constants.shooterArmSpeed);
    }
    public void unwindShooter() {
    	shooterPull.set(-(Constants.shooterArmSpeed));
    }
    
     public boolean shooterIsDown() {
    	 if(shooterDownLimit.get() == Constants.pressed) {
    		 return true;
    	 }else {
    		 return false;
    	 }
     }
    
     public boolean shooterIsUnwound() {
    	 return (strapUnwound.get() == Constants.pressed);
    	 
     }
     
     public void stopShooterMotor() {
    	 shooterPull.set(0);
    	 
     }
     
    public void loadCube() {}
    public void shoot() {}
    public void reset() {}
}

