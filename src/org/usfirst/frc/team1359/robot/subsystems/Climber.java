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
		
    	elevatorMotor.set(Relay.Value.kForward);
    }
    
    public void stopArm() {
    	
    	elevatorMotor.set(Relay.Value.kOff);
    }
    
    public void retractArm() {
    	
    	elevatorMotor.set(Relay.Value.kReverse);
    }
    
    public void rockForward() {
    	
    	rocker.set(true);
    }
    public void rockBackward() {
    	
    	rocker.set(false);
    }
   
    public void climb(double speed) {
    	
    	climbMotor.set(speed);
    }
    
    public boolean isElevated() {
    	
    	return (upperLimit.get() == Constants.pressed);
    }
    
    public boolean isRetracted() {
    	
    	return (lowerLimit.get() == Constants.pressed);
    }
}

