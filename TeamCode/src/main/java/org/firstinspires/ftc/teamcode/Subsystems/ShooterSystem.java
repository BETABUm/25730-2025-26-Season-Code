package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Individuals.HexIndexMotor;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitchReset;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
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
    private boolean shooting = false;

    private long stateStartTime = 0;

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
                long now = System.currentTimeMillis();

                switch (state) {
                    case VELO:
                        shooter.setVelo(velocityf, velocityb);
                        if ((shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
                            state = SHOOTSTATES.FIRSTBALL;
                            stateStartTime = now;
                        }
                        break;

                    case FIRSTBALL:
                            servo3.setPower(servoThreePower);
                            if ((now - stateStartTime >= 1000) && (shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
                                state = SHOOTSTATES.SECONDBALL;
                                stateStartTime = now;
                            }
                        break;

                    case SECONDBALL:
                        hexIndexMotor.setPower(hexIndexPower);
                        if ((now - stateStartTime >= 1000) && (shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
                            state = SHOOTSTATES.THIRDBALL;
                            stateStartTime = now;
                        }
                        break;

                    case THIRDBALL:
                        lowServo.setPower(lowServoPowerL,lowServoPowerR);
                        if ((now - stateStartTime >= 2000)) {
                            state = SHOOTSTATES.DONESHOOTING;
                            stateStartTime = now;
                        }
                        break;

                    case DONESHOOTING:
                        stop();
                        if ((now - stateStartTime >= 2000)) {
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
