
package org.usfirst.frc.team4511.BERTHA;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4511.BERTHA.commands.ArmAutoUp;
import org.usfirst.frc.team4511.BERTHA.commands.ArmAutoDown;
import org.usfirst.frc.team4511.BERTHA.commands.Autonomous;
import org.usfirst.frc.team4511.BERTHA.commands.GyroReset;
import org.usfirst.frc.team4511.BERTHA.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//Subsystems
	public static final Lift lift = new Lift();
	public static final SoulTrain soulTrain = new SoulTrain();
	public static OI oi;
	
	public static Command autonomousCommand;
	

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code
     */
	
	public Robot() {
		soulTrain.gyro.initGyro();
        //lift.compressor.start();
	}
    public void robotInit() {
    		
    	autonomousCommand = new Autonomous();
    	oi = new OI();
		
        SmartDashboard.putData("Photogate Command", new ArmAutoUp());
        SmartDashboard.putData("Photogate Down", new ArmAutoDown());
        SmartDashboard.putBoolean("photoTop", Robot.lift.photoTop.get());
        SmartDashboard.putBoolean("photoBot", Robot.lift.photoBot.get());
        SmartDashboard.putData("Reset Gyro", new GyroReset());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
