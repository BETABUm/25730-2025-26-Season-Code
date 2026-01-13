package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotMap;

public class DriveTrainSystemAuto {
        RobotMap robot;
        public DriveTrainSystemAuto(RobotMap robot) {

            this.robot = robot;

        }


        public void setDriveValues(double powerFL, double powerBL, double powerFR, double powerBR, double inches) {
                double wheelDiameter = 2.95276;
                double wheelCircumference = wheelDiameter * Math.PI;
                double ticksPerRev = robot.frontLeft.getMotorType().getTicksPerRev() * 20;
                double revs = inches / wheelCircumference;
                int targetPos = (int) (revs * ticksPerRev);

                robot.frontLeft.setTargetPosition(targetPos);
                robot.backLeft.setTargetPosition(targetPos);
                robot.frontRight.setTargetPosition(targetPos);
                robot.backRight.setTargetPosition(targetPos);

                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                robot.frontLeft.setPower(powerFL);
                robot.backLeft.setPower(powerBL);
                robot.frontRight.setPower(powerFR);
                robot.backRight.setPower(powerBR);

        }

        public boolean checkDriving() {
                return ((robot.backLeft.getCurrentPosition() <= robot.backLeft.getTargetPosition() + 100)
                        && (robot.backLeft.getCurrentPosition() > robot.backLeft.getTargetPosition() - 100)
                        && (robot.frontLeft.getCurrentPosition() <= robot.frontLeft.getTargetPosition() + 100)
                        && (robot.frontLeft.getCurrentPosition() <= robot.frontLeft.getTargetPosition() + 100)
                        && (robot.backRight.getCurrentPosition() <= robot.backRight.getTargetPosition() + 100)
                        && (robot.backRight.getCurrentPosition() <= robot.backRight.getTargetPosition() + 100)
                        && (robot.frontRight.getCurrentPosition() <= robot.frontRight.getTargetPosition() + 100)
                        && (robot.frontRight.getCurrentPosition() <= robot.frontRight.getTargetPosition() + 100));
        }
        public void stop(){
            robot.frontLeft.setPower(0);
            robot.backLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);
        }
}
