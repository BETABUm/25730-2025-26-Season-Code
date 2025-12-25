package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.Individuals.Intake;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Servo2;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.RobotMap;


public class IndexSystem {

    private RobotMap robot;
    private Intake intake;
    private LowServo lowServo;
    private Servo2 servoTwo;
    private Servo3 servoThree;
    private LimitSwitch limitSwitch;

    public IndexSystem (RobotMap robot){
        this.robot = robot;
        intake = new Intake(robot);
        lowServo = new LowServo(robot);
        servoTwo = new Servo2(robot);
        servoThree = new Servo3(robot);
        limitSwitch = new LimitSwitch(robot);
    }

    public void intakeBall (double intakePow, double intakeRevs, double lowServoRight, double lowServoLeft, double servoTwoPower, double servoThreePower){
        intake.setPowerRevs(intakePow, intakeRevs);
        if (limitSwitch.get_encoder()) {
            intake.stop();
            lowServo.setPower(lowServoLeft, lowServoRight);
            servoTwo.setPower(servoTwoPower);
            servoThree.setPower(servoThreePower);
        }
    }


}
