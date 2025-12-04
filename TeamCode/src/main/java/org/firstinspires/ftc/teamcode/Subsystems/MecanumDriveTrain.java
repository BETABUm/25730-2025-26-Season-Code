package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.RobotMap;

public class MecanumDriveTrain {
    private RobotMap robot;
    public MecanumDriveTrain (RobotMap robot) {
        this.robot = robot;

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    RevHubOrientationOnRobot revOrientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

    public void Drive (Gamepad gamepad1) {

        double rotate = gamepad1.right_stick_x;
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;

        double theta = Math.atan2(forward, strafe);
        double magnitude = Math.sqrt((forward * forward) + (strafe * strafe));

        double cos = Math.cos(theta - Math.PI / 4);
        double sin = Math.sin(theta - Math.PI / 4);

        double maxPower = Math.max(Math.abs(cos), Math.abs(sin));

        double frontLeft = (magnitude * cos + rotate) / maxPower;
        double backLeft  = (magnitude * sin + rotate) / maxPower;
        double frontRight = (magnitude * sin - rotate) / maxPower;
        double backRight  = (magnitude * cos - rotate) / maxPower;

        robot.frontLeft.setPower(frontLeft);
        robot.backLeft.setPower(backLeft);
        robot.frontRight.setPower(frontRight);
        robot.backRight.setPower(backRight);

    }
}
