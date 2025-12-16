package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class RobotMap {

    public DcMotor intake;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor shooterBack;
    public DcMotor shooterFront;
    public Gamepad gamepad1;
    public IMU imu;

    public DigitalChannel limitSwitch;

    public void init(HardwareMap robot) {
        intake = robot.get(DcMotor.class, "intake");
        frontLeft = robot.get(DcMotor.class, "frontLeft");
        frontRight = robot.get(DcMotor.class, "frontRight");
        backLeft = robot.get(DcMotor.class, "backLeft");
        backRight = robot.get(DcMotor.class, "backRight");
        imu = robot.get(IMU.class, "imu");
        shooterBack = robot.get(DcMotor.class, "shooterFront");
        shooterFront = robot.get(DcMotor.class, "shooterBack");
        limitSwitch = robot.get(DigitalChannel.class, "limitSwitch");
    }
}
