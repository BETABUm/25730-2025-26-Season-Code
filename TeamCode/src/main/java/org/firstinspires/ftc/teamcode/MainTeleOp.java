package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch4;
import org.firstinspires.ftc.teamcode.Individuals.Servo2;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainSystem;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Subsystems.IndexSystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSystem;


@TeleOp
public class MainTeleOp extends OpMode{

    // still understanding this
    RobotMap robot = new RobotMap();
    Intake intake;
    Shooter shooter;
    DriveTrainSystem mecanumDriveTrain;
    LowServo lowIndex;
    LimitSwitch limitSwitch;
    LimitSwitch2 limitSwitch2;
    LimitSwitch3 limitSwitch3;
    LimitSwitch4 limitSwitch4;
    Servo2 servo2;
    Servo3 servo3;
    IndexSystem indexSystem;
    ShooterSystem shooterSystem;

    // Initialization code (still understanding)
    @Override
    public void init(){
       robot.init(hardwareMap);
       intake = new Intake(robot);
       shooter = new Shooter(robot);
       mecanumDriveTrain = new DriveTrainSystem(robot);
       limitSwitch = new LimitSwitch(robot);
       limitSwitch2 = new LimitSwitch2(robot);
       limitSwitch3 = new LimitSwitch3(robot);
       limitSwitch4 = new LimitSwitch4(robot);
       lowIndex = new LowServo(robot);
       servo2 = new Servo2(robot);
       servo3 = new Servo3(robot);
       indexSystem = new IndexSystem(robot);
       shooterSystem = new ShooterSystem(robot);
       robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Main code and functions go here
    // Last year we didn't do this, but it's good to have buttons in one file
    @Override
    public void loop(){
        // a button, a boolean value
        if (gamepad1.right_trigger >= .69){
             indexSystem.intakeBall(-1, 1, 1, 1, 1);
        }

        if (gamepad1.b){
            indexSystem.stopAll();
        }

        if(gamepad1.left_trigger >= .69) {
            shooterSystem.shootBall(750, 750, 20, 20, 1, 1, 1,1);
        }

        if(gamepad1.dpad_left){
            shooterSystem.stop();
        }

        if (gamepad1.x){
            indexSystem.stopServos();
        }

        if (gamepad1.a){
            indexSystem.runServos(1,1,1,1);
        }

        if ((robot.intake.getCurrentPosition() <= robot.intake.getTargetPosition() + 100) && (robot.intake.getCurrentPosition() > robot.intake.getTargetPosition()-100)) {
            intake.stop();
        }

        if ((robot.shooterBack.getCurrentPosition() <= robot.shooterBack.getTargetPosition() + 100) && (robot.shooterBack.getCurrentPosition() > robot.shooterBack.getTargetPosition()-100)) {
            shooter.stop();
        }

        mecanumDriveTrain.Drive(gamepad1.right_stick_x, -gamepad1.left_stick_y, -gamepad1.left_stick_x);

        telemetry.addData("Revs on Intake", intake.get_encoder());
        telemetry.addData("limitSwitch number of times", limitSwitch.get_value());
        telemetry.addData("limit switch 2 number of times", limitSwitch2.get_value());
        telemetry.addData("limit switch 3 number of times", limitSwitch3.get_value());
        telemetry.addData("limit switch 4 number of times", limitSwitch4.get_value());
        telemetry.addData("shooter front revs", shooter.get_encoderf());
        telemetry.update();

    }

}
