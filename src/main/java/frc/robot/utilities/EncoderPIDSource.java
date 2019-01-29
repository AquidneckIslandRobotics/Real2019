/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class EncoderPIDSource implements PIDSource {

    private Encoder mEncoder;
    private PIDSourceType mSourceType;

    public EncoderPIDSource(Encoder encoder, PIDSourceType sourceType) {
        mEncoder = encoder;
        mSourceType = sourceType; 
    }

    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement; 
    }

    public double pidGet() {
        return mEncoder.getDistance(); 
    }

    public void setPIDSourceType(PIDSourceType sourceType) {
        mSourceType = sourceType;
    }


}
