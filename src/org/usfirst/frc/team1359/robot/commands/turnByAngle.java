package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;

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
    	
    	Robot.kDriveSystem.arcadeDrive(.01, turnSpeed);
    	
    	float angle = NormalizeAngle(PullGyroAngle() - m_startAngle);

		ArcadeDrive(0.0f, std::max(-0.5, std::min(0.5, 0.025 * -angle)));

		if(abs(angle) < ROTATE_TOLERANCE && abs(Gyro.GetRate()) < 5){

			return true;

		}else{

			return false;

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
