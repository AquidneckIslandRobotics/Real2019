/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Add your docs here.
 */
public class SpeedOutput implements PIDOutput {
    double speed; 

    public void pidWrite(double SpeedOutput) {
        speed = SpeedOutput; 
    }

    public double getSpeed() {
        return speed; 
    }

}
