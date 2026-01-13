package org.firstinspires.ftc.teamcode.Subsystems;

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
    }

    public enum SHOOTSTATES{
        FIRSTBALL, SECONDBALL, THIRDBALL, DONESHOOTING, DONEDONE;
    }

    public SHOOTSTATES state = SHOOTSTATES.FIRSTBALL;

    public void shootBall(double velocityf, double velocityb, double revsf, double revsb, double lowServoPowerL, double lowServoPowerR, double hexIndexPower, double servoThreePower) {

        //sets velocity and revolutions for our shooter
        shooter.setVelocityRevs(velocityf, velocityb, revsf, revsb);

        //checks that our shooter is at the velocity we want it to be at
        if ((shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)) {
            //once we are at the right velocity we move our servos to shoot balls
            indexSystem.runServos(servoThreePower, hexIndexPower, lowServoPowerL, lowServoPowerR);

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
