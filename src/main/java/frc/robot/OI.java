/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoIntake;
import frc.robot.commands.CheesyDrive;
import frc.robot.commands.DeploySkis;
import frc.robot.commands.DriveConstant;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ExtendHatch;
import frc.robot.commands.FalconHatchGrabExtend;
import frc.robot.commands.FalconHatchGrabRetract;
import frc.robot.commands.FalconHatchPlaceExtend;
import frc.robot.commands.FalconHatchPlaceRetract;
import frc.robot.commands.ManualDownavatorControl;
import frc.robot.commands.SetDownavatorPID;
import frc.robot.commands.SetDriveToBrake;
import frc.robot.commands.SetElevatorPID;
import frc.robot.commands.SetElevatorPosition;
import frc.robot.commands.ToggleDriveOrientation;
import frc.robot.commands.ToggleHatchExtend;
import frc.robot.commands.ToggleHatchGrip;
import frc.robot.commands.ToggleIntakeDeploy;
import frc.robot.commands.TurnAbsolute;
import frc.robot.commands.Unyeet;
import frc.robot.commands.Yeet;
import frc.robot.subsystems.Elevator.Preset;
import frc.robot.commands.ManualDriveControl;
import frc.robot.commands.RetractHatch;
import frc.robot.commands.RunIntake;
import frc.robot.utilities.XboxTriggerButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  XboxController driverStick = new XboxController(0);
  Button driverA = new JoystickButton(driverStick, 1);
  Button driverB = new JoystickButton(driverStick, 2);
  Button driverX = new JoystickButton(driverStick, 3);
  Button driverY = new JoystickButton(driverStick, 4);
  Button driverLB = new JoystickButton(driverStick, 5);
  Button driverRB = new JoystickButton(driverStick, 6);
  Button driverBack = new JoystickButton(driverStick, 7);
  Button driverLT = new XboxTriggerButton(driverStick, Hand.kLeft, RobotMap.triggerDeadzone);
  Button driverRT = new XboxTriggerButton(driverStick, Hand.kRight, RobotMap.triggerDeadzone);

  XboxController manipulatorStick = new XboxController(1);
  Button manipulatorA = new JoystickButton(manipulatorStick, 1);
  Button manipulatorB = new JoystickButton(manipulatorStick, 2);
  Button manipulatorX = new JoystickButton(manipulatorStick, 3);
  Button manipulatorY = new JoystickButton(manipulatorStick, 4);
  Button manipulatorLB = new JoystickButton(manipulatorStick, 5);
  Button manipulatorRB = new JoystickButton(manipulatorStick, 6);
  Button manipulatorBack = new JoystickButton(manipulatorStick, 7);
  Button manipulatorLT = new XboxTriggerButton(manipulatorStick, Hand.kLeft, RobotMap.triggerDeadzone);
  Button manipulatorRT = new XboxTriggerButton(manipulatorStick, Hand.kRight, RobotMap.triggerDeadzone);

  XboxController endgameStick = new XboxController(2);
  Button endgameA = new JoystickButton(endgameStick, 1);
  Button endgameB = new JoystickButton(endgameStick, 2);
  Button endgameX = new JoystickButton(endgameStick, 3);
  Button endgameY = new JoystickButton(endgameStick, 4);
  Button endgameLB = new JoystickButton(endgameStick, 5);
  Button endgameRB = new JoystickButton(endgameStick, 6);
  Button endgameBack = new JoystickButton(endgameStick, 7);

  public OI() {
    //Driver Controls
    // driverA.whenPressed(new DriveDistance(5));
    // driverB.whenPressed(new CheesyDrive());
    // driverX.whenPressed(new TurnAbsolute(45));
    driverLB.whenPressed(new ToggleDriveOrientation());
    driverA.whenPressed(new ToggleHatchExtend());
    driverB.whenPressed(new ToggleHatchGrip());
    driverX.whileHeld(new ManualDriveControl(0.25));
    driverX.whenReleased(new SetDriveToBrake());
    driverY.whenPressed(new Yeet());
    driverY.whenReleased(new Unyeet());
    driverBack.whileHeld(new DriveConstant());
    driverRT.whenPressed(new ToggleHatchGrip());
    driverLT.whileHeld(new RunIntake(driverStick, 2, true));
    //Manipulator Controls
    manipulatorA.whileHeld(new SetElevatorPID(RobotMap.lowRocket));
    manipulatorA.whenReleased(new SetElevatorPID(RobotMap.intake));
    manipulatorB.whileHeld(new SetElevatorPID(RobotMap.midRocket));
    manipulatorB.whenReleased(new SetElevatorPID(RobotMap.intake));
    manipulatorX.whileHeld(new SetElevatorPID(RobotMap.cargoShip));
    manipulatorX.whenReleased(new SetElevatorPID(RobotMap.intake));
    manipulatorY.whileHeld(new SetElevatorPID(RobotMap.highRocket));
    manipulatorY.whenReleased(new SetElevatorPID(RobotMap.intake));
    // manipulatorA.whenPressed(new SetElevatorPosition(Preset.LOWROCKET));
    // manipulatorA.whenReleased(new SetElevatorPosition(Preset.INTAKE));
    // manipulatorB.whenPressed(new SetElevatorPosition(Preset.MIDROCKET));
    // manipulatorB.whenReleased(new SetElevatorPosition(Preset.INTAKE));
    // manipulatorX.whenPressed(new SetElevatorPosition(Preset.CARGOSHIP));
    // manipulatorX.whenReleased(new SetElevatorPosition(Preset.INTAKE));
    // manipulatorY.whenPressed(new SetElevatorPosition(Preset.HIGHROCKET));
    // manipulatorY.whenReleased(new SetElevatorPosition(Preset.INTAKE));
    
    // manipulatorLB.whenPressed(new ExtendHatch());
    // manipulatorLB.whenReleased(new RetractHatch());
    // manipulatorRB.whenPressed(new ToggleHatchGrip());
    manipulatorLB.whenPressed(new FalconHatchGrabExtend());
    manipulatorLB.whenReleased(new FalconHatchGrabRetract());
    manipulatorRB.whenPressed(new FalconHatchPlaceExtend());
    manipulatorRB.whenReleased(new FalconHatchPlaceRetract());

    // manipulatorLT.whenPressed(new ToggleIntakeDeploy());
    manipulatorLT.whileHeld(new RunIntake(manipulatorStick, 2, false));
    // manipulatorRT.whileHeld(new RunIntake(manipulatorStick, 3, false));
    manipulatorRT.whenPressed(new AutoIntake());
    // manipulatorBack.whenPressed(new DeploySkis()); Does not exist yet

    // endgameA.whileHeld(new ManualDownavatorControl(-0.25)); //-0.65 works well
    // endgameB.whileHeld(new SetDownavatorPID(-51000, 0.25));
    // endgameX.whileHeld(new SetDownavatorPID(-58000)); //-51000 for just over level, -61177 for top level
    // endgameY.whileHeld(new ManualDownavatorControl(1)); //0.25
    // endgameLB.whileHeld(new ManualDriveControl(0.25));
    // endgameRB.whileHeld(new ManualDriveControl(-0.2));
    
    endgameA.whileHeld(new SetDownavatorPID(-39000)); //for level 1 -> 2
    // endgameB.whileHeld(new SetDownavatorPID(-Y)); for level 2 -> 3
    endgameX.whileHeld(new SetDownavatorPID(-60000)); //for level 1 -> 3 //-33207 new gearing //-58000
    endgameX.whenReleased(new SetDownavatorPID(-51000, true)); //-27635 new gearing //-51000 //-51500
    endgameY.whileHeld(new SetDownavatorPID(-3000)); //to raise (0 clicks?) //-400
    endgameRB.whileHeld(new ManualDownavatorControl(1)); //temporary manual raise
    endgameLB.whileHeld(new ManualDownavatorControl(-1));
    endgameBack.whenPressed(new DeploySkis());
    
  }

  public double getSpeed() {
    double speed = driverStick.getY(Hand.kLeft);
    if(Math.abs(speed) < 0.01) return 0;
    else if(Robot.mDrive.yeeting) {
      if(speed > 0) return Math.pow(speed, 2);
      else return -Math.pow(speed, 2);
    } else {
      if(speed > 0) return Math.pow(speed, 2) * 0.55;
      else return -Math.pow(speed, 2) * 0.55;
    }
    // else if(speed > 0) return Math.pow(speed, 2) * 0.5;
    // else return -Math.pow(speed, 2) * 0.5;
    // else return Math.pow(speed, 3) * 0.5;
  }

  public double getRotation() {
    double rotation = -driverStick.getX(Hand.kRight);
    if(Math.abs(rotation) < 0.01) return 0;
    else if(Robot.mDrive.yeeting) {
      if(rotation > 0) return Math.pow(rotation, 2) * 0.55;
      else return -Math.pow(rotation, 2) * 0.55;
    } else {
      if(rotation > 0) return Math.pow(rotation, 2) * 0.55;
      else return -Math.pow(rotation, 2) * 0.55;
    }
    // else return Math.pow(rotation, 3) * 0.5;
  }

  public boolean getQuickTurn() {
    return driverRB.get();
  }

  // public double getDriverLeftY() {
  //   double leftY = driverStick.getY(Hand.kLeft);
  //   if(Math.abs(leftY) < 0.01) return 0;
  //   else return leftY;
  // }

  // public double getDriverRightY() {
  //   double rightY = driverStick.getY(Hand.kRight);
  //   if(Math.abs(rightY) < 0.01) return 0;
  //   else return rightY;
  // }

  public double getManipulatorLeftY() {
    double leftY = -manipulatorStick.getY(Hand.kLeft);
    if(Math.abs(leftY) < 0.01) return 0;
    else return leftY;
  }

  public double getManipulatorRightY() {
    double rightY = -manipulatorStick.getY(Hand.kRight);
    if(Math.abs(rightY) < 0.01) return 0;
    else return rightY;
  }

  public boolean drivingFast() {
    return driverLB.get();
  }

  

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
