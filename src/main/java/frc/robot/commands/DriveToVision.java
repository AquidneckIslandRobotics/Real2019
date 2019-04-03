/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.utilities.SpeedOutput;

public class DriveToVision extends Command {

  public PIDController turnController;
  public SpeedOutput turnOutput;
  private double mTargetDegrees;
  private Timer mTimer;

  public DriveToVision() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);    
    requires(Robot.mDrive);
    turnOutput = new SpeedOutput();
    turnController = new PIDController(0.0452, 0, 0.0, Robot.mDrive.gyro, turnOutput);
    mTimer = new Timer();
    mTargetDegrees = Robot.mDrive.getAngle() + Robot.mVision.getLimelightAngle();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.mVision.setLimelightOn();
    turnController.setAbsoluteTolerance(0.25);
    turnController.setInputRange(-180, 180);
    turnController.setContinuous(true);
    turnController.setOutputRange(-0.5, 0.5); //(-1,1)
    turnController.setSetpoint(mTargetDegrees);
    turnController.enable();
    mTimer.start();
    Robot.mDrive.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    turnController.setSetpoint((Robot.mDrive.getAngle() + Robot.mVision.getLimelightAngle()) % 180);
    SmartDashboard.putNumber("DriveToAngle", (Robot.mDrive.getAngle() + Robot.mVision.getLimelightAngle()) % 180);
    double rotation = turnOutput.getSpeed();
    Robot.mDrive.arcadeDrive(0.4, rotation);
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
    Robot.mVision.setLimelightOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
