package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotMap;

public class Intake {
    //still understanding this line and the constructor
    //think of robot as the place to access our motors, servos, etc
    private RobotMap robot;
    public Intake (RobotMap robot){
        this.robot = robot;
    }

    // basic function to set the power of the intake motor. robot.intake lets us access the motor from our "robot"
    public void set_power(double power){
        robot.intake.setPower(power);
    }


    // function to set power and encoder position (in ticks) of intake
    public void set_power_pos(double power, int position){
        robot.intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.intake.setPower(power);
        robot.intake.setTargetPosition(position);
    }

    // stops intake
    public void stop(){
        robot.intake.setPower(0);
    }

    // function to get encoder values so we can access them anywhere
    public double get_encoder(){
        int intake_encoder = robot.intake.getCurrentPosition();
        double ticksPerRev = robot.intake.getMotorType().getTicksPerRev();
        //this gives us revolutions, which is better than encoder ticks and more reliable
        return robot.intake.getCurrentPosition() / ticksPerRev; //* gear ratio;
    }

}
