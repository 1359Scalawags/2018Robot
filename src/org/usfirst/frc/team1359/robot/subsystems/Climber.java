package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Author: Rebecca Munk
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DigitalInput lowerLimit, upperLimit;
	Talon climbMotor;
	Relay elevatorMotor;
	Solenoid rocker;
	
	
	public Climber(){
		
		climbMotor = new Talon(RobotMap.climbMotor);
		elevatorMotor = new Relay(RobotMap.elevatorMotor);
		lowerLimit = new DigitalInput(RobotMap.lowerLimit);
		upperLimit = new DigitalInput(RobotMap.upperLimit);
		rocker = new Solenoid(RobotMap.rocker);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void extendArm() {
		if(!isElevated()) {
			elevatorMotor.set(Relay.Value.kForward);
		}
		else {
			elevatorMotor.set(Relay.Value.kOff);
		}
    }
    
    public void retractArm() {
    	if(!isRetracted()) {
        	elevatorMotor.set(Relay.Value.kReverse); 		
    	}
    	else {
    		elevatorMotor.set(Relay.Value.kOff);
    	}
    }
    
    public void rockForward() {
    	
    	rocker.set(true);
    }
    public void rockBackward() {
    	
    	rocker.set(false);
    }
   
    public void climb(double speed) {
    	// warning: this motor is under user control...no protections at this point
    	climbMotor.set(speed);
    }
    
    public void stopElevator() {
    	elevatorMotor.set(Relay.Value.kOff);
    }
    
    public boolean isElevated() {
    	
    	return (upperLimit.get() == Constants.pressed);
    }
    
    public boolean isRetracted() {
    	
    	return (lowerLimit.get() == Constants.pressed);
    }
}

