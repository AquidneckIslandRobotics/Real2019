/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UpdateIntakeState;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public boolean isDeployed = false;
  public boolean isRunning = false;
  public boolean hasCargo = false;
  public boolean isIntaking = false;
  public double intakeSpeed = 0;

  public TalonSRX intake = new TalonSRX(RobotMap.intakeMotor);
  public DoubleSolenoid intakeActuator = new DoubleSolenoid(RobotMap.intakeForwardChannel, RobotMap.intakeReverseChannel);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new UpdateIntakeState());
  }

  public void initIntakeController() {
    intake.setNeutralMode(NeutralMode.Brake);
    intake.setInverted(true);
  }

  public void setIntake(double speed) {
    intake.set(ControlMode.PercentOutput, speed);
  }

  public void stopIntake() {
    setIntake(0);
  }

  public void deployIntake() {
    intakeActuator.set(Value.kReverse);
  }

  public void retractIntake() {
    intakeActuator.set(Value.kForward);
  }

}
