/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RunIntake extends Command {

  private XboxController mStick;
  private int mAxis;
  private boolean mOuttake;

  public RunIntake(XboxController stick, int axis, boolean outtake) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    mStick = stick;
    mAxis = axis;
    mOuttake = outtake;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.mIntake.isRunning = true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = Math.abs(mStick.getRawAxis(mAxis));
    if(speed < 0.3) speed = 0;
    if(mOuttake) Robot.mIntake.setIntake(-speed);
    else Robot.mIntake.setIntake(speed);
    SmartDashboard.putNumber("Intake Speed", speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mIntake.stopIntake();
    Robot.mIntake.isRunning = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
