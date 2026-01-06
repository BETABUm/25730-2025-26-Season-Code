package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotMap;

public class DriveTrainSystemAuto {
        RobotMap robot;
        public DriveTrainSystemAuto(RobotMap robot) {

            this.robot = robot;

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

        // drivetrain math from last year
        public void Drive(double velocityFL, double velocityBL, double velocityFR, double velocityBR, double inches) {
            robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            double wheelDiameter = 2.95276;
            double wheelCircumference = wheelDiameter * Math.PI;
            double ticksPerRev = robot.frontLeft.getMotorType().getTicksPerRev();
            double revs = inches/wheelCircumference;
            int targetPos = (int)(revs * ticksPerRev);

            robot.frontLeft.setTargetPosition(targetPos);
            robot.backLeft.setTargetPosition(targetPos);
            robot.frontRight.setTargetPosition(targetPos);
            robot.backRight.setTargetPosition(targetPos);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.frontLeft.setVelocity(velocityFL);
            robot.backLeft.setVelocity(velocityBL);
            robot.frontRight.setVelocity(velocityFR);
            robot.backRight.setVelocity(velocityBR);

        }
}
