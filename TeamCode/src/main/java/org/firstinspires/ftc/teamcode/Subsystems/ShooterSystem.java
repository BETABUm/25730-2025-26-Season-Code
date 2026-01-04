package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch4;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo2;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.RobotMap;


public class ShooterSystem {

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

    public ShooterSystem (RobotMap robot) {
        this.robot = robot;
        limitSwitch4 = new LimitSwitch4(robot);
        shooter = new Shooter(robot);
        lowServo = new LowServo(robot);
        servo2 = new Servo2(robot);
        servo3 = new Servo3(robot);
        indexSystem = new IndexSystem(robot);
    }

    public void shootBall(double velocityf, double velocityb, double revsf, double revsb, double lowServoPowerL, double lowServoPowerR, double servoTwoPower, double servoThreePower){

        shooter.setVelocityRevs(velocityf,velocityb,revsf,revsb);

        if((shooter.get_velob() >= velocityb) && (shooter.get_velof() >= velocityf)){
            indexSystem.runServos(servoThreePower, servoTwoPower, lowServoPowerL, lowServoPowerR);

            if(limitSwitch4.get_value() == 3 && limitSwitch.get_value() == 3){
                indexSystem.stopServos();
            }

            if(limitSwitch.get_value() == 2 && limitSwitch4.get_value() == 2){
                indexSystem.stopServos();
            }

            if(limitSwitch.get_value() == 1 && limitSwitch4.get_value() == 1){
                indexSystem.stopServos();
            }

        }

        if(!robot.shooterFront.isBusy() && !robot.shooterBack.isBusy()){
            robot.shooterBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.shooterFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void stop(){
        shooter.stop();
        lowServo.stop();
        servo2.stop();
        servo3.stop();
    }
}
