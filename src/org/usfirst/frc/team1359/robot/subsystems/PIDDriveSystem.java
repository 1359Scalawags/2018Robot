package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class PIDDriveSystem extends PIDSubsystem {
	
	SpeedController leftMotor = new Talon(1);
	SpeedController rightMotor = new Talon(2);
	 
    // Initialize your subsystem here
    public PIDDriveSystem() {
    	super("PIDDriveSystem", 1.0, .01, 0.0); // change these values (P, I, D)
    	setAbsoluteTolerance(.05);
    	PIDController p = getPIDController();
    	p.setContinuous(false);
    	
    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }


    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }

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
    	
    	m_drive.tankDrive(leftSpeed, rightSpeed);
    	
    	
    }
    
    public void arcadeDrive(double moveSpeed, double turnSpeed) {
    	
    	m_drive.arcadeDrive(moveSpeed, turnSpeed);

    }
	
	

}
