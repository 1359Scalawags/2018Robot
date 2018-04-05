package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.commands.arm.MoveToMiddle;
import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.arm.CubeGrab;
import org.usfirst.frc.team1359.robot.commands.arm.CubeRelease;
import org.usfirst.frc.team1359.robot.commands.autonomous.DriveStraightDistance;
import org.usfirst.frc.team1359.robot.commands.shooter.ReleaseShooter;

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
		super("AutonomousRightPosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kCubeLoader);
		requires(Robot.kCubeShooter);

		DriverStation driverStation = DriverStation.getInstance();
		switchPosNear = driverStation.getGameSpecificMessage().charAt(0);
		scalePos = driverStation.getGameSpecificMessage().charAt(1);

		if (switchPosNear == 'R' && scalePos == 'L') { // drop cube in switch
			if (Robot.AutonomousLeftOrRightPriority.equals("Scale")){ // go to scale
				addSequential(new CubeGrab());
				addSequential(new DriveStraightDistance(Constants.distanceToCenterPassage, Constants.autoDriveSpeed));
				addSequential(new TurnByAngle(-90));
				addSequential(new DriveStraightDistance(Constants.distanceAcrossCenterPassage, Constants.autoDriveSpeed));
				addSequential(new TurnByAngle(-25)); // random value
				addSequential(new CubeRelease());
				addSequential(new Delay(1));
				addSequential(new ReleaseShooter());
				SmartDashboard.putString("Scale Close", "Right");
			}
			else { // drop cube in switch
			addSequential(new CubeGrab());
			addSequential(new DriveStraightDistance(Constants.distanceToSwitchCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
			addSequential(new TurnByAngle(-90));
			addSequential(new DriveStraightDistance(Constants.approachSwitchEnd, Constants.autoDriveSpeed*.75));
			addSequential(new MoveToMiddle());  
			addSequential(new CubeRelease());
			SmartDashboard.putString("Switch Close", "Right");
			}
		} else if (scalePos == 'R' && switchPosNear == 'L') { // drop cube in scale
			addSequential(new DriveStraightDistance(Constants.distanceToScaleCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
			addSequential(new TurnByAngle(115));
			addSequential(new DriveStraightDistance(Constants.approachScaleEnd, Constants.autoDriveSpeed)); // random value in MoveForward()
			addSequential(new ReleaseShooter()); // assuming PrepareToLaunchShooter was already ran
			// ==== mirrored from left side
			addSequential(new Delay(1));
			addSequential(new DriveStraightDistance(2.5, Constants.autoDriveSpeed*.5));
			SmartDashboard.putString("Scale Close", "Right");
		} else if (scalePos == 'R' && switchPosNear == 'R') { // determined by smartDashboard
			if (Robot.AutonomousLeftOrRightPriority.equals("Switch")) { // drop cube in switch
				addSequential(new CubeGrab());
				addSequential(new DriveStraightDistance(Constants.distanceToSwitchCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
				addSequential(new TurnByAngle(-90));
				addSequential(new MoveToMiddle()); // CHANGE THIS
				addSequential(new CubeRelease());
				SmartDashboard.putString("Switch Close", "Right");
			} else if (Robot.AutonomousLeftOrRightPriority.equals("Scale")) { // drop cube in scale
				addSequential(new DriveStraightDistance(Constants.distanceToScaleCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
				addSequential(new TurnByAngle(115));
				addSequential(new DriveStraightDistance(Constants.approachScaleEnd, Constants.autoDriveShortSpeed)); // random value in MoveForward()
				addSequential(new ReleaseShooter()); // assuming PrepareToLaunchShooter was already ran
				// ==== mirrored from left side
				addSequential(new Delay(1));
				addSequential(new DriveStraightDistance(2.5, Constants.autoDriveSpeed*.5));
				SmartDashboard.putString("Scale Close", "Right");
			}
		} else { // go to scale
			if (Robot.AutonomousLeftOrRightPriority.equals("Scale")) {
			addSequential(new CubeGrab());
			addSequential(new DriveStraightDistance(Constants.distanceToCenterPassage, Constants.autoDriveSpeed));
			addSequential(new TurnByAngle(-90));
			addSequential(new DriveStraightDistance(Constants.distanceAcrossCenterPassage, Constants.autoDriveSpeed));
			addSequential(new TurnByAngle(-25));
			addSequential(new CubeRelease());
			addSequential(new Delay(1));
			addSequential(new ReleaseShooter());
			SmartDashboard.putString("Scale Close", "Right");
			}
			else {
				addSequential(new DriveStraightDistance(Constants.distanceToSwitchCenterline, Constants.autoDriveSpeed));
				SmartDashboard.putString("Neither Close", "None");
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
}
