package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class RobotMap {

    public DcMotor intake;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public Gamepad gamepad1;

    public IMU imu;
    public void init(HardwareMap robot) {
        intake = robot.get(DcMotor.class, "intake");
        frontLeft = robot.get(DcMotor.class, "frontLeft");
        frontRight = robot.get(DcMotor.class, "frontRight");
        backLeft = robot.get(DcMotor.class, "backLeft");
        backRight = robot.get(DcMotor.class, "backRight");
        imu = robot.get(IMU.class, "imu");
    }
}
