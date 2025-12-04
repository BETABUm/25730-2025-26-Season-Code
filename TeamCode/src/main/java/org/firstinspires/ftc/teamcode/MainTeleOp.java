package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveTrain;

@TeleOp
public class MainTeleOp extends OpMode{

    RobotMap robot = new RobotMap();
    Intake intake;

    MecanumDriveTrain mecanumDriveTrain = new MecanumDriveTrain(robot);

    // Initialization code
    @Override
    public void init(){
       robot.init(hardwareMap);
       intake = new Intake(robot);
    }

    // Main code and functions go here
    @Override
    public void loop(){
        // a button, boolean value
        if (gamepad1.a){
             intake.set_power(.5);
        }

        // b button, boolean value
        if (gamepad1.b){
            intake.stop();
        }

        // x button, boolean value

        // y button, boolean value

        // left bumper (LB), boolean value

        // right bumper (RB), boolean value

        // left trigger (LT), acts like a joystick of values from -1.0 to 1.0

        // right trigger (RT), acts like a joystick of values from -1.0 to 1.0

        // Adds revolution for intake motor to Telemetry

        mecanumDriveTrain.Drive(gamepad1);


        telemetry.addData("Revs on Intake", intake.get_encoder());
        telemetry.update();
    }
}
