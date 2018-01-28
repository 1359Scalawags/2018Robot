package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeLift extends Command {

//	public enum DaysOfWeek {
//		Saturday,
//		Sunday,
//		Monday,
//		Tuesday
//	}
//	
//	DaysOfWeek day = DaysOfWeek.Monday;
//	
//	public void setDay(DaysOfWeek day) {
//		this.day = day;
//	}
	
    public CubeLift() {
    	super("CubeLift");
    	requires(Robot.kCubeLoader);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kCubeLoader.lift();
    
    	//Robot.kCubeLoader.liftCube(Robot.kOI.getJoystick3().getX());
    }
    
   

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.kCubeLoader.getPosition() < 90 && !Robot.kCubeLoader.isLifted()) {
        	
    		return false;
    	}
    	else {    		
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {

    Robot.kCubeLoader.stop();
    }


    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
