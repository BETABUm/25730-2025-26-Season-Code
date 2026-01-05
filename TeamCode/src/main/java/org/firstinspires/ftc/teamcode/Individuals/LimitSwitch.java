package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.RobotMap;

public class LimitSwitch {
    //still understanding this line and the constructor
    //think of robot as the place to access our motors, servos, etc
    private RobotMap robot;
    int limitSwitchCount = 0;
    boolean lastState = false;
    boolean currentState;
    public LimitSwitch(RobotMap robot){

        this.robot = robot;

    }

    public int get_value() {
        robot.limitSwitch.setMode(DigitalChannel.Mode.INPUT);
        currentState = robot.limitSwitch.getState();

        if(currentState && !lastState){
            limitSwitchCount++;
        }

        lastState = currentState;
        limitSwitchCount = Math.min(limitSwitchCount, 3);
        return limitSwitchCount;
    }

    public void reset_value(){
        limitSwitchCount = 0;
    }

}