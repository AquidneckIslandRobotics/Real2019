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
import frc.robot.RobotMap;
import frc.robot.utilities.SpeedOutput;
import frc.robot.utilities.TalonPIDSource;

public class UpdateElevatorState extends Command {
  
  public TalonPIDSource elevatorEncoderSource;
  public SpeedOutput elevatorOutput;
  public PIDController elevatorController;
  private double mSetpoint;
  private double mMaxSpeed;

  public UpdateElevatorState() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.mElevator);
    elevatorEncoderSource = new TalonPIDSource(Robot.mElevator.elevator);
    elevatorOutput = new SpeedOutput();
    elevatorController = new PIDController(0.00025, 0.0, 0.00025, elevatorEncoderSource, elevatorOutput);
    mMaxSpeed = 1;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    elevatorController.setAbsoluteTolerance(40);
    elevatorController.setOutputRange(-mMaxSpeed, mMaxSpeed);
    elevatorController.setContinuous(false);
    elevatorController.setSetpoint(mSetpoint);
    elevatorController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(!Robot.mElevator.canLower()) Robot.mElevator.resetElevatorEncoder();
    
    switch(Robot.mElevator.currentPreset) {
      case INTAKE: mSetpoint = RobotMap.intake;
      break;
      case CARGOSHIP: mSetpoint = RobotMap.cargoShip;
      break;
      case LOWROCKET: mSetpoint = RobotMap.lowRocket;
      break;
      case MIDROCKET: mSetpoint = RobotMap.midRocket;
      break;
      case HIGHROCKET: mSetpoint = RobotMap.highRocket;
      break;
    }

    SmartDashboard.putNumber("", elevatorOutput.getSpeed());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
