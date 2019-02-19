/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.utilities.EncoderPIDSource;
import frc.robot.utilities.SpeedOutput; 

public class DriveDistance extends Command {

  public PIDController leftDrivePID, rightDrivePID;
  public EncoderPIDSource leftDriveSource, rightDriveSource;
  public SpeedOutput leftSpeedOutput, rightSpeedOutput;
  private double mTargetClicks;
  private Timer mTimer;

  public DriveDistance(double targetFeet) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.mDrive);
    leftDriveSource = new EncoderPIDSource(Robot.mDrive.leftQuadEncoder, PIDSourceType.kDisplacement);
    leftSpeedOutput = new SpeedOutput(); 
    leftDrivePID = new PIDController(0.0005, 0, 0, leftDriveSource, leftSpeedOutput);
    rightDriveSource = new EncoderPIDSource(Robot.mDrive.rightQuadEncoder, PIDSourceType.kDisplacement);
    rightSpeedOutput = new SpeedOutput();
    rightDrivePID = new PIDController(0.0005, 0, 0, rightDriveSource, rightSpeedOutput);
    mTargetClicks = -targetFeet * RobotMap.feetToClicks;
    mTimer = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.mDrive.resetDriveEncoders();
    leftDrivePID.setAbsoluteTolerance(40);
    leftDrivePID.setContinuous(false);
    leftDrivePID.setOutputRange(-0.5, 0.5);
    leftDrivePID.setSetpoint(mTargetClicks);
    rightDrivePID.setAbsoluteTolerance(40);
    rightDrivePID.setContinuous(false);
    rightDrivePID.setOutputRange(-0.5, 0.5);
    rightDrivePID.setSetpoint(mTargetClicks);
    SmartDashboard.putNumber("Target Clicks (L)", leftDrivePID.getSetpoint());
    leftDrivePID.enable();
    rightDrivePID.enable();
    mTimer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double lSpeed = -leftSpeedOutput.getSpeed();
    double rSpeed = rightSpeedOutput.getSpeed();
    Robot.mDrive.setSpeed(lSpeed, rSpeed);
    // SmartDashboard.putNumber("lSpeed", lSpeed);
    // SmartDashboard.putNumber("rSpeed", rSpeed);
    // SmartDashboard.putNumber("lError", leftDrivePID.getError());
    // SmartDashboard.putNumber("rError", rightDrivePID.getError());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(!leftDrivePID.onTarget() || !rightDrivePID.onTarget()) mTimer.reset();
    if(mTimer.get() > 0.35) return true;
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    leftDrivePID.disable();
    rightDrivePID.disable();
    Robot.mDrive.stopDriveMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
