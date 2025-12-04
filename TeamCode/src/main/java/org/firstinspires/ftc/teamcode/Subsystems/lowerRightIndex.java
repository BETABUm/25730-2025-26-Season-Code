package org.firstinspires.ftc.teamcode.Subsystems;
import android.graphics.Path;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.RobotMap;

public class lowerRightIndex{

    private RobotMap robot;

    public lowerRightIndex(RobotMap robot){
        this.robot = robot;
    }

    public void setPower(double power){
        robot.lowerRightIndex.setPower(power);
    }

    public void setPowerDirection(double power, DcMotorSimple.Direction direction){
        robot.lowerRightIndex.setDirection(direction);
        robot.lowerRightIndex.setPower(power);
    }

}
