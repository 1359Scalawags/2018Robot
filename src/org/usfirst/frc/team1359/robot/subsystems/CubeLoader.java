package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.arm.CubeMove;

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
		TOP, MIDDLE, BOTTOM
	}

	ArmPosition armposition;

	public CubeLoader() {
		
		bottomLimit = new DigitalInput(RobotMap.grabLowerLimit);
		topLimit = new DigitalInput(RobotMap.grabUpperLimit);
		liftMotor = new Talon(RobotMap.liftMotor);
		// cubeClamp = new Relay(RobotMap.cubeClamp);
		armValve = new Solenoid(RobotMap.armValve);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new CubeMove());
	}
	
//	public double getPosition() {
//		return pot.get();
//	}

	public void grab() {
		armValve.set(true);

	}

	public void release() {
		armValve.set(false);
	}

	public void move(double speed) {
		if (!isAtBottom()) {
			liftMotor.set(-speed);
		}
		else if(!isAtTop()) {
			liftMotor.set(speed);
		}
		else {
			liftMotor.set(0);
		}
//		if (pos == ArmPosition.BOTTOM) {
//			goToBottom();
//		} else if (pos == ArmPosition.MIDDLE) {
//			goToMiddle();
//		} else if (pos == ArmPosition.TOP) {
//			goToTop();
//		} else {
//			stop();
//		}
	}
	
	public void moveAuto(ArmPosition pos) {
		if (pos == ArmPosition.BOTTOM) {
			goToBottom();
		}
		 else if (pos == ArmPosition.TOP) {
				goToTop();
			} else {
				stop();
			}
	}
	
	private void goToBottom() {
		if (!isAtBottom()) {
			lower();
		} else {
			stop();
			armposition = ArmPosition.BOTTOM;
		}
	}
//
//	private void goToMiddle() {
//		if (!isAtMiddle()) {
//			if (getLiftAngle() < 90) {
//				lift();
//			} else if (getLiftAngle() > 90) {
//				lower();
//			}
//		} else {
//			stop();
//			armposition = ArmPosition.MIDDLE;
//		}
//	}

	private void goToTop() {
		if (!isAtTop()) {
			lift();
		} else {
			armposition = ArmPosition.TOP;
			stop();
		}
	}

	private void lift() {
		liftMotor.set(Constants.cubeArmSpeed);
	}

	private void lower() {
		liftMotor.set(-(Constants.cubeArmSpeed));
	}

	public void stop() {

		liftMotor.set(0);
	}

//	public boolean isAtMiddle() {
////		if (Math.abs(getLiftAngle() - 90) < Constants.angleTolerance) {
////			return true;
////		} else {
////			return (topLimit.get() == Constants.notPressed);
////		}
//		if (Math.abs(getLiftAngle() - 90) < Constants.angleTolerance) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}

//	public double getLiftAngle() {
//		return pot.get() * Constants.anglePerValue;
//	}

	public boolean isAtTop() {
//		if (getLiftAngle() > 179.0 && armposition == ArmPosition.TOP) {
//			return true;
//		} else {
//			return (topLimit.get() == Constants.notPressed);
//		}
		return (topLimit.get() == Constants.pressed);
	}

	public boolean isAtBottom() {
//		if (getLiftAngle() < .1 && armposition == ArmPosition.BOTTOM) {
//			return true;
//		} else {
//			return (bottomLimit.get() == Constants.notPressed);
//		}
		return (bottomLimit.get() == Constants.pressed);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public boolean isGrabbed() {
		return armValve.get();
	}

}
