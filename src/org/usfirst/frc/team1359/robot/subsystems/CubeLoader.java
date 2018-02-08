package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class CubeLoader extends Subsystem {
	
	DigitalInput bottomLimit, topLimit;
	Talon liftMotor;
	// Relay cubeClamp; 
	Solenoid armValve;
	
	 Potentiometer pot = new AnalogPotentiometer(0, 180, 0);
	 
	public enum ArmPosition {
		Top,
		Middle,
		Bottom
	}
	
	ArmPosition armposition;
	
	public CubeLoader(){
		
		bottomLimit = new DigitalInput(RobotMap.bottomLimit);
		topLimit = new DigitalInput(RobotMap.topLimit);
		liftMotor = new Talon(RobotMap.liftMotor);
		// cubeClamp = new Relay(RobotMap.cubeClamp);
		armValve = new Solenoid(RobotMap.armValve);
		
	}
	
	   public double getPosition() {
			return pot.get();
		}
		
	public void grab() {
		armValve.set(true);
		
	}
	
	public void release() {
		armValve.set(false);
	}
	
	public void lift() {
		
		liftMotor.set(Constants.cubeArmSpeed);
	}
	
	public void lower() {
		
		liftMotor.set(-(Constants.cubeArmSpeed));
	}
	
	public void stop() {
		
		liftMotor.set(0);
	}
	
	public boolean isLifted90(){
		if(pot.get() > 89.0 && armposition == ArmPosition.Middle) {
			return true;
		}
		else {
		return (topLimit.get() == Constants.notPressed);
		}
		}
	
	public boolean isLifted180() {
		if(pot.get() > 179.0 && armposition == ArmPosition.Top) {
			return true;
		}
		else {
			return (topLimit.get() == Constants.notPressed);
		}
	}
		
	
	public boolean isLowered() {
		if(pot.get() < .1 && armposition == ArmPosition.Bottom) {
			return true;
		}
		else {
		return (bottomLimit.get() == Constants.notPressed);
		}
		}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isGrabbed() {
    	return armValve.get();
    }

    
}

