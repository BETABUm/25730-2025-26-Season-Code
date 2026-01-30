package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Individuals.HexIndexMotor;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;

import org.firstinspires.ftc.teamcode.Individuals.LimitSwitchReset;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Intake;
import org.firstinspires.ftc.teamcode.Individuals.Timer;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainSystem;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Subsystems.IndexSystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSystem;
import org.firstinspires.ftc.teamcode.Individuals.Timer;



@TeleOp(name = "25730 TeleOp")
public class MainTeleOp extends OpMode{

    RobotMap robot = new RobotMap();
    Intake intake;
    Shooter shooter;
    DriveTrainSystem driveTrainSystem;
    LowServo lowIndex;
    LimitSwitch limitSwitch;
    LimitSwitch2 limitSwitch2;
    LimitSwitch3 limitSwitch3;
    Servo3 servo3;
    IndexSystem indexSystem;
    ShooterSystem shooterSystem;
    LimitSwitchReset limitSwitchReset;
    HexIndexMotor hexIndexMotor;
    Timer timer;

    private boolean servo = false;
    private double servoTime = 0;
/*
⣿⣿⣿⡇⠀⠀⣿⡄⠀⢘⣿⣿⣿⣿⣿⡿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⡿⠿⣿⣧⠀⠀⢿⡇⠀⠈⢿⣿⣿⣿⡿⠁⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⠀⢻⡷⠀⠀⢸⡇⠀⠀⣸⣿⣿⣿⠇⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣻⣿⣿⣯⣿⣿⣻⣽⣿⣽⣷⣿⣿⣽⣾⣿⣽⣾⣿⣿⣽⣿⢿⣻⣿⣿⣿⢿⣿⡏⠙⣿⣿⣿⡏⠀⢈⣿⡿⠻⣿⣿⣿
⠀⠀⠸⣟⠀⠀⠈⡇⠀⠀⣸⣿⣿⠏⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣟⣿⣿⣯⣿⣿⣽⣾⣿⣷⣿⣿⣿⣿⣿⣷⣿⡿⣿⣿⡿⣿⣻⣯⣿⣾⣿⢿⣿⣿⣿⣿⣾⣿⢿⣿⣿⡿⣿⣾⣿⣿⡇⠀⠾⣿⣿⡇⠀⠀⢻⡇⠀⢹⣿⣿
⡇⠀⠀⢻⠀⠀⠸⣿⠀⠀⢾⣿⡟⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⢿⡿⠟⠛⢿⣿⣿⣟⣿⣽⣿⣻⣿⣷⣿⣻⣽⣿⣾⢿⣽⣟⣿⣿⣳⣿⣟⣿⣻⣯⣿⣻⣿⣻⣾⢿⣽⣿⣻⣿⣟⣷⣿⣿⣟⣯⣿⣷⠀⠀⢸⣿⣇⠀⠀⠸⣿⠀⢸⣿⡛
⣷⠀⠀⠚⠀⠀⠀⠀⠀⠀⠀⠉⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠋⠀⢀⣠⣰⣆⣦⠀⠦⠀⡁⠻⣿⣿⣿⣽⡿⣷⡿⣽⣟⣿⡾⣿⣻⣯⡿⣟⣾⢿⣽⢯⣟⣯⣷⢿⣳⡿⣯⣟⣿⣻⡾⣿⣽⣾⣟⣯⣷⣿⢿⣻⣿⡆⠀⠀⣽⣿⡄⠀⠀⣿⠀⠀⣿⣅
⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⣀⡶⠆⠈⢀⠙⠛⢻⣷⣷⣧⣌⣳⣷⣬⢹⡹⣽⢯⣿⣻⡽⣷⢿⣻⣽⡷⣿⣻⣽⣟⡾⣟⣯⣷⣻⢯⡿⣽⢷⣻⣯⣷⢿⣻⢷⣟⣯⣿⣽⣾⡿⣟⣿⣿⡄⠀⠈⠉⠛⠀⠀⠈⠑⠀⠿⣯
⣏⠀⠀⠀⠀⠀⠀⠀⠀⠠⠞⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⢀⡶⠋⠀⠀⠀⠀⠀⠆⠁⢩⡩⢚⢽⢻⣿⣿⣷⣯⡽⣿⣾⣽⢿⡽⣯⢿⡾⣽⢷⣯⡷⣯⢿⡽⣞⣷⣻⢯⡿⣽⣻⢷⣻⣞⡿⣽⣻⣾⣻⢾⣳⣯⢿⡿⣽⣻⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿
⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⡿⠿⠛⠛⠛⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⡿⠁⣠⠊⠀⠀⠀⠀⠀⠀⠀⠄⠀⠂⠈⠀⠚⡜⣿⣿⣿⣿⣿⣞⣿⣿⢯⡿⣽⢯⡿⣽⣻⢾⡽⣯⢿⣽⡻⣞⡽⣯⣻⠷⣯⣟⡷⣯⣟⣯⢷⣯⣟⣯⣿⡾⡟⠛⠛⣿⣿⡇⣰⠃⢀⣤⠀⠀⠀⠀⠈⣉
⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣤⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠠⡀⠀⠀⠀⠀⠠⠄⠘⠹⣿⣿⣿⣿⣿⣾⣿⣯⣟⡽⣏⣟⣳⢯⡯⣷⢏⣿⣺⠽⣏⡿⡵⣏⡿⣵⠾⣽⣳⢻⣞⣟⣾⣹⣿⣵⣾⣧⣤⣤⣾⣽⣿⣯⡄⢸⡿⠆⡀⠀⠀⠀⠈
⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⢿⡅⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⣠⣤⠀⠀⠀⠀⣀⣽⡿⣿⣿⣿⣿⣿⣿⡷⣏⢿⣹⢮⡟⣧⡻⣵⡻⣞⣵⣻⡽⣳⣻⡭⣟⣞⡻⡵⢯⣛⡾⣞⣞⠷⣯⣟⠿⣏⡿⣻⣿⠏⠙⢛⢹⡆⡔⢹⣿⣦⡀⠀⠀
⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣷⣿⣿⣿⡇⠀⠀⢸⠁⠀⢀⣀⣀⣀⣶⡿⣇⠀⣀⣀⣾⣶⣾⣷⣶⣿⣿⣿⣿⣿⢷⣉⡿⣎⣷⡹⣾⢹⣶⢹⡾⣶⢇⡿⣱⢇⡿⣸⣎⣹⡹⣏⢷⣹⢹⡾⢿⣷⡾⢿⣇⢿⣱⢿⣷⣀⣾⣿⠇⠇⣾⣿⣿⠇⠀⠀
⣿⡁⠀⠐⠊⠀⠠⠤⠄⠀⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⢿⣯⣿⣷⠀⠀⠂⠀⣰⣿⣿⣿⡿⢿⣧⡇⠠⣿⣿⡿⣿⠿⢿⣿⣿⣿⣿⣿⣿⢣⣏⢾⡱⣎⠷⣭⡳⣎⠿⣜⢧⡻⣜⢧⡻⣜⡳⣎⢷⡹⢮⣳⢭⡗⣯⢳⢮⣝⡳⢮⡻⣜⠯⡽⣿⣹⣧⡴⡼⢟⣽⣿⣼⠇⣠
⣿⠀⠀⠀⠀⠀⠠⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⡿⣿⣯⣿⠿⣤⡞⠀⢰⣿⣿⣿⣿⡿⠟⠋⠀⠠⣿⣿⠛⠙⠿⢟⣿⣿⣿⣿⣿⣿⡗⣎⢧⣛⢾⡹⢶⡹⢎⡟⣼⢣⡟⣼⢣⡟⡼⢳⡹⢮⡝⣧⣛⢮⡝⣮⣛⠾⡼⣹⢣⠟⡼⣙⢮⡹⢿⡌⠻⠶⠞⢫⡿⠋⡆⢿
⣿⠀⠀⠀⠀⠀⠀⠀⢺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣯⣿⣿⣽⣷⣿⢿⣿⣿⣶⢯⠀⠀⠰⠏⠠⠎⢁⣤⠾⢀⣤⠤⠸⣿⣷⡄⠀⢶⣾⣿⣿⣿⣿⣿⡟⡜⢮⣱⢫⡝⣎⡳⢫⣜⢎⡳⣜⢣⣏⢼⡙⣧⢫⣓⡞⡥⢏⡶⣹⢦⡙⣧⠳⣍⢧⢫⠵⣩⠖⡍⣏⢿⣴⠧⠐⠋⠀⠀⡷⣾
⣇⠀⠀⢀⣀⣀⣀⣀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣷⣿⢿⣾⡿⣿⣿⢛⣸⠋⠃⠀⠐⠛⠛⠛⠁⢀⡾⠁⠀⠀⢸⣿⣧⡀⠐⠉⣰⣿⣿⣿⣿⡧⡝⢦⢇⠧⣛⢬⢳⡹⣌⢯⡱⢎⡳⡜⢦⡛⡴⢣⠳⣜⡹⢎⡼⡱⢎⡝⡲⡝⢎⡎⣇⠻⡐⢏⡜⢦⢋⣿⣤⣤⣤⣴⣾⣷⣿
⣹⠆⢊⠐⡱⣊⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣽⣾⣿⣷⣿⡿⣟⣯⣿⣯⢿⣟⣿⣯⣧⡀⠀⠀⢀⣤⡶⠃⣠⢿⣤⣴⣦⣶⣿⣿⣿⣿⣾⣾⣽⣿⣿⣿⣿⢣⡝⣒⠮⣙⢎⡎⢧⢓⡜⢦⡙⢮⡑⢮⡑⢮⢱⡋⢵⢊⡵⢩⡒⡝⢪⡜⣱⠩⢞⡰⢎⠳⣉⠖⣸⠡⢎⣻⣿⣿⣿⣿⣿⣿⣿
⣿⠀⣂⠡⡽⣱⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣟⣿⣿⣿⣿⢿⣯⣿⣾⣿⢿⣿⣽⣾⣟⣯⢿⣿⡎⠷⠤⠀⠸⠋⣰⡾⠋⠀⠀⠀⠀⠉⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣟⠢⡝⣌⢣⡍⡲⢜⢣⢋⡜⢦⡙⢦⡙⢦⡙⢆⡣⢜⡱⠎⡔⡣⠜⣌⠳⢌⠥⡛⢤⡙⣌⠣⣍⢚⠤⡛⢤⠛⣛⣫⢭⣭⣭⣭⣽
⣿⠀⢀⢋⣹⢳⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⢿⣷⣿⣿⣟⣯⣷⡿⣿⣳⣿⣞⣯⣟⣯⢿⣧⣰⡀⠀⢀⡴⠏⢀⣀⣤⣤⠤⠶⠶⠾⣶⣿⣯⡿⢿⣿⣿⣿⡏⣎⠱⡜⢤⠣⡜⡱⢊⠆⡧⢌⢣⡙⠦⡑⢦⢙⢢⡑⠎⡴⢉⠖⣡⢋⠤⢋⡜⢢⡙⠦⠱⣌⠓⡌⢎⡒⢍⠦⡙⣼⣯⣟⣾⣻⣿⢿
⣧⣤⣦⣼⣾⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⢿⣯⣿⣿⢿⣾⣟⣿⣻⣽⣿⣻⣽⡷⣯⣟⣾⡽⣯⣟⡿⣷⡀⢸⡇⣰⡟⠋⠀⠀⡀⠀⠄⣤⣴⣾⣽⣇⢸⣿⣿⠟⣌⡆⡏⡜⢆⠳⣌⠱⣉⠜⢤⠋⠴⣨⠱⡘⢆⡉⠆⢎⡑⠢⢍⠸⣀⠃⠎⢥⠘⠦⢌⠱⢃⠦⠩⠜⡰⡘⠌⢦⠡⢻⣿⣞⣮⢷⣫⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣽⣿⣿⣻⣿⣿⣻⣾⣿⣟⣯⣿⣻⣽⡾⣟⣷⣿⣻⣽⢾⣽⣳⢯⡿⣽⣿⠲⢷⣏⠀⠀⠀⠀⠀⠀⠀⣠⣾⣵⣿⣿⣿⣿⣯⡱⢢⠜⣢⠙⡬⢑⠢⡑⡌⡘⠦⡙⢢⠁⣆⠱⢌⡘⢌⠢⡘⢅⠊⡔⢠⠋⡘⢠⠩⠘⠤⡉⢌⠒⡩⢘⠡⡘⡘⢂⠹⣿⣿⣽⣾⣭⣳⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣟⣿⣿⣿⣿⣽⣿⢿⣾⡿⣿⣽⣾⣿⣽⡿⣽⣟⣿⣽⣾⣯⣟⡿⣾⣽⣯⡿⢋⣿⠂⢈⠻⢿⣶⣤⣤⣴⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡙⠤⣋⠒⡍⡒⠥⢂⡱⢁⠦⡁⢎⠰⢈⠆⡘⠄⡃⠌⣂⠑⡄⢃⠰⢁⠢⠡⢉⠂⡑⠈⡔⢡⠈⠆⡑⠨⠄⢹⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⢛⣿⡿⠟⣻⠟⡿⠻⠟⣻⠛⢿⢿⣿⣿⡿⣿⣿⣛⡿⠿⠟⢟⣧⠄⢸⣿⡄⠸⢷⣦⣿⣿⡛⠛⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣭⡰⢁⡓⠌⠄⠃⣔⣡⣆⣌⣤⣒⣈⠠⣁⡒⢀⠂⠄⢃⠰⠀⢂⠁⠢⠈⠄⠡⠐⠠⠈⠔⠠⠁⠌⢸⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀⢀⣼⣿⣿⣿⣧⣫⣤⣿⣾⣻⣬⣾⣿⣿⣾⣿⣅⣛⣿⣎⡝⣷⣿⣿⡧⣆⣂⣾⣿⣿⣶⡆⠀⠀⠈⠙⢷⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠒⠈⠄⠂⠘⠄⠈⠄⠂⠈⠐⠈⠀⡈⠀⡐⠈⠐⢸⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⣿⣷⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣡⢻⣿⣿⣿⣿⠁⠀⠀⢀⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⣁⣀⠀⠁⢀⠈⠐⠀⠈⠄⠀⠄⠠⠁⠁⢼⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⢀⣠⣾⣿⢿⢹⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣺⣿⣿⣿⣿⣦⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⠀⠀⠠⠀⠄⠢⠀⠀⠀⠀⠀⠀⣻⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⡿⣿⣿⣷⣺⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⣀⣀⣀⠀⠀⠀⠀⡐⠠⠁⢾⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣯⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣥⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣿⣹⢻⣿⠁⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣀⣠⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣷⣟⡿⣾⢸⢀⣿⡄⠼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⢾⡿⣿⣿⣿⣿⣿⣿⠟⣯⣿⣿⣿⣿⣿⡾⣽⢾⣝⣻⣻⡆⣿⡇⢹⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⢿⣿⣿⣿⣿⣿⣿⣣⣿⣿⣿⣿⢿⣷⡿⣟⣽⣯⣾⣳⣹⡯⣿⡇⢸⣿⣿⣿⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣷⣿⣿⣿⣿⣿⣻⣷⣿⣿⣷⣿⣿⣿⣿⣷⣻⣿⢸⣿⣿⣿⢿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⢀⣉⣛⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣷⣿⣿⣿⣽⢯⣟⣯⣿⣿⣿⣿⣿⣏⣿⣻⣿⣿⣿⣿⣸⣿⣿⣾⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⡻⣿⣿⡏⣯⣏⡛⣿⠟⣩⣉⡛⣿⠟⣋⣽⡙⢻⡏⢹⣿⣿⡟⢹⣿⣿⡏⣿⣽⡏⢹⣿⡿⢿⣯⣏⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⡿⠿⠛⠛⠛⠛
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢸⣿⢿⣿⡇⠹⠏⢱⣿⣶⡏⠹⢿⣿⢸⣿⣿⣿⡿⣿⢸⣿⣿⣿⢸⣿⣿⣇⣿⣿⣿⣿⣿⡇⢹⠿⠉⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠏⠉⠀⣾⠏⠀⠀⠀⠀⠀⢾
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠠⢹⣿⣮⠉⠉⠛⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣼⣿⣄⢻⡇⢿⣿⠗⣿⠛⢿⣿⢆⣿⡚⠿⣿⠟⣡⣿⠸⢿⣿⣿⡘⢿⡿⢋⣿⣿⣇⢹⣿⡏⢹⢿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⢀⡾⠁⠀⠀⠀⠀⠀⠀⣾
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣹⣿⣿⣷⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣷⣷⣶⣾⣿⣿⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣾⣷⣿⣿⣏⣿⣿⣿⣿⣶⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⣸⠇⠀⠀⠀⠀⠀⠀⢸⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⣿⣧⡽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣾⣾⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⢀⡿⠀⠀⠀⠀⠀⠀⠀⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠉⠁⠈⠉⠉⠛⢿⣿⣿⣿⡏⠉⠉⢻⣿⣿⠉⠉⠉⠻⣿⣿⣿⣻⣿⠉⠉⢹⣿⣿⣿⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⢹⣿⣿⡏⠉⠉⠉⢙⣿⣿⣿⣿⡿⠉⠉⠉⠉⢹⣿⣿⣿⣿⣿⣿⠋⠉⠉⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣸⠁⠀⠀⠀⠀⠀⠀⢼⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠁⢀⢀⣤⣴⣶⣤⡀⠀⠀⠹⣿⣿⡇⠀⡅⢸⣿⣿⡁⠀⠀⠀⠙⣿⣳⣿⣿⠀⠀⢸⣿⣿⣿⠀⠀⢹⣤⣤⣤⣤⣦⣦⣤⣾⣿⣿⡇⠀⢀⠀⠀⢻⣿⣿⣿⠇⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⡏⠀⢠⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⢀⡿⠀⠀⠀⠀⠀⠀⢸⢸⣷⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢠⣿⣿⣿⣿⣿⣿⣤⣴⣶⣿⣿⡇⠀⠀⢸⣿⣿⠀⠀⣧⠀⠀⠘⢿⣷⣿⠀⠀⢸⣿⣿⣿⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠘⡇⠀⠈⣿⣿⡿⠀⠀⣼⠀⠀⢸⣿⣿⣿⣿⡟⠀⡀⢸⡇⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠠⠀⣼⡇⠀⠀⠀⠀⠀⢀⠎⣻⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⠀⠀⠈⣷⡀⠀⠈⢿⣿⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠀⠐⣷⠀⠀⢸⣿⠃⠀⢰⡇⠀⠀⢸⣿⣿⣿⡿⠀⠀⢠⣿⣿⡄⠀⠀⢿⣿⣿⣿⣿⣿⣿⠁⠠⠰⢰⡟⠀⠀⠀⠀⠀⠀⢊⠖⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⠀⠀⠀⣿⣷⡄⠀⠀⢻⡄⠀⢸⣿⣿⣿⠀⠀⢰⣶⣶⣶⣶⣶⣶⣶⣿⣿⣿⡅⠀⠀⣿⣇⠀⠀⣿⠀⠁⣼⡇⠀⠀⢸⣿⣿⡿⠁⠀⠀⠛⠛⠛⠻⠀⠀⢨⢿⣿⣿⣿⣿⣿⣧⠀⠉⢼⡇⠀⠀⠀⠀⠀⠀⢬⣹⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠈⢿⣿⣿⣿⣿⠟⠀⠀⢉⣿⣿⡇⠀⠀⢸⣿⣿⠀⠀⠀⣿⣿⣿⣄⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⢸⣯⣽⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⣿⣿⠀⠀⠀⠀⢀⣿⡇⠀⠀⢸⣿⣿⠇⠀⢰⣀⣠⣀⣀⣀⣀⠀⠀⠘⣿⣿⣿⣿⣿⣿⡇⠨⢼⡇⠀⠀⠀⠀⠀⢣⢚⣼⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷  ⣄⠀⠀⠉⠙⠋⠁⠀⠀⣠⣾⣿⣿⡇⠀⠀⢸⣿⣿⠀⠀⠀⣿⣿⣿⣿⣦⠀⠀⠀⢠⣟⣿⣽⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⣿⣿⠇⠀⠀⣿⣿⡇⠀⠀⠀⣾⣿⡇⠀⠀⢸⣿⠏⠀⠀⢸⣿⣿⣿⣿⣿⣿⡆⠀⠀⠹⣿⣿⣿⣿⣿⡇⡘⣼⡇⠀⠀⢀⠀⢀⢣⢫⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣤⣀⣤⣶⣾⣿⣿⣿⣿⣧⣤⣤⣼⣿⣿⣤⣤⣤⣿⣿⣿⣿⣿⣷⣦⣤⣼⣿⣿⣿⣤⣤⣤⣤⣤⣦⣤⣤⣤⣤⣤⣿⣿⣦⣤⣤⣿⣿⣿⣤⣤⣴⣿⣿⣧⣤⣤⣼⣿⣤⣤⣤⣿⣿⣿⣿⣿⣿⣿⣿⣦⣤⣤⣽⣿⣿⣿⣿⡇⡜⢼⡇⠀⠀⡌⣇⠈⡇⡷⣿⣿⣿⣿
*/

