package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.deprecated.CubeAtTop;
import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.arm.CubeGrab;
import org.usfirst.frc.team1359.robot.commands.arm.CubeRelease;
import org.usfirst.frc.team1359.robot.commands.drive.MoveDistance;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddlePosition extends CommandGroup {

	private char switchPos;

	public AutonomousMiddlePosition() {
		super("AutonomousMiddlePosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kCubeLoader);
		DriverStation driverStation = DriverStation.getInstance();

		switchPos = driverStation.getGameSpecificMessage().charAt(0);

		if (switchPos == 'L') { // left switch
			addSequential(new CubeGrab());
			addSequential(new MoveDistance(Constants.avoidSwitchDistance, true));
			addSequential(new TurnByAngle(-45));
			addSequential(new MoveDistance(Constants.avoidSwitchDistance, true));
			addSequential(new TurnByAngle(45));
			addSequential(new MoveDistance(Constants.avoidSwitchDistance, true));
			if (Robot.AutonomousMiddlePriority == "Yes") {
				addSequential(new CubeAtTop()); // CHANGE THIS
				addSequential(new CubeRelease());
			} else {

			}

		} else { // right switchS
			addSequential(new CubeGrab());
			addSequential(new MoveDistance(Constants.avoidSwitchDistance, true));
			addSequential(new TurnByAngle(45));
			addSequential(new MoveDistance(Constants.avoidSwitchDistance, true));
			addSequential(new TurnByAngle(-45));
			addSequential(new MoveDistance(Constants.avoidSwitchDistance, true));
			if (Robot.AutonomousMiddlePriority == "Yes") {
				addSequential(new CubeAtTop()); // CHANGE THIS
				addSequential(new CubeRelease());
			} else {

			}

		}

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

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
