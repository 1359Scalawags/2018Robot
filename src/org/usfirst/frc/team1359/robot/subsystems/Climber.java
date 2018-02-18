package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Author: Rebecca Munk
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DigitalInput lowerLimit, upperLimit;
	Talon climbMotor;
	Relay elevatorMotor;
	//Talon elevatorMotor;
	Solenoid rocker;

	public Climber() {

		climbMotor = new Talon(RobotMap.climbMotor);
		elevatorMotor = new Relay(RobotMap.elevatorMotor);
		//elevatorMotor = new Talon(RobotMap.elevatorMotor);
		lowerLimit = new DigitalInput(RobotMap.grabLowerLimit);
		upperLimit = new DigitalInput(RobotMap.grabUpperLimit);
		rocker = new Solenoid(RobotMap.rocker);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void extendArm() {
		if (!isElevated()) {
			elevatorMotor.set(Relay.Value.kForward);
			//elevatorMotor.set(Constants.elevatorSpeed);
		} else {
			elevatorMotor.set(Relay.Value.kOff);
			//elevatorMotor.set(0);
		}
	}

	public void retractArm() {
		if (!isRetracted()) {
			elevatorMotor.set(Relay.Value.kReverse);
			//elevatorMotor.set(-(Constants.elevatorSpeed));
		} else {
			elevatorMotor.set(Relay.Value.kOff);
			//elevatorMotor.set(0);
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

	public void stopArm() {
		elevatorMotor.set(Relay.Value.kOff);
		//elevatorMotor.set(0);
	}

	public boolean isElevated() {

		return (upperLimit.get() == Constants.pressed);
	}

	public boolean isRetracted() {

		return (lowerLimit.get() == Constants.pressed);
	}
	
	
}
