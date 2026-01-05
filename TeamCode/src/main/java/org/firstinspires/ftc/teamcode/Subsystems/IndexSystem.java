package org.firstinspires.ftc.teamcode.Subsystems;

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


public class IndexSystem {

    private RobotMap robot;
    private Intake intake;
    private LowServo lowServo;
    private Servo2 servoTwo;
    private Servo3 servoThree;
    private LimitSwitch limitSwitch;
    private LimitSwitch2 limitSwitch2;
    private LimitSwitch3 limitSwitch3;
    private LimitSwitch4 limitSwitch4;
    private Shooter shooter;

    public IndexSystem (RobotMap robot){
        this.robot = robot;
        intake = new Intake(robot);
        lowServo = new LowServo(robot);
        servoTwo = new Servo2(robot);
        servoThree = new Servo3(robot);
        limitSwitch = new LimitSwitch(robot);
        limitSwitch2 = new LimitSwitch2(robot);
        limitSwitch3 = new LimitSwitch3(robot);
        limitSwitch4 = new LimitSwitch4(robot);
        shooter = new Shooter(robot);
    }

    public void intakeBall (double intakePow, double lowServoRight, double lowServoLeft, double servoTwoPower, double servoThreePower){

            intake.setPower(intakePow);

            if (Math.min(limitSwitch.get_value(),1) == 1){
                runServos(servoThreePower,servoTwoPower,lowServoLeft,lowServoRight);
                robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            if(Math.min(limitSwitch4.get_value(),1) == 1){
                servoThree.stop();
            }

            if(Math.min(limitSwitch.get_value(),2) == 2){
                lowServo.setPower(lowServoLeft, lowServoRight);
                servoTwo.setPower(servoTwoPower);
            }

            if(Math.min(limitSwitch3.get_value(),2) == 2){
                servoTwo.stop();
            }

            if(Math.min(limitSwitch.get_value(),3) == 3){
                lowServo.setPower(lowServoLeft, lowServoRight);
            }

            if(Math.min(limitSwitch2.get_value(),3) == 3){
                lowServo.stop();
            }

        }

    public void runServos(double servoThreePower, double servoTwoPower, double lowServoPowerL, double lowServoPowerR){
        servoThree.setPower(servoThreePower);
        servoTwo.setPower(servoTwoPower);
        lowServo.setPower(lowServoPowerL, lowServoPowerR);
    }
    public void stopServos(){
        lowServo.stop();
        servoTwo.stop();
        servoThree.stop();
    }

    public void stopAll(){
        stopServos();
        intake.stop();
    }

}

