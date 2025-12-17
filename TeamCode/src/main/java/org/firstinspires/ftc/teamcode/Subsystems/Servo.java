package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.RobotMap;

import com.qualcomm.robotcore.hardware.CRServo;

public class Servo {
    private RobotMap robot;

    public Servo (RobotMap robot){

        this.robot = robot;

    }

    public void setPower(double power){

        robot.leftindex1.setPower(power);
        robot.rightindex1.setPower(power);

    }
}

