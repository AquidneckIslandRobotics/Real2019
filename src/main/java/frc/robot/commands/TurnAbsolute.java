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
import frc.robot.Robot;
import frc.robot.utilities.SpeedOutput;

public class TurnAbsolute extends Command {

  public PIDController turnController;
  public SpeedOutput turnOutput;
  private double mTargetDegrees;
  private Timer mTimer;

  public TurnAbsolute(double targetDegrees) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.mDrive);
    turnOutput = new SpeedOutput();
    turnController = new PIDController(0.027, 0, 0.023, Robot.mDrive.gyro, turnOutput);
    mTimer = new Timer();
    mTargetDegrees = targetDegrees;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    turnController.setAbsoluteTolerance(1.5);
    turnController.setInputRange(-180, 180);
    turnController.setContinuous(true);
    turnController.setOutputRange(-1, 1);
    turnController.setSetpoint(mTargetDegrees);
    turnController.enable();
    mTimer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = -turnOutput.getSpeed();
    Robot.mDrive.setSpeed(speed, speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(!turnController.onTarget()) mTimer.reset();
    if(mTimer.get() > 0.35) return true;
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    turnController.disable();
    Robot.mDrive.stopDriveMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
