package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.RobotMap;
public class LimitSwitchReset {

    private RobotMap robot;
    private LimitSwitch limitSwitch;
    private LimitSwitch2 limitSwitch2;
    private LimitSwitch3 limitSwitch3;

    public LimitSwitchReset (RobotMap robot, LimitSwitch limitSwitch, LimitSwitch2 limitSwitch2, LimitSwitch3 limitSwitch3){
        this.robot = robot;
        this.limitSwitch = limitSwitch;
        this.limitSwitch2 = limitSwitch2;
        this.limitSwitch3 = limitSwitch3;
    }

    public void resetLimitSwitches(){
        limitSwitch.reset_value();
        limitSwitch2.reset_value();
        limitSwitch3.reset_value();
    }
}
