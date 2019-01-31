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

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("vision");
  NetworkTableEntry xvals = table.getEntry("xval");
  double[] defaultValue = {320};

  public double getAverageX() {
    double[] array = xvals.getDoubleArray(defaultValue);
    if(array.length > 1) return (array[0] + array[1]) / 2;
    else return 320; //Default to center pixel value
  }

  public double getVisionAngle() {
    // double averageX = getAverageX();
    // double robotAngle = Robot.mDrive.getAngle();
    // if(averageX < 320) {
    //   return (robotAngle - ((320 - averageX) * RobotMap.pixelsToDegrees));
    // } else if(averageX > 320) {
    //   return (robotAngle + ((averageX - 320) * RobotMap.pixelsToDegrees));
    // } else {
    //   return robotAngle;
    // }
    return (getAverageX() - 320) * RobotMap.pixelsToDegrees;
  }

}
