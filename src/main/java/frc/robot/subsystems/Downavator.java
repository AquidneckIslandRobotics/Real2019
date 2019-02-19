/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Downavator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public TalonSRX downavatorLeader = new TalonSRX(RobotMap.downavatorLeader);
  public TalonSRX downavatorFollower = new TalonSRX(RobotMap.downavatorFollower);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void initDownavatorControllers() {
    downavatorFollower.follow(downavatorLeader);

    downavatorLeader.setNeutralMode(NeutralMode.Brake);
    downavatorFollower.setNeutralMode(NeutralMode.Brake);

    downavatorLeader.setInverted(true);
    downavatorFollower.setInverted(true);

    downavatorLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
  }

  public void setDownavator(double speed) {
    downavatorLeader.set(ControlMode.PercentOutput, speed);
  }

  public void stopDownavator() {
    setDownavator(0);
  }

  public double getDownavatorEncoder() {
    return downavatorLeader.getSelectedSensorPosition();
  }

  public void resetDownavatorEncoder() {
    downavatorLeader.setSelectedSensorPosition(0);
  }

}
