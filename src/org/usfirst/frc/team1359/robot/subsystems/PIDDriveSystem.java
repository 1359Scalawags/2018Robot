package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.Utilities;
import org.usfirst.frc.team1359.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveSystem extends Subsystem {

	// left side
	Talon m_frontLeft = new Talon(RobotMap.frontleftMotor);
	Talon m_rearLeft = new Talon(RobotMap.rearLeftMotor);
    SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

    // right side
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
    
    public PIDDriveSystem() {
    	leftEncoder.setDistancePerPulse(Constants.feetPerPulse);
    	rightEncoder.setDistancePerPulse(Constants.feetPerPulse);
    	leftEncoder.setSamplesToAverage(Constants.samplesToAverage);
    	rightEncoder.setSamplesToAverage(Constants.samplesToAverage);
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
    
    public double getDistanceLeft() {
    	return leftEncoder.getDistance();
    }
    
    public double getRateLeft() {
    	return leftEncoder.getRate();
    }
    
    public double getDistanceRight() {
    	return rightEncoder.getDistance();
    }
    
    public double getRateRight() {
    	return rightEncoder.getRate();
    }
    
    public double getAverageDistance() {
    	
    	return (getDistanceRight() + getDistanceLeft()) / 2;
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	  
    	
    	// get the encoder input
    	double leftInput = leftEncoder.getRate();
    	double rightInput = rightEncoder.getRate();
    	
    	// set the target point
    	leftControl.SetPoint(convertToEncoderRate(leftSpeed));
    	rightControl.SetPoint(convertToEncoderRate(rightSpeed));
    	
    	// compute the output that gives us that target...clamp values
    	double leftOutput = Utilities.Clamp(leftControl.Compute(leftInput), -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
    	double rightOutput = Utilities.Clamp(rightControl.Compute(rightInput), -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
    	
    	
    	// run the tank drive
    	m_drive.tankDrive(leftOutput, rightOutput);   	
    	
    }
    public double convertToEncoderRate(double motorSpeed) {
    	return Constants.fullDriveSpeed * motorSpeed; // feet per second
    }
    
    public void arcadeDrive(double moveSpeed, double turnSpeed) {
    	m_drive.arcadeDrive(moveSpeed, turnSpeed);
    }
    
   public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {
	   
	   double angleInput = m_Gyro.getAngle();
	   
	   gyroControl.SetPoint(targetAngle);
	   
	   double angleOutput = Utilities.Clamp(gyroControl.Compute(angleInput), -maxTurnSpeed, maxTurnSpeed);
	   
	   m_drive.arcadeDrive(moveSpeed, angleOutput);  
	   
   }
    
}