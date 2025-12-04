package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Intake {
    private RobotMap robot;
    public Intake (RobotMap robot){
        this.robot = robot;
    }

    public void set_power(double power){
        robot.intake.setPower(power);
    }

    public void set_power_pos(double power, int position){
        robot.intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.intake.setPower(power);
        robot.intake.setTargetPosition(position);
    }

    public void stop(){
        robot.intake.setPower(0);
    }

    public double get_encoder(){
        int intake_encoder = robot.intake.getCurrentPosition();
        double ticksPerRev = robot.intake.getMotorType().getTicksPerRev();
        return robot.intake.getCurrentPosition() / ticksPerRev; //* gear ratio;
    }



}
