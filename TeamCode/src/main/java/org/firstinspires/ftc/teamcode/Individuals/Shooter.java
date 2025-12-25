package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Shooter {

    private RobotMap robot;

    public Shooter (RobotMap robot){

        this.robot = robot;

    }
    public void set_power(double power, double power1){

        robot.shooterBack.setPower(power1);
        robot.shooterFront.setPower(power);

    }

    // function to set power and encoder position (in ticks)
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

    public void setPowerRevsBack(double pwrb, double revsB){
        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double ticksPerRevBack = robot.shooterBack.getMotorType().getTicksPerRev();
        revsB = revsB * ticksPerRevBack;
        robot.shooterBack.setTargetPosition((int)revsB);
        robot.shooterBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.shooterBack.setPower(pwrb);

        if (robot.shooterBack.getCurrentPosition() >= revsB) {
            stop();
        }
    }

    public double getRevsBack(double revs){
        double ticksPerRevBack = robot.shooterBack.getMotorType().getTicksPerRev();
        double revsBackint = revs;
        revsBackint = revsBackint * ticksPerRevBack;
        return revsBackint;
    }
    public void setPowerRevs(double powerf,double powerb, double revsFront, double revsBack){
        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double ticksPerRevFront = robot.shooterFront.getMotorType().getTicksPerRev();
        double ticksPerRevBack = robot.shooterBack.getMotorType().getTicksPerRev();

        revsFront = revsFront * ticksPerRevFront;
        revsBack = revsBack * ticksPerRevBack;

        robot.shooterBack.setTargetPosition((int)revsBack);
        robot.shooterFront.setTargetPosition((int)revsFront);

        robot.shooterBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.shooterFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.shooterBack.setPower(powerb);
        robot.shooterFront.setPower(powerf);

        int currentposb = robot.shooterBack.getCurrentPosition();
        int math = Math.abs((int)revsBack);

        if (Math.abs(robot.shooterBack.getCurrentPosition() - robot.shooterBack.getTargetPosition()) <= 200) {
            robot.shooterBack.setPower(0);
            robot.shooterFront.setPower(0);
        }

        }


    // stops
    public void stop(){

        robot.shooterBack.setPower(0);
        robot.shooterFront.setPower(0);


    }

    public double get_encoderf(){
        double ticksPerRev = robot.shooterFront.getMotorType().getTicksPerRev();
        //this gives us revolutions, which is better than encoder ticks and a more reliable
        return (robot.shooterFront.getCurrentPosition()) / (ticksPerRev * 20);

    }

    public double get_posb(){
        return (robot.shooterBack.getCurrentPosition());

    }

    public double get_encoderb(){
        double ticksPerRev = robot.shooterBack.getMotorType().getTicksPerRev();
        //this gives us revolutions, which is better than encoder ticks and a more reliable
        return (robot.shooterBack.getCurrentPosition()) / (ticksPerRev * 20);

    }
}

