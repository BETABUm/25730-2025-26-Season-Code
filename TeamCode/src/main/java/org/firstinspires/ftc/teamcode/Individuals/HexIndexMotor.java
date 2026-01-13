package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.RobotMap;

public class HexIndexMotor {

    RobotMap robot;

    public HexIndexMotor (RobotMap robot){
        this.robot = robot;
    }

    public void setPower(double power){
        robot.hexIndex.setPower(power);
    }

    public void stop(){
        robot.hexIndex.setPower(0);
    }
}
