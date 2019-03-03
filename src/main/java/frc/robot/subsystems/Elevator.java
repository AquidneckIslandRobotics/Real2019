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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManualElevatorControl;
import frc.robot.commands.UpdateElevatorState;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public TalonSRX elevator = new TalonSRX(RobotMap.elevatorMotor);
  public DigitalInput lowerLimitSwitch = new DigitalInput(RobotMap.elevatorLowerLimitSwitch);
  public DigitalInput upperLimitSwitch = new DigitalInput(RobotMap.elevatorUpperLimitSwitch);
  public enum Preset {
    INTAKE, CARGOSHIP, LOWROCKET, MIDROCKET, HIGHROCKET;
  }
  public Preset currentPreset = Preset.INTAKE;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ManualElevatorControl());
    // setDefaultCommand(new UpdateElevatorState());
  }

  public void initElevatorController() {
    elevator.setNeutralMode(NeutralMode.Brake);
    elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    elevator.setInverted(true);
  }

  public void setElevator(double speed) {
    if(speed > 0 && canRaise()) elevator.set(ControlMode.PercentOutput, speed);
    else if(speed < 0 && canLower()) elevator.set(ControlMode.PercentOutput, speed);
    else elevator.set(ControlMode.PercentOutput, 0);
    // elevator.set(ControlMode.PercentOutput, speed);
  }

  public void stopElevator() {
    setElevator(0);
  }

  public double getElevatorEncoder() {
    return elevator.getSelectedSensorPosition(0);
  }

  public void resetElevatorEncoder() {
    elevator.setSelectedSensorPosition(0);
  }

  public boolean canLower() {
    return lowerLimitSwitch.get();
  }

  public boolean canRaise() {
    return upperLimitSwitch.get();
  }
}
