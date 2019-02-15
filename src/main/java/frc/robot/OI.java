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
import frc.robot.commands.SetElevatorPID;
import frc.robot.commands.ToggleDriveOrientation;
import frc.robot.commands.TurnAbsolute;
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

  public OI() {
    //Driver Controls
    driverA.whenPressed(new DriveDistance(5));
    driverB.whenPressed(new CheesyDrive());
    driverX.whenPressed(new TurnAbsolute(45));
    driverLB.whenPressed(new ToggleDriveOrientation());

    //Manipulator Controls
    manipulatorA.whileHeld(new SetElevatorPID(RobotMap.lowRocket));
    manipulatorB.whileHeld(new SetElevatorPID(RobotMap.midRocket));
    manipulatorX.whileHeld(new SetElevatorPID(RobotMap.cargoShip));
    manipulatorY.whileHeld(new SetElevatorPID(RobotMap.highRocket));
    
  }

  public double getSpeed() {
    double speed = driverStick.getY(Hand.kLeft);
    if(Math.abs(speed) < 0.05) return 0;
    else return speed;
  }

  public double getRotation() {
    double rotation = -driverStick.getX(Hand.kRight);
    if(Math.abs(rotation) < 0.05) return 0;
    else return rotation;
  }

  public boolean getQuickTurn() {
    return driverRB.get();
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
