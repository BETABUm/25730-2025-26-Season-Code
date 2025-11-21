package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotMap {

    public DcMotor intake = null;

    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "intake");
    }
}
