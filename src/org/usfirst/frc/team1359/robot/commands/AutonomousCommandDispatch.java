package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommandDispatch extends CommandGroup {

	private char switchPosNear;
	private char switchPosFar;
	private char scalePos;
	private DriverStation.Alliance alliance;
	private String userPriority;
	
	public enum AutoStates{
		GoPastLineLeft,
		GoPastLineRight,
		GoPastLineCenter,
		DropCubeSwitchLeft,
		DropCubeSwitchRight,
		DropCubeScaleLeft,
		DropCubeScaleRight
	}
	
    public AutonomousCommandDispatch() {
    	super("AutonomousCommandDispatch");
    	requires(Robot.kPIDDriveSystem);
    	DriverStation driverStation = DriverStation.getInstance();
    	
    	alliance = driverStation.getAlliance();
    	switchPosNear = driverStation.getGameSpecificMessage().charAt(0);
    	switchPosFar = driverStation.getGameSpecificMessage().charAt(2);
    	scalePos = driverStation.getGameSpecificMessage().charAt(1);
    	
    	SmartDashboard.putString("Alliance", alliance.toString());
  
    	if(driverStation.getLocation() == 1) { //left
    		addSequential(new AutonomousLeftPosition()); //get into position
    		SmartDashboard.putString("Location", "Left");
//    		if(switchPosNear == 'L') {
//    			//Drop a cube
//    			SmartDashboard.putString("Close Switch", "Left");
//    		}else {
//    			//Don't drop a cube
//    		}
    	}else if(driverStation.getLocation() == 2) {//center
    		addSequential(new AutonomousMiddlePosition());//get into position
    		SmartDashboard.putString("Location", "Center");
    	}else if(driverStation.getLocation() == 3) {//right
    		addSequential(new AutonomousRightPosition());//get into position
    		SmartDashboard.putString("Location", "Right");
//    		if(switchPosNear == 'R') {
//    			//Drop a cube
//    			SmartDashboard.putString("Close Switch", "Right");
//    		}else {
//    			//Don't drop a cube
//    		}
    	}else {
    		
    	}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
