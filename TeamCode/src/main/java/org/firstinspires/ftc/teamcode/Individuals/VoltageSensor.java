package org.firstinspires.ftc.teamcode.Individuals;

import org.firstinspires.ftc.teamcode.RobotMap;

public class VoltageSensor {

    private RobotMap robot;

    public VoltageSensor(RobotMap robot){
        this.robot = robot;
    }

    public double getVoltage(){
        return robot.voltageSensor.getVoltage();
    }
}
