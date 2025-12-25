package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.RobotMap;

public class LimitSwitch {
    //still understanding this line and the constructor
    //think of robot as the place to access our motors, servos, etc
    private RobotMap robot;
    public LimitSwitch(RobotMap robot){

        this.robot = robot;

    }

    public boolean get_encoder() {
        robot.limitSwitch.setMode(DigitalChannel.Mode.INPUT);
        return robot.limitSwitch.getState();
    }
}