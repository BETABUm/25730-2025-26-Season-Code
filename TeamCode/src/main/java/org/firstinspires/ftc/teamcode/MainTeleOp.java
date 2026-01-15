package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Individuals.HexIndexMotor;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch2;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch3;

import org.firstinspires.ftc.teamcode.Individuals.LimitSwitchReset;
import org.firstinspires.ftc.teamcode.Individuals.Servo3;
import org.firstinspires.ftc.teamcode.Individuals.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainSystem;
import org.firstinspires.ftc.teamcode.Individuals.LowServo;
import org.firstinspires.ftc.teamcode.Individuals.Shooter;
import org.firstinspires.ftc.teamcode.Individuals.LimitSwitch;
import org.firstinspires.ftc.teamcode.Subsystems.IndexSystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSystem;


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
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠉⠙⠋⠁⠀⠀⣠⣾⣿⣿⡇⠀⠀⢸⣿⣿⠀⠀⠀⣿⣿⣿⣿⣦⠀⠀⠀⢠⣟⣿⣽⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⣿⣿⠇⠀⠀⣿⣿⡇⠀⠀⠀⣾⣿⡇⠀⠀⢸⣿⠏⠀⠀⢸⣿⣿⣿⣿⣿⣿⡆⠀⠀⠹⣿⣿⣿⣿⣿⡇⡘⣼⡇⠀⠀⢀⠀⢀⢣⢫⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣤⣀⣤⣶⣾⣿⣿⣿⣿⣧⣤⣤⣼⣿⣿⣤⣤⣤⣿⣿⣿⣿⣿⣷⣦⣤⣼⣿⣿⣿⣤⣤⣤⣤⣤⣦⣤⣤⣤⣤⣤⣿⣿⣦⣤⣤⣿⣿⣿⣤⣤⣴⣿⣿⣧⣤⣤⣼⣿⣤⣤⣤⣿⣿⣿⣿⣿⣿⣿⣿⣦⣤⣤⣽⣿⣿⣿⣿⡇⡜⢼⡇⠀⠀⡌⣇⠈⡇⡷⣿⣿⣿⣿
*/

    // Initialization code for all files
    @Override
    public void init(){
       robot.init(hardwareMap);
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
       limitSwitchReset = new LimitSwitchReset(robot);
       hexIndexMotor = new HexIndexMotor(robot);
       robot.shooterBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.shooterFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

       robot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       robot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       limitSwitchReset.resetLimitSwitches();
    }

    // Main code and functions go here
    // Last year we didn't do this, but it's good to have buttons in one file
    @Override
    public void loop(){
        // all buttons like a,b,x,y, etc are all booleans
        if (gamepad1.right_trigger >= .69){
            indexSystem.intakeBall(-1, 1, 1, 1, 1);
        }

        // right trigger is like a stick with values from 0 to 1, same with left trigger
        if(gamepad1.right_trigger < .69){
            indexSystem.state = IndexSystem.IndexStatesThreeBalls.INTAKEON;
        }

        if (gamepad1.b){
            indexSystem.stopAll();
        }

        //stops intake once it gets to target pos
        if ((robot.intake.getCurrentPosition() <= robot.intake.getTargetPosition() + 100) && (robot.intake.getCurrentPosition() > robot.intake.getTargetPosition()-100)) {
            intake.stop();
        }

        //stops shooter once it reaches target pos
        if (shooter.check_position()) {
            shooter.stop();
        }

        //mecanum drivetrain
        driveTrainSystem.Drive(-gamepad1.right_stick_x,  gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_bumper, gamepad1.left_bumper);

        //print functions
        telemetry.addData("front shooter velocity", robot.shooterFront.getVelocity());
        telemetry.addData("back shooter velocity", robot.shooterBack.getVelocity());
        telemetry.update();

    }

}
