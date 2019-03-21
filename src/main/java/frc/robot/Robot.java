/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Downavator;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Skis;
import frc.robot.subsystems.StupidDrive;
import frc.robot.subsystems.Vision;

import frc.robot.commands.autos.CenterAuto;
import frc.robot.commands.autos.RightRocket;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static StupidDrive mDrive = new StupidDrive();
  public static Vision mVision = new Vision();
  public static Elevator mElevator = new Elevator();
  public static Downavator mDownavator = new Downavator();
  public static Intake mIntake = new Intake();
  public static Hatch mHatch = new Hatch();
  public static Skis mSkis = new Skis();
  public static OI m_oi;
  public static Compressor mCompressor = new Compressor();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    // mDrive = new StupidDrive();
    mDrive.initDriveControllers();
    mDrive.resetDriveEncoders();
    mDrive.resetGyro();
    mElevator.initElevatorController();
    mElevator.resetElevatorEncoder();
    mDownavator.initDownavatorControllers();
    mDownavator.resetDownavatorEncoder();
    mIntake.initIntakeController();
    
    m_chooser.addOption("Center Auto", new CenterAuto());
    m_chooser.addOption("Right Rocket", new RightRocket());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    mSkis.isDeployed = false;
    mDrive.initDriveControllers();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
    SmartDashboard.putNumber("Speed", m_oi.getSpeed());
    SmartDashboard.putNumber("Rotation", m_oi.getRotation());
    SmartDashboard.putBoolean("Quickturn", m_oi.getQuickTurn());
    SmartDashboard.putNumber("Right Quad Encoder", mDrive.getRightEncoder()); 
    SmartDashboard.putNumber("Left Quad Encoder", mDrive.getLeftEncoder()); 
    SmartDashboard.putData("Drive", mDrive);
    SmartDashboard.putNumber("Angle", mDrive.gyro.getAngle());
    SmartDashboard.putNumber("pidGet() Gyro", mDrive.gyro.pidGet());
    SmartDashboard.putBoolean("drivingForwards", mDrive.drivingForwards);
    SmartDashboard.putNumber("Vision X", mVision.getAverageX());
    SmartDashboard.putNumber("Vision Angle", mVision.getVisionAngle());
    SmartDashboard.putNumber("Downa Encoder", mDownavator.getDownavatorEncoder());
    SmartDashboard.putNumber("Ele Encoder", mElevator.getElevatorEncoder());
    SmartDashboard.putBoolean("Ele CanLower", mElevator.canLower());
    SmartDashboard.putBoolean("Ele CanRaise", mElevator.canRaise());
    SmartDashboard.putBoolean("Driving Fast", m_oi.drivingFast());

    if(!mElevator.canLower()) mElevator.resetElevatorEncoder();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
