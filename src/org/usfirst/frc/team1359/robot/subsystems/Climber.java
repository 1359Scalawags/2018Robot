package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.climber.ClimberStrap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Author: Rebecca Munk
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DigitalInput lowerLimit, upperLimit;
	Talon climbMotorStrap;
	//Relay elevatorMotor;
	Talon elevatorMotor;
	Solenoid rocker;
	boolean climberLocked;

	public Climber() {

		climbMotorStrap = new Talon(RobotMap.climbMotor);
		//elevatorMotor = new Relay(RobotMap.elevatorMotor);
		elevatorMotor = new Talon(RobotMap.elevatorMotor);
		lowerLimit = new DigitalInput(RobotMap.climbBottomLimit);
		upperLimit = new DigitalInput(RobotMap.climbTopLimit);
		rocker = new Solenoid(RobotMap.rocker);
		climberLocked = true;
		LiveWindow.add(elevatorMotor);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ClimberStrap());
	}

	public void unlockClimber() {
		climberLocked = false;
	}
	
	public boolean getClimberLock() {
		return climberLocked;
	}
	
//	public void extendArm() {
//		if (!isElevated() && !climberLocked && isRockedForward()) {
//			//elevatorMotor.set(Relay.Value.kForward);
//			elevatorMotor.set(Constants.elevatorSpeed);
//		} else {
//			//elevatorMotor.set(Relay.Value.kOff);
//			elevatorMotor.set(0);
//		}
//	}
	public void ClimberArm(double speed) {
		if(climberLocked) {
			elevatorMotor.set(0);
		}
		else {
			elevatorMotor.set(speed);
		}
	}
	
//	public void retractArm() {
//		if (!isRetracted() && isRockedForward()) {
//			//elevatorMotor.set(Relay.Value.kReverse);
//			elevatorMotor.set(-(Constants.elevatorSpeed * .8));
//		} else {
//			//elevatorMotor.set(Relay.Value.kOff);
//			elevatorMotor.set(0);
//		}
//	}
	
	public boolean isRockedForward( ) {
		return rocker.get();
	}

	public void rockForward() {
		
		rocker.set(true);
	}

	public void rockBackward() {
		rocker.set(false);
//		if(isRetracted()) {
//		rocker.set(false);
//		}
//		else {
//			rocker.set(true);
//		}
	}

	public void climberStrap(double speed) {
		// warning: this motor is under user control...no protections at this point
		if(climberLocked) {
			climbMotorStrap.set(0);
		}else {
			climbMotorStrap.set(speed);
		}
	}

	public void stopArm() {
		//elevatorMotor.set(Relay.Value.kOff);
		elevatorMotor.set(0);
	}

	public boolean isElevated() {
		boolean value = (upperLimit.get() == Constants.pressed);
		return value;
	}

	public boolean isRetracted() {
		boolean value = (lowerLimit.get() == Constants.pressed);
		return value;
	}
	
	
}
