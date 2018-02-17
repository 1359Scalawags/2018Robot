package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseShooter extends Command {

	private boolean failed = false;

	public ReleaseShooter() {
		super("ReleaseShooter");
		requires(Robot.kCubeShooter);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.kCubeShooter.shooterIsDown() && Robot.kCubeShooter.shooterIsUnwound()) {
			Robot.kCubeShooter.unlockShooter();
//			failed = false;
//		} else {
//			failed = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if (failed) {
//			return true;
//		}
		return !Robot.kCubeShooter.shooterIsDown();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.kCubeShooter.lockShooter();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		//end();
	}
}
