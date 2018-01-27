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
	
	public void lower(double speed) {
		
		
	}
	
	public void stop() {
		
		liftMotor.set(0);
	}
	
	public boolean isLifted(){
		
		return (topLimit.get() == Constants.pressed);
	}
	
	public boolean isLowered() {
		return (bottomLimit.get() == Constants.notPressed);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

