package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterUnwind extends Command {

	public ShooterUnwind() {
		super("ShooterUnwind");
		requires(Robot.kCubeShooter);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		setInterruptible(false);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.kCubeShooter.unwindShooter();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.kCubeShooter.shooterIsUnwound();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.kCubeShooter.stopShooterMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		// cannot be interrupted
	}
}
