package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Individuals.HexIndexMotor;
import org.firstinspires.ftc.teamcode.Individuals.Intake;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.Individuals.Timer;
import org.firstinspires.ftc.teamcode.RobotMap;


public class IndexSystem {

    private RobotMap robot;
    private Intake intake;
    private LowServo lowServo;
    private Servo3 servoThree;
    private LimitSwitch limitSwitch;
    private LimitSwitch2 limitSwitch2;
    private LimitSwitch3 limitSwitch3;
    private Shooter shooter;
    private HexIndexMotor hexIndexMotor;
    private Timer timer;

    public IndexSystem (RobotMap robot){
        this.robot = robot;
        intake = new Intake(robot);
        lowServo = new LowServo(robot);
        servoThree = new Servo3(robot);
        limitSwitch = new LimitSwitch(robot);
        limitSwitch2 = new LimitSwitch2(robot);
        limitSwitch3 = new LimitSwitch3(robot);
        shooter = new Shooter(robot);
        hexIndexMotor = new HexIndexMotor(robot);
        timer = new Timer(robot);
    }

    public enum IndexStatesThreeBalls {

        INTAKEON, THIRDSERVOSTOP, SECONDSERVOSTOP, LOWSERVOINTAKESTOP
    }
    public IndexStatesThreeBalls state = IndexStatesThreeBalls.INTAKEON;


    public void intakeBall (double intakePow, double lowServoRight, double lowServoLeft, double hexIndexPower, double servoThreePower){

            switch(state){

                case INTAKEON:
                    intake.setPower(intakePow);
                    runServos(servoThreePower,hexIndexPower,lowServoLeft,lowServoRight);
                    if(Math.min(limitSwitch3.get_value(),1) == 1){
                        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        state = IndexStatesThreeBalls.THIRDSERVOSTOP;
                    }
                    break;

                case THIRDSERVOSTOP:
                    servoThree.stop();
                    if(Math.min(limitSwitch2.get_value(),1) == 1){
                        hexIndexMotor.stop();
                        state = IndexStatesThreeBalls.SECONDSERVOSTOP;
                    }

                    break;

                case SECONDSERVOSTOP:
                    if(Math.min(limitSwitch.get_value(),1) == 1){
                        state = IndexStatesThreeBalls.LOWSERVOINTAKESTOP;
                    }
                    break;

                case LOWSERVOINTAKESTOP:
                    timer.reset();
                    stopServos();
                    intake.stop();
                    break;

            }
    }

    public void runServos(double servoThreePower, double hexIndexPower, double lowServoPowerL, double lowServoPowerR){
        servoThree.setPower(servoThreePower);
        hexIndexMotor.setPower(hexIndexPower);
        lowServo.setPower(lowServoPowerL, lowServoPowerR);
    }
    public void stopServos(){
        lowServo.stop();
        hexIndexMotor.stop();
        servoThree.stop();
    }

    public void stopAll(){
        stopServos();
        intake.stop();
    }

}

