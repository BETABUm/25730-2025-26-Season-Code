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


public class ShooterSystem {

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
    public ShooterSystem (RobotMap robot) {
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

    public enum SHOOTSTATES{
        VELO, FIRSTBALL, CHECKSERVOS, SECONDBALL, CHECKSERVOS2, THIRDBALL, DONESHOOTING, DONEDONE;
    }

    public SHOOTSTATES state = SHOOTSTATES.VELO;
    public void shootBall(double velocityf, double velocityb, double lowServoPowerL, double lowServoPowerR, double hexIndexPower, double servoThreePower, double left_trigger) {

        if (left_trigger>=.69){
            shooting = true;
        } else {
            shooting = false;
        }

            if (shooting) {
                switch (state) {
                    case VELO:
                        timer.reset();
                        shooter.setVelo(velocityf, velocityb);
                        if ((Math.abs(shooter.get_velob() - velocityb) < 20) && ((Math.abs(shooter.get_velof() - velocityf) < 20))) {
                            servo3.setPower(servoThreePower);
                            state = SHOOTSTATES.FIRSTBALL;
                            stateStartTime = timer.timer();
                        }
                        break;

                    case FIRSTBALL:
                        if ((timer.timer() - stateStartTime >= 2) && ((Math.abs(shooter.get_velob() - velocityb) < 30) && ((Math.abs(shooter.get_velof() - velocityf) < 30)))) {
                            state = SHOOTSTATES.SECONDBALL;
                            stateStartTime = timer.timer();
                        }
                        break;

                    case SECONDBALL:
                        hexIndexMotor.setPower(hexIndexPower);
                        if ((timer.timer() - stateStartTime >= .05) && ((Math.abs(shooter.get_velob() - velocityb) < 30) && ((Math.abs(shooter.get_velof() - velocityf) < 30)))) {
                            state = SHOOTSTATES.THIRDBALL;
                            stateStartTime = timer.timer();
                        }
                        break;

                    case THIRDBALL:
                        lowServo.setPower(lowServoPowerL,lowServoPowerR);
                        if ((timer.timer() - stateStartTime >= .02)) {
                            state = SHOOTSTATES.DONESHOOTING;
                            stateStartTime = timer.timer();
                        }
                        break;

                    case DONESHOOTING:
                        if ((timer.timer() - stateStartTime >= 5)) {
                            stop();
                            shooting = false;
                            state = SHOOTSTATES.VELO;
                        }
                        break;

                }
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
