package org.usfirst.frc.team1359.deprecated;
//package org.usfirst.frc.team1359.robot.commands.climber;
//
//import org.usfirst.frc.team1359.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class ExtendClimberArm extends Command {
//
//	public ExtendClimberArm() {
//		super("ExtendClimberArm");
//		requires(Robot.kClimber);
//		// Use requires() here to declare subsystem dependencies
//		// eg. requires(chassis);
//	}
//
//	// Called just before this Command runs the first time
//	protected void initialize() {
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	protected void execute() {
//		Robot.kClimber.extendArm();
//	}
//
//	// Make this return true when this Command no longer needs to run execute()
//	protected boolean isFinished() {
//		return Robot.kClimber.isElevated();
//	}
//
//	// Called once after isFinished returns true
//	protected void end() {
//		Robot.kClimber.stopArm();
//	}
//
//	// Called when another command which requires one or more of the same
//	// subsystems is scheduled to run
//	protected void interrupted() {
//		Robot.kClimber.stopArm();
//	}
//}
