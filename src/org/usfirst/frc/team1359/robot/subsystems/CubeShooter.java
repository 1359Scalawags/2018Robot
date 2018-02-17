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
	DigitalInput strapUnwoundLimit;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		shooterPull = new Talon(RobotMap.shooterPull);
		lockValve = new Solenoid(RobotMap.shooterLock);
		shooterDownLimit = new DigitalInput(RobotMap.shooterDownLimit);
		strapUnwoundLimit = new DigitalInput(RobotMap.strapunwoundlimit);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void lockShooter() {
		lockValve.set(Constants.locked);
	}

	public void unlockShooter() {
		if (shooterIsDown()) {
			lockValve.set(Constants.unLocked);
		}
	}
	public void forceShooterUnlock() {
		lockValve.set(true);
	}

	public boolean isLocked() {
		return (lockValve.get() == Constants.locked);
	}

	public void pullShooter() {
		if (!shooterIsDown()) {
			shooterPull.set(Constants.shooterArmSpeed);
		} else {
			stopShooterMotor();
		}
	}

	public void unwindShooter() {
		if (!shooterIsUnwound()) {
			shooterPull.set(-(Constants.shooterArmSpeed));
		} else {
			stopShooterMotor();
		}
	}

	public boolean shooterIsDown() {
		if (shooterDownLimit.get() == Constants.pressed) {
			return true;
		} else {
			return false;
		}
	}

	public boolean shooterIsUnwound() {
		return (strapUnwoundLimit.get() == Constants.pressed);

	}

	public void stopShooterMotor() {
		shooterPull.set(0);

	}

}
