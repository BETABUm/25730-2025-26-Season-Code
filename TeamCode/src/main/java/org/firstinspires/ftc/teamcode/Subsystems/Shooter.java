package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Shooter {

    private RobotMap robot;

    public Shooter (RobotMap robot){
        this.robot = robot;
    }
    public void set_power(double power){
        robot.shooterBack.setPower(power);
        robot.shooterFront.setPower(power);
    }


    // function to set power and encoder position (in ticks) of intake
    public void setPowerPos(double power, int position){
        robot.shooterBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.shooterFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.shooterBack.setPower(power);
        robot.shooterFront.setPower(power);
        robot.shooterBack.setTargetPosition(position);
        robot.shooterFront.setTargetPosition(position);
    }

    // function to get encoder values so we can access them anywhere
    public double get_encoderFront(){
        double ticksPerRevFront = robot.shooterFront.getMotorType().getTicksPerRev();
        return robot.shooterFront.getCurrentPosition() / ticksPerRevFront;
    }

    public double get_encoderBack(){
        double ticksPerRevBack = robot.shooterFront.getMotorType().getTicksPerRev();
        return robot.shooterBack.getCurrentPosition() / ticksPerRevBack;
    }

    public void setPowerRevs(double powerf,double powers, double revsFront, double revsBack){

        double ticksPerRevFront = robot.shooterFront.getMotorType().getTicksPerRev();
        double ticksPerRevBack = robot.shooterBack.getMotorType().getTicksPerRev();

        revsFront = revsFront * ticksPerRevFront;
        revsBack = revsBack * ticksPerRevBack;

        robot.shooterBack.setTargetPosition((int)revsBack);
        robot.shooterFront.setTargetPosition((int)revsFront);

        robot.shooterBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.shooterFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.shooterBack.setPower(powerf);
        robot.shooterFront.setPower(powers);

    }

    // stops
    public void stop(){
        robot.shooterBack.setPower(0);
        robot.shooterFront.setPower(0);

    }
}
