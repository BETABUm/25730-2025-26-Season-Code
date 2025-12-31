package org.firstinspires.ftc.teamcode.Individuals;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Intake {
    //still understanding this line and the constructor
    //think of robot as the place to access our motors, servos, etc
    private RobotMap robot;

    public Intake (RobotMap robot){

        this.robot = robot;

    }



    // basic function to set the power of the intake motor. robot.intake lets us access the motor from our "robot"
    public void setPower(double power){

        robot.intake.setPower(power);

    }


    // function to set power and encoder position (in ticks) of intake
    public void setPowerPos(double power, int position){
        robot.intake.setTargetPosition(position);
        robot.intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.intake.setPower(power);

    }

    // function to get encoder values so we can access them anywhere
    public double get_encoder(){
        double ticksPerRev = robot.intake.getMotorType().getTicksPerRev();
        //this gives us revolutions, which is better than encoder ticks and a more reliable
        return (robot.intake.getCurrentPosition()) / (ticksPerRev * 20);

    }

    public void setPowerRevs(double power, double revs){
        robot.intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        double ticksPerRev = robot.intake.getMotorType().getTicksPerRev() * 20;
        int targetPos = (int)(revs * ticksPerRev);
        robot.intake.setTargetPosition(targetPos);
        robot.intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.intake.setPower(power);


        }


    public double get_posintake(){
        return (robot.intake.getCurrentPosition());
    }

    public boolean get_state(){
        return robot.intake.isBusy();
    }
    // stops intake
    public void stop(){

        robot.intake.setPower(0);

    }
}
