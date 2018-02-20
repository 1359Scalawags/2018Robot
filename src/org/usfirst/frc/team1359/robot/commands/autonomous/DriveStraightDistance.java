package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightDistance extends Command {

	double targetHeading;
	double targetDistance;
	
    public DriveStraightDistance(double distance) {
    	super("DriveStraightDistance");
        // Use requires() here to declare subsystem dependencies
        requires(Robot.kPIDDriveSystem);
        targetDistance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetHeading = Robot.kPIDDriveSystem.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kPIDDriveSystem.driveForward(Constants.maxMotorSpeed, targetHeading);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.kPIDDriveSystem.getAverageDistance() > targetDistance) {
        	return true;
        }
        else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kPIDDriveSystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
