package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;


@TeleOp
public class MainTeleOp extends OpMode{

    // still understanding this
    RobotMap robot = new RobotMap();
    Intake intake;
    Shooter shooter;
    MecanumDriveTrain mecanumDriveTrain;

    limitSwitch limitSwitch;


    // Initialization code (still understanding)
    @Override
    public void init(){
       robot.init(hardwareMap);
       intake = new Intake(robot);
       shooter = new Shooter(robot);
       mecanumDriveTrain = new MecanumDriveTrain(robot);
       limitSwitch = new limitSwitch(robot);
    }

    // Main code and functions go here
    // Last year we didn't do this, but it's good to have buttons in one file
    @Override
    public void loop(){
        // a button, a boolean value
        if (gamepad1.a){
             intake.setPowerRevs(1, -5);
        }

        // b button, boolean value
        if (gamepad1.b){
            intake.stop();
        }

        if (gamepad1.x){
            shooter.stop();
        }

        if (gamepad1.y){
            shooter.set_power(.5);
        }

        // x button, boolean value

        // y button, boolean value

        // left bumper (LB), boolean value

        // right bumper (RB), boolean value

        // left trigger (LT), acts like a joystick of values from -1.0 to 1.0

        // right trigger (RT), acts like a joystick of values from -1.0 to 1.0

        // drivetrain code
        // mecanumDriveTrain.Drive(gamepad1.right_stick_x, gamepad1.left_stick_y, gamepad1.left_stick_x);

        // Adds revolution for intake motor to Telemetry

        telemetry.addData("Revs on Intake", intake.get_encoder());
        telemetry.addData("limitSwitch", limitSwitch.get_encoder());
        telemetry.update();



    }
}
