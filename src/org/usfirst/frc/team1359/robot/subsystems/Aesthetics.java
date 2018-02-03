package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.LeonardoControll;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Aesthetics extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static Aesthetics CommInstance = new Aesthetics();
	public static Aesthetics getCommInstance() {
		return CommInstance;
	}
	
	private I2C i2c;
	
	private int data;
	
	public Aesthetics() {
		i2c = new I2C(I2C.Port.kMXP, RobotMap.leonardoI2CAdress);
	}
	
//	public void sendData(int data) {
//		i2c.write(1, data);
//	}
	
	public void testSend() {
		SmartDashboard.putString("Sending Data", "Trying to send");
		String WriteString = "go";
		char[] CharArray = WriteString.toCharArray();
		byte[] WriteData = new byte[CharArray.length];
		for(int i = 0; i < CharArray.length; i++) {
			WriteData[i] = (byte) CharArray[i];
		}
		i2c.transaction(WriteData, WriteData.length, null, 0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new LeonardoControll());
    }
}

