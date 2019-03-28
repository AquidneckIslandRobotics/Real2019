/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//TEST COMMENT
package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.CheesyDrive;

/**
 * Add your docs here.
 */
public class StupidDrive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax leftLeader = new CANSparkMax(RobotMap.leftLeader, MotorType.kBrushless);
  CANSparkMax leftFollower1 = new CANSparkMax(RobotMap.leftFollower1, MotorType.kBrushless);
  CANSparkMax leftFollower2 = new CANSparkMax(RobotMap.leftFollower2, MotorType.kBrushless);
  CANSparkMax rightLeader = new CANSparkMax(RobotMap.rightLeader, MotorType.kBrushless);
  CANSparkMax rightFollower1 = new CANSparkMax(RobotMap.rightFollower1, MotorType.kBrushless);
  CANSparkMax rightFollower2 = new CANSparkMax(RobotMap.rightFollower2, MotorType.kBrushless);

  public Encoder rightQuadEncoder = new Encoder(0, 1); 
  public Encoder leftQuadEncoder = new Encoder(2, 3);
  public AHRS gyro = new AHRS(Port.kMXP);

  public DifferentialDrive diffDrive = new DifferentialDrive(leftLeader, rightLeader);

  public boolean drivingForwards = true;
  public boolean yeeting = false;

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

    // leftLeader.setParameter(ConfigParameter.kSensorType, 1); Sets sensor type to hall effect, shouldn't be necessary

    //Set to coast
    leftLeader.setIdleMode(IdleMode.kCoast);
    leftFollower1.setIdleMode(IdleMode.kCoast);
    leftFollower2.setIdleMode(IdleMode.kCoast);
    rightLeader.setIdleMode(IdleMode.kCoast);
    rightFollower1.setIdleMode(IdleMode.kCoast);
    rightFollower2.setIdleMode(IdleMode.kCoast);

    //Limit acceleration
    leftLeader.setOpenLoopRampRate(0.5);
    rightLeader.setOpenLoopRampRate(0.5);

    //Limit current draw
    leftLeader.setSmartCurrentLimit(40);
    leftFollower1.setSmartCurrentLimit(40);
    leftFollower2.setSmartCurrentLimit(40);
    rightLeader.setSmartCurrentLimit(40);
    rightFollower1.setSmartCurrentLimit(40);
    rightFollower2.setSmartCurrentLimit(40);

  }

  public void cheesyDrive(double speedModifier) {
    diffDrive.curvatureDrive(-Robot.m_oi.getSpeed(), -Robot.m_oi.getRotation(), Robot.m_oi.getQuickTurn());
  }

  public void inverseCheesyDrive(double speedModifier) {
    diffDrive.curvatureDrive(Robot.m_oi.getSpeed(), -Robot.m_oi.getRotation(), Robot.m_oi.getQuickTurn());
  }

  public void tankDrive(double lSpeed, double rSpeed) {
    diffDrive.tankDrive(lSpeed, rSpeed);
  }

  public void arcadeDrive(double speed, double rotation) {
    diffDrive.arcadeDrive(speed, rotation);
  }

  // public void inverseTankDrive() {
  //   diffDrive.tankDrive(Robot.m_oi.getDriverLeftY(), Robot.m_oi.getDriverRightY(), true);
  // }

  public void stopDriveMotors() {
    leftLeader.set(0);
    rightLeader.set(0);
  }

  public void setBrakeMode() {
    leftLeader.setIdleMode(IdleMode.kBrake);
    leftFollower1.setIdleMode(IdleMode.kBrake);
    leftFollower2.setIdleMode(IdleMode.kBrake);
    rightLeader.setIdleMode(IdleMode.kBrake);
    rightFollower1.setIdleMode(IdleMode.kBrake);
    rightFollower2.setIdleMode(IdleMode.kBrake);
  }

  public void setSpeed(double left, double right) {
    leftLeader.set(left);
    rightLeader.set(right);
  }

  public double getRightEncoder() {
    return rightQuadEncoder.getDistance(); 
  }

  public double getLeftEncoder() {
    return leftQuadEncoder.getDistance();
  }

  public void resetDriveEncoders() {
    leftQuadEncoder.reset();
    rightQuadEncoder.reset();
  }

  public double getAngle() {
    return gyro.getAngle();
  }
  
  public void resetGyro() {
    gyro.reset();
  }
}
