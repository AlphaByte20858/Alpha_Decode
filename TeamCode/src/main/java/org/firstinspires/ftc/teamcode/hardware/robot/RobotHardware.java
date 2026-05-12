package org.firstinspires.ftc.teamcode.hardware.robot;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
public class RobotHardware {


    OpMode opMode;
    public DcMotorEx MDT, MDF, MET, MEF, MSH, MSHII, MIT;
    double axial, lateral, yaw,angle;
    public Servo servo; //Define o nome dos servos no sistema
    public IMU imu;


    public RobotHardware(OpMode opMode) {
        MET = opMode.hardwareMap.get(DcMotorEx.class, "MET");
        MDT = opMode.hardwareMap.get(DcMotorEx.class, "MDT");
        MDF = opMode.hardwareMap.get(DcMotorEx.class, "MDF");
        MEF = opMode.hardwareMap.get(DcMotorEx.class, "MEF");
        MSH = opMode.hardwareMap.get(DcMotorEx.class, "MSH");
        MSHII = opMode.hardwareMap.get(DcMotorEx.class, "MSHII");
        MIT = opMode.hardwareMap.get(DcMotorEx.class, "MIT");

        MDF.setDirection(DcMotorSimple.Direction.FORWARD);
        MDT.setDirection(DcMotorSimple.Direction.FORWARD);
        MDT.setDirection(DcMotorSimple.Direction.FORWARD);
        MET.setDirection(DcMotorSimple.Direction.REVERSE);
        MEF.setDirection(DcMotorSimple.Direction.REVERSE);
        MSH.setDirection(DcMotorSimple.Direction.FORWARD);
        MSHII.setDirection(DcMotorSimple.Direction.REVERSE);

        MDF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MDT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MET.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MEF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        MDF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MDT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MET.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MEF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MSH.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MSHII.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        MSH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MSHII.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        //define o meio de "freio" para os motores
        imu = opMode.hardwareMap.get(IMU.class,"imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.UP;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();
    }
}
