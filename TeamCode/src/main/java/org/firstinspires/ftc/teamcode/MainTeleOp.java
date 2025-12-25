package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Individuals.Servo2;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainSystem;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Subsystems.IndexSystem;


@TeleOp
public class MainTeleOp extends OpMode{

    // still understanding this
    RobotMap robot = new RobotMap();
    Intake intake;
    Shooter shooter;
    DriveTrainSystem mecanumDriveTrain;
    LowServo lowIndex;
    LimitSwitch limitSwitch;

    Servo2 servo2;

    Servo3 servo3;

    IndexSystem indexSystem;


    // Initialization code (still understanding)
    @Override
    public void init(){
       robot.init(hardwareMap);
       intake = new Intake(robot);
       shooter = new Shooter(robot);
       mecanumDriveTrain = new DriveTrainSystem(robot);
       limitSwitch = new LimitSwitch(robot);
       lowIndex = new LowServo(robot);
       servo2 = new Servo2(robot);
       servo3 = new Servo3(robot);
       indexSystem = new IndexSystem(robot);
    }

    // Main code and functions go here
    // Last year we didn't do this, but it's good to have buttons in one file
    @Override
    public void loop(){
        // a button, a boolean value
        if (gamepad1.a){
             intake.setPowerRevs(1, -3);
        }

        // b button, boolean value
        if (gamepad1.b){
            intake.stop();
        }

        if (gamepad1.x){
            shooter.stop();
        }

        if (gamepad1.y){
           shooter.setPowerRevs(.5, .67, 4, 4);
        }

        if(gamepad1.right_bumper){
            lowIndex.setPower(1, 1);

        }

        if(gamepad1.dpad_left){
            servo2.setPower(1);
        }

        if(gamepad1.dpad_right){
            servo3.setPower(1);
        }

        if((gamepad1.right_trigger) >= 0.69){
            indexSystem.intakeBall(1, -2, 1, 1, 1, 1);
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
        telemetry.addData("Revs Pos", intake.get_posintake());
        telemetry.addData("limitSwitch", limitSwitch.get_encoder());
        telemetry.addData("shooter front revs", shooter.get_encoderf());
        telemetry.addData("shooter back revs", shooter.get_encoderb());
        telemetry.addData("shooterback pos", shooter.get_posb());
        telemetry.addData("Shooter Back Revs", shooter.getRevsBack(2));
        telemetry.update();

    }
}
