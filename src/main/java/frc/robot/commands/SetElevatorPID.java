/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utilities.EncoderPIDSource;
import frc.robot.utilities.SpeedOutput;
import frc.robot.utilities.TalonPIDSource;

public class SetElevatorPID extends Command {

  public TalonPIDSource elevatorEncoderSource;
  public SpeedOutput elevatorOutput;
  public PIDController elevatorController;
  private double mTarget;

  public SetElevatorPID(double target) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.mElevator);
    elevatorEncoderSource = new TalonPIDSource(Robot.mElevator.elevator);
    elevatorOutput = new SpeedOutput();
    elevatorController = new PIDController(0.0, 0.0, 0.0, elevatorEncoderSource, elevatorOutput);
    mTarget = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    elevatorController.setAbsoluteTolerance(40);
    elevatorController.setOutputRange(-1, 1);
    elevatorController.setContinuous(false);
    elevatorController.setSetpoint(mTarget);
    elevatorController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = elevatorOutput.getSpeed();
    // Robot.mElevator.setElevator(speed); CHECK DIRECTION

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mElevator.stopElevator();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
