/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  double mCenter = RobotMap.cameraCenter;
  double mFocalLength = RobotMap.focalLength;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("RPi");
  NetworkTableEntry xvals = table.getEntry("visionX");
  double[] defaultValue = {mCenter};

  public double getAverageX() {
    double[] array = xvals.getDoubleArray(defaultValue);
    if(array.length > 1) return (array[0] + array[1]) / 2;
    else return 319.5; //Default to center pixel value
  }

  public double getVisionAngle() {
    return (Math.atan((getAverageX() - defaultValue[0]) / mFocalLength)) * (180/Math.PI);
  }

  NetworkTable limelightTable = inst.getTable("limelight");

  public double getLimelightAngle() {
    return limelightTable.getEntry("tx").getDouble(0);
  }

  public void setLimelightOn() {
    limelightTable.getEntry("ledMode").setDouble(3);
  }

  public void setLimelightOff() {
    limelightTable.getEntry("ledMode").setDouble(1);
  }
}