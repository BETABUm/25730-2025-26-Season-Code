package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Timer {
    
    RobotMap robot;
    ElapsedTime elapsedTime;

    public Timer(RobotMap robot){
        this.robot = robot;
        elapsedTime = new ElapsedTime();
    }

    public void reset(){
        elapsedTime.reset();
    }

    public double timer(){
        return elapsedTime.seconds();
    }
}
