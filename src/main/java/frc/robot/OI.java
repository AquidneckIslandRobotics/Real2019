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
import frc.robot.commands.CheesyDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ExtendHatch;
import frc.robot.commands.ManualDownavatorControl;
import frc.robot.commands.SetDownavatorPID;
import frc.robot.commands.SetElevatorPID;
import frc.robot.commands.ToggleDriveOrientation;
import frc.robot.commands.ToggleHatchExtend;
import frc.robot.commands.ToggleHatchGrip;
import frc.robot.commands.ToggleIntakeDeploy;
import frc.robot.commands.TurnAbsolute;
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

  XboxController testStick = new XboxController(2);
  Button testA = new JoystickButton(testStick, 1);
  Button testB = new JoystickButton(testStick, 2);
  Button testX = new JoystickButton(testStick, 3);
  Button testY = new JoystickButton(testStick, 4);
  Button testLB = new JoystickButton(testStick, 5);
  Button testRB = new JoystickButton(testStick, 6);

  public OI() {
    //Driver Controls
    // driverA.whenPressed(new DriveDistance(5));
    // driverB.whenPressed(new CheesyDrive());
    // driverX.whenPressed(new TurnAbsolute(45));
    driverLB.whenPressed(new ToggleDriveOrientation());
    driverA.whenPressed(new ToggleHatchExtend());
    driverB.whenPressed(new ToggleHatchGrip());
    driverX.whileHeld(new ManualDriveControl(0.6));
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
    manipulatorLB.whenPressed(new ExtendHatch());
    manipulatorLB.whenReleased(new RetractHatch());
    manipulatorRB.whenPressed(new ToggleHatchGrip());
    manipulatorLT.whenPressed(new ToggleIntakeDeploy());
    manipulatorRT.whileHeld(new RunIntake(manipulatorStick, 3, false));
    // manipulatorBack.whenPressed(new DeploySkis()); Does not exist yet

    testA.whileHeld(new ManualDownavatorControl(-0.25)); //-0.65 works well
    testB.whileHeld(new SetDownavatorPID(-51000, 0.25));
    testX.whileHeld(new SetDownavatorPID(-58000)); //-51000 for just over level, -61177 for top level
    testY.whileHeld(new ManualDownavatorControl(1)); //0.25
    testLB.whileHeld(new ManualDriveControl(0.25));
    testRB.whileHeld(new ManualDriveControl(-0.2));
    
  }

  public double getSpeed() {
    double speed = driverStick.getY(Hand.kLeft);
    if(Math.abs(speed) < 0.01) return 0;
    else if(speed > 0) return Math.pow(speed, 2);
    else return -Math.pow(speed, 2);
  }

  public double getRotation() {
    double rotation = -driverStick.getX(Hand.kRight);
    if(Math.abs(rotation) < 0.01) return 0;
    else if(rotation > 0) return Math.pow(rotation, 2);
    else return -Math.pow(rotation, 2);
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
