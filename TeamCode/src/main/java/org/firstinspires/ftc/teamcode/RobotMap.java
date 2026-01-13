package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class RobotMap {

    //maps all motors, servos, switches, all devices that we access throughout the code
    public DcMotorEx intake;
    public DcMotorEx frontLeft;
    public DcMotorEx frontRight;
    public DcMotorEx backLeft;
    public DcMotorEx backRight;
    public DcMotorEx shooterBack;
    public DcMotorEx shooterFront;
    public Gamepad gamepad1;
    public IMU imu;
    public CRServo leftindex1;
    public CRServo rightindex1;
    public CRServo index3;
    public DigitalChannel limitSwitch;
    public DigitalChannel limitSwitch2;
    public DigitalChannel limitSwitch3;
    public DcMotorEx hexIndex;


    public void init(HardwareMap robot) {
        //gets the name of the device from our tablet and configures it
        intake = robot.get(DcMotorEx.class, "intake");
        frontLeft = robot.get(DcMotorEx.class, "frontLeft");
        frontRight = robot.get(DcMotorEx.class, "frontRight");
        backLeft = robot.get(DcMotorEx.class, "backLeft");
        backRight = robot.get(DcMotorEx.class, "backRight");
        imu = robot.get(IMU.class, "imu");
        shooterBack = robot.get(DcMotorEx.class, "shooterFront");
        shooterFront = robot.get(DcMotorEx.class, "shooterBack");
        limitSwitch = robot.get(DigitalChannel.class, "limitSwitch");
        limitSwitch2 = robot.get(DigitalChannel.class, "limitSwitch2");
        limitSwitch3 = robot.get(DigitalChannel.class, "limitSwitch3");
        leftindex1 = robot.get(CRServo.class, "leftindex1");
        rightindex1 = robot.get(CRServo.class, "rightindex1");
        index3 = robot.get(CRServo.class, "index3");
        hexIndex = robot.get(DcMotorEx.class, "hexIndex");
    }
}
