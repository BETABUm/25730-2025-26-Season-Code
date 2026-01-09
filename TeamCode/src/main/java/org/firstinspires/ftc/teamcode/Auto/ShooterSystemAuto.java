package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch4;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitchReset;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo2;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.RobotMap;
import org.firstinspires.ftc.teamcode.Subsystems.IndexSystem;

public class ShooterSystemAuto {

    private RobotMap robot;
    private LimitSwitch limitSwitch;
    private LimitSwitch2 limitSwitch2;
    private LimitSwitch3 limitSwitch3;
    private LimitSwitch4 limitSwitch4;
    private Shooter shooter;
    private LowServo lowServo;
    private Servo2 servo2;
    private Servo3 servo3;
    private IndexSystem indexSystem;
    private LimitSwitchReset limitSwitchReset;

    public ShooterSystemAuto(RobotMap robot) {
        this.robot = robot;
        limitSwitch4 = new LimitSwitch4(robot);
        shooter = new Shooter(robot);
        lowServo = new LowServo(robot);
        servo2 = new Servo2(robot);
        servo3 = new Servo3(robot);
        indexSystem = new IndexSystem(robot);
        limitSwitch = new LimitSwitch(robot);
        limitSwitch2 = new LimitSwitch2(robot);
        limitSwitch3 = new LimitSwitch3(robot);
        limitSwitch4 = new LimitSwitch4(robot);
        limitSwitchReset = new LimitSwitchReset(robot);
    }

    public enum ShooterStates {
        SHOOTING, DONESHOOTING
    }

    public ShooterStates state = ShooterStates.SHOOTING;


        public void shootBallAuto(double velocityf, double velocityb, double revsf, double revsb, double lowServoPowerL, double lowServoPowerR, double servoTwoPower, double servoThreePower){

            switch(state) {
                case SHOOTING:
                    shooter.setVelocityRevs(velocityf, velocityb, revsf, revsb);

                    if ((shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
                        indexSystem.runServos(servoThreePower, servoTwoPower, lowServoPowerL, lowServoPowerR);
                    }

                    if(shooter.check_position()){
                        state = ShooterStates.DONESHOOTING;
                    }
                    break;

                case DONESHOOTING:
                        indexSystem.stopServos();
                        limitSwitchReset.resetLimitSwitches();
                    break;
            }
        }
    }

