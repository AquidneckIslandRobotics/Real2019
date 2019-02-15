/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //Drive Motors
  public static final int leftLeader = 1;
  public static final int leftFollower1 = 2;
  public static final int leftFollower2 = 3;
  public static final int rightLeader = 4;
  public static final int rightFollower1 = 5;
  public static final int rightFollower2 = 6;

  //Elevator Motor
  public static final int elevator = 0; //UPDATE

  //Elevator Presets
  public static final double intake = 0;
  public static final double cargoShip = 0;
  public static final double lowRocket = 0;
  public static final double midRocket = 0;
  public static final double highRocket = 0;

  //Constants
  public static final double feetToClicks = (1024) / (0.5 * Math.PI);
  public static final double pixelsToDegrees = 0.0953125;
  public static final double cameraCenter = 319.5;
  public static final double focalLength = 554.256;
  public static final double triggerDeadzone = 0.05;
}
