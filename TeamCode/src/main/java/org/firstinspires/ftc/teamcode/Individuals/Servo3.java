package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Servo3 {
    private RobotMap robot;

    public Servo3(RobotMap robot){
        this.robot = robot;

    }

    public void setPower(double power){
        robot.index3.setPower(power);
        robot.index3.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void stop(){
        robot.index3.setPower(0);
    }
}
