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
    public CRServo leftindex1;

    public CRServo rightindex1;

    public CRServo index2;

    public CRServo index3;

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
        leftindex1 = robot.get(CRServo.class, "leftindex1");
        rightindex1 = robot.get(CRServo.class, "rightindex1");
        index2 = robot.get(CRServo.class, "index2");
        index3 = robot.get(CRServo.class, "index3");
    }
}
