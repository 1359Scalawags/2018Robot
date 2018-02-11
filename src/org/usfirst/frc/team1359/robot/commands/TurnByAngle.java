package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.Utilities;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnByAngle extends Command {
	private double m_startAngle;
	private double m_deltaAngle;
	private double m_angleRemaining;
	private final double m_turnRateMultiplier = 0.5;
	private final double m_maxTurnRate = 0.55;
	private double m_MotorSpeed = 0;
	
    public TurnByAngle(double angle) {
    	
    	super("TurnByAngle");
    	requires(Robot.kDriveSystem);
    	m_deltaAngle = angle;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    m_startAngle = Robot.kDriveSystem.getAngle();
	SmartDashboard.putNumber("AngleRemaining", m_angleRemaining);
	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kPIDDriveSystem.arcadeDrive(0, 10, m_startAngle);
    	
    	m_angleRemaining = Utilities.NormalizeAngle(m_deltaAngle - (Robot.kDriveSystem.getAngle() - m_startAngle));
    	//double turnSpeed = Math.max(-m_maxTurnRate, Math.min(m_maxTurnRate, m_turnRateMultiplier * -Math.pow(m_angleRemaining, .333)));
    	
//    	if(this.m_angleRemaining > RobotMap.ROTATE_TOLERANCE) {
//	    	if(Robot.kDriveSystem.getGyroRate() < 20) {
//	    		this.m_MotorSpeed = this.m_MotorSpeed + .01;
//	    	}else if(Robot.kDriveSystem.getGyroRate() > 40) {
//	    		this.m_MotorSpeed = this.m_MotorSpeed - .01;
//	    	}    		
//    	}else if(this.m_angleRemaining < RobotMap.ROTATE_TOLERANCE){
//	    	if(Robot.kDriveSystem.getGyroRate() < -20) {
//	    		this.m_MotorSpeed = this.m_MotorSpeed - .01;
//	    	}else if(Robot.kDriveSystem.getGyroRate() > -40) {
//	    		this.m_MotorSpeed = this.m_MotorSpeed + .01;
//	    	}    		
//    	}else {
//    		this.m_MotorSpeed = 0;
//    	}
//    	
//    	m_MotorSpeed = Math.max(-.5, Math.min(.5, m_MotorSpeed));
//    	
//    	Robot.kDriveSystem.arcadeDrive(.01, this.m_MotorSpeed);
//    	SmartDashboard.putNumber("AngleRemaining", m_angleRemaining);
//    	SmartDashboard.putNumber("GyroRate", Robot.kDriveSystem.getGyroRate());
//
//    	
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if(Math.abs(m_angleRemaining) < RobotMap.ROTATE_TOLERANCE && Math.abs(Robot.kDriveSystem.getGyroRate()) < 5){
			return true;
		}else {
			return false;
		}

    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kDriveSystem.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
