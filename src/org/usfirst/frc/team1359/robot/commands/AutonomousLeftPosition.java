package org.usfirst.frc.team1359.robot.commands;

import org.usfirst.frc.team1359.deprecated.CubeAtTop;
import org.usfirst.frc.team1359.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousLeftPosition extends CommandGroup {

	private char switchPosNear;
	private char scalePos;

	public AutonomousLeftPosition() {
		super("AutonomousLeftPositionCommandGroup");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kCubeLoader);
		requires(Robot.kCubeShooter);

		DriverStation driverStation = DriverStation.getInstance();
		switchPosNear = driverStation.getGameSpecificMessage().charAt(0);
		scalePos = driverStation.getGameSpecificMessage().charAt(1);

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		if (switchPosNear == 'L' && scalePos == 'R') { // Drop cube in switch
			addSequential(new CubeGrab());
			addSequential(new MoveDistance(10, true)); // random value in MoveForward()
			addSequential(new TurnByAngle(90));
			addSequential(new CubeAtTop()); // CHANGE THIS
			addSequential(new CubeRelease());
			SmartDashboard.putString("Close Switch", "Left");
		} else if (scalePos == 'L' && switchPosNear == 'R') { // drop cube in scale
			addSequential(new MoveDistance(20, true)); // random value in MoveForward()
			addSequential(new TurnByAngle(90));
			addSequential(new MoveDistance(5, false)); // random value in MoveForward()
			addSequential(new ReleaseShooter()); // assuming PrepareToLaunchShooter was already ran
			SmartDashboard.putString("Close Scale", "Left");
		} else if (scalePos == 'L' && switchPosNear == 'L') {
			if (Robot.AutonomousLeftOrRightPriority == "Switch") {
				addSequential(new MoveDistance(10, true)); // random value in MoveForward()
				addSequential(new TurnByAngle(90));
				addSequential(new CubeGrab());
				addSequential(new CubeAtTop()); // CHANGE THIS
				addSequential(new CubeRelease());
				SmartDashboard.putString("Close Switch", "Left");
			} else if (Robot.AutonomousLeftOrRightPriority == "Scale") {
				addSequential(new MoveDistance(20, true)); // random value in MoveForward()
				addSequential(new TurnByAngle(90));
				addSequential(new MoveDistance(5, false)); // random value in MoveForward()
				addSequential(new ReleaseShooter()); // assuming PrepareToLaunchShooter was already ran
				SmartDashboard.putString("Close Scale", "Left");
			}

		} else { // cross line, don't drop cube
			addSequential(new MoveDistance(10, true)); // random value in MoveForward()
			SmartDashboard.putString("Neither Close", "Left");
		}
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
