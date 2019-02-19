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
import frc.robot.commands.UpdateHatchState;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public boolean isExtended = false;
  public boolean isGripped = false;

  public DoubleSolenoid hatchExtender = new DoubleSolenoid(RobotMap.hatchExtenderForwardChannel, RobotMap.hatchExtenderReverseChannel);
  public DoubleSolenoid hatchGripper = new DoubleSolenoid(RobotMap.hatchGripperForwardChannel, RobotMap.hatchGripperReverseChannel);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new UpdateHatchState());
  }

  public void extendHatch() {
    hatchExtender.set(Value.kForward);
  }

  public void retractHatch() {
    hatchExtender.set(Value.kReverse);
  }

  public void gripHatch() {
    hatchGripper.set(Value.kForward);
  }

  public void releaseHatch() {
    hatchGripper.set(Value.kReverse);
  }

}