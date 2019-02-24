/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UpdateSkisState;

/**
 * Add your docs here.
 */
public class Skis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public boolean isDeployed = false;
  public DoubleSolenoid skisActuator = new DoubleSolenoid(RobotMap.skisForwardChannel, RobotMap.skisReverseChannel);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new UpdateSkisState());
  }

  public void deploySkis() {
    skisActuator.set(Value.kForward);
  }

  public void retractSkis() {
    //---------REDACTED--------
  }

}
