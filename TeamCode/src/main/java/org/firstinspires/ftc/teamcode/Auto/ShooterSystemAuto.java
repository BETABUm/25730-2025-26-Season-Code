package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Individuals.HexIndexMotor;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitchReset;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.Individuals.Timer;
import org.firstinspires.ftc.teamcode.RobotMap;


public class ShooterSystemAuto {

    private RobotMap robot;
    private LimitSwitch limitSwitch;
    private LimitSwitch2 limitSwitch2;
    private LimitSwitch3 limitSwitch3;
    private Shooter shooter;
    private LowServo lowServo;
    private Servo3 servo3;
    private IndexSystem indexSystem;
    private LimitSwitchReset limitSwitchReset;
    private HexIndexMotor hexIndexMotor;
    private Gamepad gamepad1;
    private Timer timer;
    private boolean shooting = false;

    private double stateStartTime;

    //init and instances of other classes
    public ShooterSystemAuto (RobotMap robot) {
        this.robot = robot;
        shooter = new Shooter(robot);
        lowServo = new LowServo(robot);
        servo3 = new Servo3(robot);
        indexSystem = new IndexSystem(robot);
        limitSwitch = new LimitSwitch(robot);
        limitSwitch2 = new LimitSwitch2(robot);
        limitSwitch3 = new LimitSwitch3(robot);
        limitSwitchReset = new LimitSwitchReset(robot);
        hexIndexMotor = new HexIndexMotor(robot);
        gamepad1 = new Gamepad();
        timer = new Timer(robot);
    }

    public enum SHOOTSTATES2{
        VELO, FIRSTBALL, CHECKSERVOS, SECONDBALL, CHECKSERVOS2, THIRDBALL, DONESHOOTING, DONEDONE;
    }

    public SHOOTSTATES2 state = SHOOTSTATES2.VELO;
    public void shootBallAuto(double velocityf, double velocityb, double lowServoPowerL, double lowServoPowerR, double hexIndexPower, double servoThreePower) {

            switch (state) {
                case VELO:
                    timer.reset();
                    shooter.setVelo(velocityf, velocityb);
                    if (((Math.abs(shooter.get_velob() - velocityb) < 20) && ((Math.abs(shooter.get_velof() - velocityf) < 20))) || (timer.timer() >= 5)) {
                        servo3.setPower(servoThreePower);
                        state = SHOOTSTATES2.FIRSTBALL;
                        stateStartTime = timer.timer();
                    }
                    break;

                case FIRSTBALL:
                    if (((timer.timer() - stateStartTime >= 2.5) && ((Math.abs(shooter.get_velob() - velocityb) < 30) && ((Math.abs(shooter.get_velof() - velocityf) < 30)))) || (timer.timer() >= 5)) {
                        state = SHOOTSTATES2.SECONDBALL;
                        stateStartTime = timer.timer();
                    }
                    break;

                case SECONDBALL:
                    hexIndexMotor.setPower(hexIndexPower);
                    if (((timer.timer() - stateStartTime >= .05) && ((Math.abs(shooter.get_velob() - velocityb) < 30) && ((Math.abs(shooter.get_velof() - velocityf) < 30)))) || (timer.timer() >= 5)) {
                        state = SHOOTSTATES2.THIRDBALL;
                        stateStartTime = timer.timer();
                    }
                    break;

                case THIRDBALL:
                    lowServo.setPower(lowServoPowerL,lowServoPowerR);
                    if ((timer.timer() - stateStartTime >= .02)) {
                        state = SHOOTSTATES2.DONESHOOTING;
                        stateStartTime = timer.timer();
                    }
                    break;

                case DONESHOOTING:
                    if ((timer.timer() - stateStartTime >= 2)) {
                        stop();
                        shooting = false;
                    }
                    break;

            }
        }

    //stops all things related to our shooter
    public void stop(){
        shooter.stop();
        lowServo.stop();
        hexIndexMotor.stop();
        servo3.stop();
    }
}