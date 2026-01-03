package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.RobotMap;

public class LimitSwitch4 {
    //still understanding this line and the constructor
    //think of robot as the place to access our motors, servos, etc
    private RobotMap robot;
    int limitSwitchCount = 0;
    boolean lastState = false;
    boolean currentState;
    public LimitSwitch4(RobotMap robot){
        this.robot = robot;
    }

    public int get_value() {
        robot.limitSwitch4.setMode(DigitalChannel.Mode.INPUT);
        currentState = robot.limitSwitch4.getState();

        if(currentState && !lastState){
            limitSwitchCount++;
        }

        lastState = currentState;

        return limitSwitchCount;
    }
}