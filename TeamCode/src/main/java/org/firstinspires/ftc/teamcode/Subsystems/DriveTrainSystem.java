package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.RobotMap;

public class DriveTrainSystem {
    private RobotMap robot;
    private Gamepad gamepad1;
    private double sigmamode;
    public DriveTrainSystem(RobotMap robot) {

        this.robot = robot;
        gamepad1 = new Gamepad();

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    // drivetrain math from last year
    public void Drive (double right_stick_x, double left_stick_y, double left_stick_x, boolean right_bumper, boolean left_bumper) {

        if(right_bumper){
            sigmamode = .345;
        } else if(left_bumper){
            sigmamode = 1;
        } else {
            sigmamode = .80;
        }

        double rotate = right_stick_x;
        double forward = left_stick_y;
        double strafe = left_stick_x;

        double theta = Math.atan2(forward, strafe);
        double magnitude = Math.sqrt((forward * forward) + (strafe * strafe));

        double cos = Math.cos(theta - Math.PI / 4);
        double sin = Math.sin(theta - Math.PI / 4);

        double maxPower = Math.max(1.0, Math.max(Math.abs(cos), Math.abs(sin)));

        double frontLeft = (magnitude * cos + rotate) / maxPower;
        double backLeft  = (magnitude * sin + rotate) / maxPower;
        double frontRight = (magnitude * sin - rotate) / maxPower;
        double backRight  = (magnitude * cos - rotate) / maxPower;

        frontLeft *= sigmamode;
        backLeft *= sigmamode;
        frontRight *= sigmamode;
        backRight *= sigmamode;

        robot.frontLeft.setPower(frontLeft);
        robot.backLeft.setPower(-backLeft);
        robot.frontRight.setPower(frontRight);
        robot.backRight.setPower(backRight);

    }


    public double sigmamode(){
        return sigmamode;
    }
}
