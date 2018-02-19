package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.climber.ClimbCommand;

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
	Talon climbMotor;
	//Relay elevatorMotor;
	Talon elevatorMotor;
	Solenoid rocker;
	boolean climberLocked;

	public Climber() {

		climbMotor = new Talon(RobotMap.climbMotor);
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
		setDefaultCommand(new ClimbCommand());
	}

	public void unlockClimber() {
		climberLocked = false;
	}
	
	public boolean getClimberLock() {
		return climberLocked;
	}
	
	public void extendArm() {
		if (!isElevated() && !climberLocked && isRockedForward()) {
			//elevatorMotor.set(Relay.Value.kForward);
			elevatorMotor.set(Constants.elevatorSpeed);
		} else {
			//elevatorMotor.set(Relay.Value.kOff);
			elevatorMotor.set(0);
			System.out.println("Climber::extendArm(): elevatorMotor.set(0)");
		}
	}

	public void retractArm() {
		if (!isRetracted() && isRockedForward()) {
			//elevatorMotor.set(Relay.Value.kReverse);
			elevatorMotor.set(-(Constants.elevatorSpeed));
		} else {
			//elevatorMotor.set(Relay.Value.kOff);
			elevatorMotor.set(0);
			System.out.println("Climber::retractArm(): elevatorMotor.set(0)");
		}
	}
	
	public boolean isRockedForward( ) {
		return rocker.get();
	}

	public void rockForward() {

		rocker.set(true);
	}

	public void rockBackward() {

		rocker.set(false);
	}

	public void climb(double speed) {
		// warning: this motor is under user control...no protections at this point
		if(climberLocked) {
			climbMotor.set(0);
		}else {
			climbMotor.set(speed);
		}
	}

	public void stopArm() {
		//elevatorMotor.set(Relay.Value.kOff);
		elevatorMotor.set(0);
	}

	public boolean isElevated() {
		boolean value = (upperLimit.get() == Constants.pressed);
		System.out.println("Climber::isElevated(): " + value);
		return value;
	}

	public boolean isRetracted() {
		boolean value = (lowerLimit.get() == Constants.pressed);
		System.out.println("Climber::isElevated(): " + value);
		return value;
	}
	
	
}
