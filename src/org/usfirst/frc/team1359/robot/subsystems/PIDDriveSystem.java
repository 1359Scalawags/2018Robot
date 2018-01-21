package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon m_frontLeft = new Talon(RobotMap.frontleftMotor);
	Talon m_rearLeft = new Talon(RobotMap.rearLeftMotor);
    SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

    Talon m_frontRight = new Talon(RobotMap.frontRightMotor);
    Talon m_rearRight = new Talon(RobotMap.rearRightMotor);
    SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
    
    ADXRS450_Gyro m_Gyro = new ADXRS450_Gyro();
    
    Encoder leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

    Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
    
    PIDControl leftControl = new PIDControl(1.0, 1.0, 0.1);
    PIDControl rightControl = new PIDControl(1.0, 1.0, 0.1);
    PIDControl gyroControl = new PIDControl(1.0, 1.0, 0.1);
    

    public ADXRS450_Gyro getGyro() {
    	return m_Gyro;
    }
    
    public double getAngle() {
    	return m_Gyro.getAngle();
    }
    public void resetGyro() {
    	m_Gyro.reset();
    }
    public double getGyroRate() {
    	return m_Gyro.getRate();
    }
    public double getLeftEncoder() {
    	return leftEncoder.getDistance();
    }

    public double getRightEncoder() {
    	return rightEncoder.getDistance();
    }

    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    	
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	
    	// get the encoder input
    	double leftInput = leftEncoder.getRate();
    	double rightInput = rightEncoder.getRate();
    	
    	// set the target point
    	leftControl.SetPoint(leftSpeed);
    	rightControl.SetPoint(rightSpeed);
    	
    	// compute the output that gives us that target
    	double leftOutput = leftControl.Compute(leftInput);
    	double rightOutput = rightControl.Compute(rightInput);
    	
    	// run the tank drive
    	m_drive.tankDrive(leftOutput, rightOutput);   	
    	
    }
    
    public void arcadeDrive(double moveSpeed, double turnSpeed) {
    	
    	
    	m_drive.arcadeDrive(moveSpeed, turnSpeed);

    }
    
   public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {
	   
   }
    
}