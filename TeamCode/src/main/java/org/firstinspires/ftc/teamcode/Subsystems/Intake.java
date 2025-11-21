package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Intake {
    private RobotMap robot;
    public Intake (RobotMap robot){
        this.robot = robot;
    }

    public void spin_in(double speed){
        robot.intake.setPower(speed);
    }

    public void spin_stop(){
        robot.intake.setPower(0);
    }

}
