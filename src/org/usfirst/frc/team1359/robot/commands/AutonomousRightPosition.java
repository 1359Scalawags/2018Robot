package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousRightPosition extends CommandGroup {

	private char switchPosNear;
	private char scalePos;

	public AutonomousRightPosition() {
		super("AutonomousRightPositionCommandGroup");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kCubeLoader);
		requires(Robot.kCubeShooter);

		DriverStation driverStation = DriverStation.getInstance();
		switchPosNear = driverStation.getGameSpecificMessage().charAt(0);
		scalePos = driverStation.getGameSpecificMessage().charAt(1);

		if (switchPosNear == 'R' && scalePos == 'L') { // drop cube in switch
			addSequential(new CubeGrab());
			addSequential(new MoveDistance(10, true)); // random value in MoveForward()
			addSequential(new TurnByAngle(-90));
			addSequential(new CubeAtTop()); // CHANGE THIS 
			addSequential(new CubeRelease());
			SmartDashboard.putString("Switch Close", "Right");
		} else if (scalePos == 'R' && switchPosNear == 'L') { // drop cube in scale
			addSequential(new MoveDistance(20, true)); // random value in MoveForward()
			addSequential(new TurnByAngle(-90));
			addSequential(new MoveDistance(5, false)); // random value in MoveForward()
			addSequential(new ReleaseShooter()); // assuming PrepareToLaunchShooter was already ran
			SmartDashboard.putString("Scale Close", "Right");
		} else if (scalePos == 'R' && switchPosNear == 'R') { // determined by smartDashboard
			if (Robot.AutonomousLeftOrRightPriority == "Switch") { // drop cube in switch
				addSequential(new CubeGrab());
				addSequential(new MoveDistance(10, true)); // random value in MoveForward()
				addSequential(new TurnByAngle(-90));
				addSequential(new CubeAtTop()); // CHANGE THIS
				addSequential(new CubeRelease());
				SmartDashboard.putString("Switch Close", "Right");
			} else if (Robot.AutonomousLeftOrRightPriority == "Scale") { // drop cube in scale
				addSequential(new MoveDistance(20, true)); // random value in MoveForward()
				addSequential(new TurnByAngle(-90));
				addSequential(new MoveDistance(5, false)); // random value in MoveForward()
				addSequential(new ReleaseShooter()); // assuming PrepareToLaunchShooter was already ran
				SmartDashboard.putString("Scale Close", "Right");
			}
		} else { // don't drop cube, just cross line
			addSequential(new MoveDistance(10, true)); // random value in MoveForward()
			SmartDashboard.putString("Neither Close", "Right");
		}
		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
