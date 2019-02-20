/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CheesyDrive extends Command {
  public CheesyDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.mDrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.drivingFast()) {
      if(Robot.mDrive.drivingForwards) Robot.mDrive.cheesyDrive(1);
      else Robot.mDrive.inverseCheesyDrive(1);
    } else {
      if(Robot.mDrive.drivingForwards) Robot.mDrive.cheesyDrive(0.5);
      else Robot.mDrive.inverseCheesyDrive(0.65);
    }
    // if(Robot.mDrive.drivingForwards) Robot.mDrive.tankDrive();
    // else Robot.mDrive.inverseTankDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mDrive.stopDriveMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
