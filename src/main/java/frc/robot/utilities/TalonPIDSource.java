/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class TalonPIDSource implements PIDSource {

    private TalonSRX mTalon;
    private PIDSourceType mSourceType;
    private int mSensorNumber;

    public TalonPIDSource(TalonSRX talon) {
        mTalon = talon;
        mSourceType = PIDSourceType.kDisplacement;
        mSensorNumber = 0;
    }

    public PIDSourceType getPIDSourceType() {
        return mSourceType;
    }

    public double pidGet() {
        return mTalon.getSelectedSensorPosition(mSensorNumber);
    }

    public void setPIDSourceType(PIDSourceType sourceType) {
        mSourceType = sourceType;
    }

}
