package org.firstinspires.ftc.teamcode.Individuals;

import org.firstinspires.ftc.teamcode.RobotMap;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class LowServo {
    private RobotMap robot;

    public LowServo(RobotMap robot){

        this.robot = robot;

    }

    public void setPower(double powerLeft, double powerRight){

        robot.leftindex1.setPower(powerLeft);
        robot.rightindex1.setPower(powerRight);
        robot.rightindex1.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}

