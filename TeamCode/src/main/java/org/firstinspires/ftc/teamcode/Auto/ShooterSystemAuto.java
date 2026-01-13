package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitchReset;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.RobotMap;
import org.firstinspires.ftc.teamcode.Subsystems.IndexSystem;

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

    public ShooterSystemAuto(RobotMap robot) {
        this.robot = robot;
        shooter = new Shooter(robot);
        lowServo = new LowServo(robot);
        servo3 = new Servo3(robot);
        indexSystem = new IndexSystem(robot);
        limitSwitch = new LimitSwitch(robot);
        limitSwitch2 = new LimitSwitch2(robot);
        limitSwitch3 = new LimitSwitch3(robot);
        limitSwitchReset = new LimitSwitchReset(robot);
    }

    public enum ShooterStates {
        SHOOTING, DONESHOOTING
    }

    public ShooterStates state = ShooterStates.SHOOTING;


        public void shootBallAuto(double velocityf, double velocityb, double revsf, double revsb, double lowServoPowerL, double lowServoPowerR, double servoTwoPower, double servoThreePower){

        }
    }

