package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Individuals.Intake;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch4;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo2;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.RobotMap;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainSystem;
import org.firstinspires.ftc.teamcode.Subsystems.IndexSystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSystem;

@Autonomous(name = "Three Piece + Move", preselectTeleOp = "MainTeleOp")
public class Three_Piece_Move_Auto extends LinearOpMode {

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
    ShooterSystemAuto shooterSystemAuto;
    DriveTrainSystemAuto driveTrainSystemAuto;

    public void runOpMode() {

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
        shooterSystemAuto = new ShooterSystemAuto(robot);
        driveTrainSystemAuto = new DriveTrainSystemAuto(robot);
        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        while (opModeIsActive()) {

            shooterSystemAuto.shootBallAuto(750, 750, 10, 10, 1, 1, 1, 1);
            driveTrainSystemAuto.Drive(1000,1000,1000,1000,5);

        }
    }
}
