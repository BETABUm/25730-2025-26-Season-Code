package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.RobotMap;
public class LimitSwitchReset {

    private RobotMap robot;
    private LimitSwitch limitSwitch;
    private LimitSwitch2 limitSwitch2;
    private LimitSwitch3 limitSwitch3;

    public LimitSwitchReset (RobotMap robot){
        this.robot = robot;
        limitSwitch = new LimitSwitch(robot);
        limitSwitch2 = new LimitSwitch2(robot);
        limitSwitch3 = new LimitSwitch3(robot);
    }

    public void resetLimitSwitches(){
        limitSwitch.reset_value();
        limitSwitch2.reset_value();
        limitSwitch3.reset_value();
    }
}
