package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotMap;

public class DriveTrainSystem {
    private RobotMap robot;
    public DriveTrainSystem(RobotMap robot) {

        this.robot = robot;

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    //orientation of IMU (gyro) in control hub for field orientated drive
    RevHubOrientationOnRobot revOrientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

    // drivetrain math from last year
    public void Drive (double right_stick_x, double left_stick_y, double left_stick_x) {

        double rotate = Math.pow(right_stick_x, 5.0);
        double forward = Math.pow(left_stick_y, 5.0);
        double strafe = Math.pow(left_stick_x, 5.0);

        double theta = Math.atan2(forward, strafe);
        double magnitude = Math.sqrt((forward * forward) + (strafe * strafe));

        double cos = Math.cos(theta - Math.PI / 4);
        double sin = Math.sin(theta - Math.PI / 4);

        double maxPower = Math.max(Math.abs(cos), Math.abs(sin));

        double frontLeft = (magnitude * cos + rotate * .7) / maxPower;
        double backLeft  = (magnitude * sin + rotate * .7) / maxPower;
        double frontRight = (magnitude * sin - rotate * .7) / maxPower;
        double backRight  = (magnitude * cos - rotate * .7) / maxPower;

        robot.frontLeft.setPower(frontLeft);
        robot.backLeft.setPower(-backLeft);
        robot.frontRight.setPower(frontRight);
        robot.backRight.setPower(backRight);

    }
}
