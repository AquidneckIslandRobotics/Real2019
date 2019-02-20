/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class UpdateIntakeState extends Command {
  public UpdateIntakeState() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.mIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    SmartDashboard.putNumber("Intake Current", Robot.mIntake.intake.getOutputCurrent());

    //Detect cargo
    if(Robot.mIntake.intake.getOutputCurrent() > 20) Robot.mIntake.hasCargo = true;
    else Robot.mIntake.hasCargo = false;

    //Deploy and retract as needed
    if(Robot.mIntake.isDeployed) Robot.mIntake.deployIntake();
    else Robot.mIntake.retractIntake();

    //Hold cargo as needed
    if(Robot.mIntake.hasCargo) Robot.mIntake.setIntake(0.1);

    //Run intake as needed
    if(!Robot.mIntake.hasCargo && Robot.mIntake.isIntaking) Robot.mIntake.setIntake(1);
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
