package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Individuals.HexIndexMotor;
import org.firstinspires.ftc.teamcode.Individuals.Intake;
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
    private Intake intake;
    private boolean shooting = false;
    private boolean wasTriggerPressed = false;
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
        limitSwitchReset = new LimitSwitchReset(robot, limitSwitch, limitSwitch2, limitSwitch3);
        hexIndexMotor = new HexIndexMotor(robot);
        gamepad1 = new Gamepad();
        intake = new Intake(robot);
        timer = new Timer(robot);
    }

    public enum SHOOTSTATES{
        VELO, FIRSTBALL, SECONDBALL, DONEDONE, IDK;
    }

    public SHOOTSTATES state = SHOOTSTATES.VELO;
    public void shootBall(double velocityf, double velocityb, double lowServoPowerL, double lowServoPowerR, double hexIndexPower, double servoThreePower, double left_trigger) {

        boolean triggerPressed = left_trigger >= 0.69;

        if (triggerPressed && !wasTriggerPressed) {
            shooting = true;
            timer.reset();
            state = SHOOTSTATES.VELO;
        }

        wasTriggerPressed = triggerPressed;

        if (shooting) {
            switch(state){
                case VELO:
                    shooter.setVelo(velocityf,velocityb);
                    if ((Math.abs(shooter.get_velob() - velocityb) < 10) && ((Math.abs(shooter.get_velof() - velocityf) < 10))) {
                        state = SHOOTSTATES.FIRSTBALL;
                        stateStartTime = timer.timer();
                    }
                    break;
                case FIRSTBALL:
                    servo3.setPower(servoThreePower);
                    if(timer.timer() - stateStartTime >=1.5){
                        state = SHOOTSTATES.SECONDBALL;
                    }
                    break;
                case SECONDBALL:
                    servo3.setPower(servoThreePower);
                    intake.setPower(-1);
                    hexIndexMotor.setPower(hexIndexPower);
                    lowServo.setPower(lowServoPowerL,lowServoPowerR);
                    if(timer.timer() - stateStartTime >=5.67){
                        state = SHOOTSTATES.DONEDONE;
                    }
                    break;
                case DONEDONE:
                    stop();
                    intake.stop();
                    state = SHOOTSTATES.IDK;
                    shooting = false;
                    break;
            }

        }
    }


    public void cancelShooting(){
        shooting = false;
        wasTriggerPressed = false;
        stop();
        intake.stop();
        state = SHOOTSTATES.IDK;
    }
    //stops all things related to our shooter
    public void stop(){
        shooter.stop();
        lowServo.stop();
        hexIndexMotor.stop();
        servo3.stop();
    }
}