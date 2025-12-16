package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotMap;

public class limitSwitch {
    //still understanding this line and the constructor
    //think of robot as the place to access our motors, servos, etc
    private RobotMap robot;
    public limitSwitch (RobotMap robot){

        this.robot = robot;

    }

    public boolean get_encoder() {
        robot.limitSwitch.setMode(DigitalChannel.Mode.INPUT);
        return robot.limitSwitch.getState();
    }
}