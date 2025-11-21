package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;

@TeleOp
public class MainTeleOp extends OpMode{

    RobotMap robot = new RobotMap();
    Intake intake;

    @Override
    public void init(){
       robot.init(hardwareMap);
       intake = new Intake(robot);
    }

    @Override
    public void loop(){
        if (gamepad1.a){
             intake.spin_in(.5);
        }

        if (gamepad1.b){
            intake.spin_stop();
        }
    }
}
