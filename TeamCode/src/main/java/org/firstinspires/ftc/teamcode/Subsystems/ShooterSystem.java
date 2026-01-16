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
                switch (state) {
                    case VELO:
                        shooter.setVelo(velocityf, velocityb);
                        state = SHOOTSTATES.FIRSTBALL;
                        break;
                    case FIRSTBALL:
                        if ((shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
                            servo3.setPower(-servoThreePower);
                            hexIndexMotor.setPower(hexIndexPower);
                            lowServo.setPower(lowServoPowerL, lowServoPowerR);
                            state = SHOOTSTATES.CHECKSERVOS;
                        }
                        break;

                    case CHECKSERVOS:
                        if (Math.min(limitSwitch3.get_value(), 2) == 2 && Math.min(limitSwitch2.get_value(), 3) == 3) {
                            indexSystem.stopServos();
                            state = SHOOTSTATES.SECONDBALL;
                        }
                        break;

                    case SECONDBALL:
                        if ((shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
                            servo3.setPower(-servoThreePower);
                            hexIndexMotor.setPower(hexIndexPower);
                            state = SHOOTSTATES.CHECKSERVOS2;
                        }
                        break;

                    case CHECKSERVOS2:
                        if (Math.min(limitSwitch3.get_value(), 3) == 3) {
                            indexSystem.stopServos();
                            state = SHOOTSTATES.THIRDBALL;
                        }
                        break;

                    case THIRDBALL:
                        if ((shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
                            servo3.setPower(-servoThreePower);
                            state = SHOOTSTATES.DONESHOOTING;
                        }
                        break;

                    case DONESHOOTING:
                        if (Math.min(limitSwitch3.get_value(), 3) == 3) {
                            stop();
                            state = SHOOTSTATES.DONEDONE;
                        }
                        break;

                    case DONEDONE:
                        shooting = false;
                        state = SHOOTSTATES.VELO;
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
