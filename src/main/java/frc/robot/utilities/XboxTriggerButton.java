/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;


/**
 * Add your docs here.
 */
public class XboxTriggerButton extends Button {

    XboxController mController;
    Hand mHand;
    double mDeadzone;

    public XboxTriggerButton(XboxController controller, Hand hand, double deadzone) {
        mController = controller;
        mHand = hand;
        mDeadzone = deadzone;
    }

    public boolean get() {
        return (Math.abs(mController.getTriggerAxis(mHand)) > mDeadzone);
    }

    public double getTrigger() {
        return mController.getTriggerAxis(mHand);
    }

}
