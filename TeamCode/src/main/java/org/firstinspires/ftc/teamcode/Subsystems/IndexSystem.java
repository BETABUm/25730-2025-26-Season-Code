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

    public enum IndexStatesThreeBalls {

        INTAKEON, THIRDSERVOSTOP, SECONDSERVOSTOP, LOWSERVOINTAKESTOP
    }
    public IndexStatesThreeBalls state = IndexStatesThreeBalls.INTAKEON;

    /*
    public enum IndexStatesTwoBalls{
        INTAKEON, THIRDSERVOSTOP, SECONDSERVOLOWSERVOINTAKESTOP;
    }

    IndexStatesTwoBalls state2 = IndexStatesTwoBalls.INTAKEON;

    public enum IndexStatesOneBall{
        INTAKEON, THIRDSECONDSERVOLOWSERVOINTAKESTOP;
    }

    IndexStatesOneBall state3 = IndexStatesOneBall.INTAKEON;
    */

    public void intakeBall (double intakePow, double lowServoRight, double lowServoLeft, double servoTwoPower, double servoThreePower){

            switch(state){

                case INTAKEON:
                    intake.setPower(intakePow);
                    runServos(servoThreePower,servoTwoPower,lowServoLeft,lowServoRight);
                    if(Math.min(limitSwitch3.get_value(),1) == 1){
                        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        state = IndexStatesThreeBalls.THIRDSERVOSTOP;
                    }
                    break;

                case THIRDSERVOSTOP:

                    servoThree.stop();
                    intake.setPower(intakePow);
                    lowServo.setPower(lowServoLeft,lowServoRight);
                    servoTwo.setPower(servoTwoPower);
                    if(Math.min(limitSwitch2.get_value(),2) == 2){
                        state = IndexStatesThreeBalls.SECONDSERVOSTOP;
                    }

                    break;

                case SECONDSERVOSTOP:

                    servoTwo.stop();
                    intake.setPower(intakePow);
                    lowServo.setPower(lowServoLeft, lowServoRight);
                    if(Math.min(limitSwitch.get_value(),3) == 3){
                        state = IndexStatesThreeBalls.LOWSERVOINTAKESTOP;
                    }
                    break;

                case LOWSERVOINTAKESTOP:
                    intake.stop();
                    stopServos();
                    break;

            }

            /*
            switch(state2){

                case INTAKEON:
                    intake.setPower(intakePow);
                    runServos(servoThreePower,servoTwoPower,lowServoLeft,lowServoRight);
                    if(Math.min(limitSwitch3.get_value(),1) == 1){
                        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        state2 = IndexStatesTwoBalls.THIRDSERVOSTOP;
                    }

                case THIRDSERVOSTOP:
                    servoThree.stop();
                    intake.setPower(intakePow);
                    lowServo.setPower(lowServoLeft,lowServoRight);
                    servoTwo.setPower(servoTwoPower);
                    if(Math.min(limitSwitch2.get_value(),2) == 2){
                        state2 = IndexStatesTwoBalls.SECONDSERVOLOWSERVOINTAKESTOP;
                    }

                case SECONDSERVOLOWSERVOINTAKESTOP:
                    servoTwo.stop();
                    lowServo.stop();
                    intake.stop();
                    break;
            }

            switch(state3){

                case INTAKEON:
                    intake.setPower(intakePow);
                    runServos(servoThreePower,servoTwoPower,lowServoLeft,lowServoRight);
                    if(Math.min(limitSwitch3.get_value(),1) == 1){
                        robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        state3 = IndexStatesOneBall.THIRDSECONDSERVOLOWSERVOINTAKESTOP;
                    }

                case THIRDSECONDSERVOLOWSERVOINTAKESTOP:
                    stopServos();
                    intake.stop();
                    break;
            }
            */
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

