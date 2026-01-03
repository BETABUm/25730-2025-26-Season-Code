package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Servo2 {
    private RobotMap robot;

    public Servo2(RobotMap robot){
        this.robot = robot;
    }

    public void setPower(double power){
        robot.index2.setPower(power);
        robot.index2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void stop(){
        robot.index2.setPower(0);
    }
}
