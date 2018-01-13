package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon m_frontLeft = new Talon(RobotMap.frontleftMotor);
	Talon m_rearLeft = new Talon(RobotMap.rearLeftMotor);
    SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

    Talon m_frontRight = new Talon(RobotMap.frontRightMotor);
    Talon m_rearRight = new Talon(RobotMap.rearRightMotor);
    SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
    
    ADXRS450_Gyro Gyro = new ADXRS450_Gyro();
    
    public ADXRS450_Gyro getGyro() {
    	return Gyro;
    }
    
    public double getAngle() {
    	return Gyro.getAngle();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    	
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	
    	m_drive.tankDrive(leftSpeed, rightSpeed);

    	
    	
    	
    }
}