    // Initialization code for all files
    @Override
    public void init(){
       robot.init(hardwareMap);
       timer = new Timer(robot);
       intake = new Intake(robot);
       shooter = new Shooter(robot);
       driveTrainSystem = new DriveTrainSystem(robot);
       limitSwitch = new LimitSwitch(robot);
       limitSwitch2 = new LimitSwitch2(robot);
       limitSwitch3 = new LimitSwitch3(robot);
       lowIndex = new LowServo(robot);
       servo3 = new Servo3(robot);
       indexSystem = new IndexSystem(robot);
       shooterSystem = new ShooterSystem(robot);
       limitSwitchReset = new LimitSwitchReset(robot, limitSwitch, limitSwitch2, limitSwitch3);
       hexIndexMotor = new HexIndexMotor(robot);
       robot.shooterBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.shooterFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

       robot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       limitSwitchReset.resetLimitSwitches();
       timer.reset();
    }

    // Main code and functions go here
    // Last year we didn't do this, but it's good to have buttons in one file
    @Override
    public void loop(){

        indexSystem.intakeBall(-1, 1, 1, 1, 1, gamepad1.right_trigger);

        shooterSystem.shootBall(900, 900, 1,1,1,1, gamepad1.left_trigger);

        if(gamepad1.yWasPressed()){
            servo3.setPower(-1);
            hexIndexMotor.setPower(-1);
            servoTime = timer.timer();
            servo = true;
        }

        if(servo && timer.timer() - servoTime >=2){
            servo3.stop();
            hexIndexMotor.stop();
            servo = false;
        }

        if(gamepad1.dpad_left){
            shooterSystem.cancelShooting();
        }

        if (gamepad1.b){
            indexSystem.cancelIndex();
        }

        if(gamepad1.a){
            intake.setPower(1);
        }

        //mecanum drivetrain
        driveTrainSystem.Drive(-gamepad1.right_stick_x,  gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_bumper, gamepad1.left_bumper);

        //print functions
        //print functions
        telemetry.addData("front shooter velocity", robot.shooterFront.getVelocity());
        telemetry.addData("back shooter velocity", robot.shooterBack.getVelocity());
        telemetry.addData("index state", indexSystem.state);
        telemetry.addData("shooter state", shooterSystem.state);
        telemetry.addData("voltage", robot.voltageSensor.getVoltage());
        telemetry.addData("f value", shooter.setF());
        telemetry.addData("limitswitch 3", limitSwitch3.get_value());
        telemetry.update();

    }

}
