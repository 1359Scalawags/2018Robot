package org.usfirst.frc.team1359.deprecated;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeonardoDisplayBlue extends Command {

	private I2C i2c;
	
    public LeonardoDisplayBlue() {
    	super("LeonardoDisplayBlue");
    	requires(Robot.kAesthetics);
    	//i2c = new I2C(I2C.Port.kOnboard, );
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
