package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.Utilities;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class turnByAngle extends Command {
	private double m_startAngle;
	private double m_deltaAngle;
    public turnByAngle(double angle) {
    	
    	super("turnByAngle");
    	requires(Robot.kDriveSystem);
    	m_deltaAngle = angle;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    m_startAngle = Robot.kDriveSystem.getAngle();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angleRemaining = m_deltaAngle - Utilities.NormalizeAngle(Robot.kDriveSystem.getAngle() - m_startAngle);
    	double turnSpeed = Math.max(-0.5, Math.min(0.5, 0.025 * -angleRemaining));
    	
    	Robot.kDriveSystem.arcadeDrive(.01, turnSpeed);

		if(Math.abs(angleRemaining) < RobotMap.ROTATE_TOLERANCE && Math.abs(Robot.kDriveSystem.getGyroRate()) < 5){
			end();
		}
    	
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
