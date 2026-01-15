package org.firstinspires.ftc.teamcode.Individuals;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Shooter {

    private RobotMap robot;

    public Shooter(RobotMap robot) {
        this.robot = robot;
    }

    public void set_power(double power, double power1) {
        robot.shooterBack.setPower(power1);
        robot.shooterFront.setPower(power);
    }

    // function to set power and encoder position (in ticks)
    public void setPowerPos(double power, int position) {
        robot.shooterBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.shooterFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.shooterBack.setPower(power);
        robot.shooterFront.setPower(power);
        robot.shooterBack.setTargetPosition(position);
        robot.shooterFront.setTargetPosition(position);
    }

    // function to get encoder values so we can access them anywhere
    public double get_encoderFront() {
        double ticksPerRevFront = robot.shooterFront.getMotorType().getTicksPerRev();
        return robot.shooterFront.getCurrentPosition() / ticksPerRevFront;
    }

    public double get_encoderBack() {
        double ticksPerRevBack = robot.shooterFront.getMotorType().getTicksPerRev();
        return robot.shooterBack.getCurrentPosition() / ticksPerRevBack;
    }

    public void setPowerRevsBack(double pwrb, double revsB) {
        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double ticksPerRevBack = robot.shooterBack.getMotorType().getTicksPerRev();
        revsB = revsB * ticksPerRevBack;
        robot.shooterBack.setTargetPosition((int) revsB);
        robot.shooterBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.shooterBack.setPower(pwrb);
    }

    public double getRevsBack(double revs) {
        double ticksPerRevBack = robot.shooterBack.getMotorType().getTicksPerRev();
        double revsBackint = revs;
        revsBackint = revsBackint * ticksPerRevBack;
        return revsBackint;
    }

    public void setVelocityRevs(double velocityf, double velocityb, double revsFront, double revsBack) {

        double ticksPerRevFront = robot.shooterFront.getMotorType().getTicksPerRev();
        double ticksPerRevBack = robot.shooterBack.getMotorType().getTicksPerRev();

        revsFront = revsFront * ticksPerRevFront;
        revsBack = revsBack * ticksPerRevBack;

        robot.shooterBack.setTargetPosition((int) revsBack);
        robot.shooterFront.setTargetPosition((int) revsFront);

        robot.shooterBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.shooterFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.shooterBack.setVelocity(velocityb);
        robot.shooterFront.setVelocity(velocityf);

    }

    public void setVelo(double velocityf, double velocityb){
        robot.shooterBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.shooterFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.shooterBack.setVelocity(velocityb);
        robot.shooterFront.setVelocity(velocityf);
    }

    // stops
    public void stop() {
        robot.shooterBack.setVelocity(0);
        robot.shooterFront.setVelocity(0);
    }

    public double get_encoderf() {
        double ticksPerRev = robot.shooterFront.getMotorType().getTicksPerRev();
        //this gives us revolutions, which is better than encoder ticks and a more reliable
        return (robot.shooterFront.getCurrentPosition()) / (ticksPerRev * 20);
    }

    public double get_posb() {
        return (robot.shooterBack.getCurrentPosition());

    }

    public double get_encoderb() {
        double ticksPerRev = robot.shooterBack.getMotorType().getTicksPerRev();
        //this gives us revolutions, which is better than encoder ticks and a more reliable
        return (robot.shooterBack.getCurrentPosition()) / (ticksPerRev * 20);

    }

    public double get_velob() {
        return robot.shooterBack.getVelocity();
    }

    public double get_velof() {

        return robot.shooterFront.getVelocity();
    }

    public boolean check_position() {
        return (robot.shooterBack.getCurrentPosition() <= robot.shooterBack.getTargetPosition() + 100) && (robot.shooterBack.getCurrentPosition() > robot.shooterBack.getTargetPosition() - 100);
    }

}


