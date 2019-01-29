/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.CheesyDrive;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax leftLeader = new CANSparkMax(RobotMap.leftLeader, MotorType.kBrushed);
  CANSparkMax leftFollower1 = new CANSparkMax(RobotMap.leftFollower1, MotorType.kBrushed);
  CANSparkMax leftFollower2 = new CANSparkMax(RobotMap.leftFollower2, MotorType.kBrushed);
  CANSparkMax rightLeader = new CANSparkMax(RobotMap.rightLeader, MotorType.kBrushed);
  CANSparkMax rightFollower1 = new CANSparkMax(RobotMap.rightFollower1, MotorType.kBrushed);
  CANSparkMax rightFollower2 = new CANSparkMax(RobotMap.rightFollower2, MotorType.kBrushed);

  Encoder rightQuadEncoder = new Encoder(0, 1); 


  // VictorSP leftFront = new VictorSP(RobotMap.leftFront);
  // VictorSP leftMid = new VictorSP(RobotMap.leftMid);
  // VictorSP leftBack = new VictorSP(RobotMap.leftBack);
  // VictorSP rightFront = new VictorSP(RobotMap.rightFront);
  // VictorSP rightMid = new VictorSP(RobotMap.rightMid);
  // VictorSP rightBack = new VictorSP(RobotMap.rightBack);

  // SpeedControllerGroup leftControllerGroup = new SpeedControllerGroup(leftFront, leftMid, leftBack);
  // SpeedControllerGroup rightControllerGroup = new SpeedControllerGroup(rightFront, rightMid, rightBack);

  DifferentialDrive diffDrive = new DifferentialDrive(leftLeader, rightLeader);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CheesyDrive());
  }

  public void initDriveControllers() {
    //Set followers
    leftFollower1.follow(leftLeader);
    leftFollower2.follow(leftLeader);
    rightFollower1.follow(rightLeader);
    rightFollower2.follow(rightLeader);

    //Set to coast
    leftLeader.setIdleMode(IdleMode.kCoast);
    leftFollower1.setIdleMode(IdleMode.kCoast);
    leftFollower2.setIdleMode(IdleMode.kCoast);
    rightLeader.setIdleMode(IdleMode.kCoast);
    rightFollower1.setIdleMode(IdleMode.kCoast);
    rightFollower2.setIdleMode(IdleMode.kCoast);

    rightQuadEncoder.reset();

  }

  public void cheesyDrive() {
    diffDrive.curvatureDrive(Robot.m_oi.getSpeed(), Robot.m_oi.getRotation(), Robot.m_oi.getQuickTurn());
  }

  public void stopDriveMotors() {
    leftLeader.set(0);
    rightLeader.set(0);
  }

  public void setSpeed(double left, double right) {
    leftLeader.set(left);
    rightLeader.set(right);
  }
  public double getRightEncoder() {
    return rightQuadEncoder.getDistance(); 
  }
}
