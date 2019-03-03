/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoIntake extends Command {
  private Timer mTimer;
  public AutoIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    mTimer = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.mIntake.isDeployed = true;
    Robot.mIntake.isIntaking = true;
    mTimer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.mIntake.setIntake(1);
    if(!Robot.mIntake.hasCargo && Robot.mIntake.intake.getOutputCurrent() > 30 && mTimer.get() > 0.5)
      Robot.mIntake.hasCargo = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.mIntake.hasCargo;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mIntake.isDeployed = false;
    Robot.mIntake.isIntaking = false;
    Robot.mIntake.stopIntake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
