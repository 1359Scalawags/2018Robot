package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeLoader extends Subsystem {
	
	DigitalInput bottomLimit, topLimit;
	Talon liftMotor;
	Relay cubeClamp; 
	Solenoid armValve;
	
	public CubeLoader(){
		
		bottomLimit = new DigitalInput(RobotMap.bottomLimit);
		topLimit = new DigitalInput(RobotMap.topLimit);
		liftMotor = new Talon(RobotMap.liftMotor);
		cubeClamp = new Relay(RobotMap.cubeClamp);
		armValve = new Solenoid(RobotMap.armValve);
		
	}
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

