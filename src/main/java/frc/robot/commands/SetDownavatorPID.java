/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.utilities.SpeedOutput;
import frc.robot.utilities.TalonPIDSource;

public class SetDownavatorPID extends Command {

  public PIDController downavatorController;
  public TalonPIDSource downavatorSource;
  public SpeedOutput downavatorOutput;
  private double mTarget;
  private double mMaxSpeed;
  
  public SetDownavatorPID(double target) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.mDownavator);
    downavatorSource = new TalonPIDSource(Robot.mDownavator.downavatorLeader);
    downavatorOutput = new SpeedOutput();
    downavatorController = new PIDController(0.00014, 0.0, 0.0, downavatorSource, downavatorOutput);
    mTarget = target;
    mMaxSpeed = 1;
    downavatorController.setOutputRange(-mMaxSpeed, mMaxSpeed);
  }

  public SetDownavatorPID(double target, double maxSpeed) {
    requires(Robot.mDownavator);
    downavatorSource = new TalonPIDSource(Robot.mDownavator.downavatorLeader);
    downavatorOutput = new SpeedOutput();
    downavatorController = new PIDController(0.00014, 0.0, 0.0, downavatorSource, downavatorOutput);
    mTarget = target;
    mMaxSpeed = maxSpeed;
    downavatorController.setOutputRange(-mMaxSpeed, mMaxSpeed);
  }

  public SetDownavatorPID(double target, boolean toLevel) {
    requires(Robot.mDownavator);
    downavatorSource = new TalonPIDSource(Robot.mDownavator.downavatorLeader);
    downavatorOutput = new SpeedOutput();
    downavatorController = new PIDController(0.00014, 0.0, 0.0, downavatorSource, downavatorOutput);
    mTarget = target;
    downavatorController.setOutputRange(-1, 0);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    downavatorController.setAbsoluteTolerance(100); //~.25"
    downavatorController.setContinuous(false);
    downavatorController.setSetpoint(mTarget);
    downavatorController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Downa Speed Out", downavatorOutput.getSpeed());
    Robot.mDownavator.setDownavator(downavatorOutput.getSpeed());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mDownavator.stopDownavator();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
