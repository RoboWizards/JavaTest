/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team522Robot extends SimpleRobot {

    private RobotDrive drive = new RobotDrive(1, 2, 3, 4);
    private Joystick leftStick = new Joystick(1);
    private Joystick rightStick = new Joystick(2);
    private Joystick thirdStick = new Joystick (3);
    private Compressor air = new Compressor(1,1);
    private Solenoid pistonDown = new Solenoid(7); 
    private Solenoid pistonUp = new Solenoid(8); 
    
    public Team522Robot() {

    }
    
    public void autonomous() {
               
        air.start();
        
        drive.drive(-0.5, -0.5);
        Timer.delay(2);
    }
    
    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){
            drive.tankDrive(leftStick.getY(), rightStick.getY());
            
            if(thirdStick.getRawButton(1)){
                deployDoor(true);
            }
            else if(thirdStick.getRawButton(3) || thirdStick.getRawButton(2)){
                deployDoor(false);
            }
            
            Timer.delay(0.005);
        }
    }
    
    public void deployDoor(boolean raiseDoor){
        
        //In-case teleop started first
        if(!air.enabled()){
            air.start();
        }
        
        if(raiseDoor){
            pistonUp.set(true);        
            pistonDown.set(false);
        }
        else{
            pistonDown.set(true);
            pistonUp.set(false);
        }
    }
    
}
