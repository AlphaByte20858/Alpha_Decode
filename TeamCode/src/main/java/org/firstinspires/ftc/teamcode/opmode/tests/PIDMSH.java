package org.firstinspires.ftc.teamcode.opmode.tests;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
@TeleOp(name = "pid2")
public class Pid2 extends LinearOpMode {

    private DcMotorEx MSH,MSHII;

    public static double P = 12.6;
    public static double I = 0;
    public static double D = 0;
    public static double F = 13.34;
    public static double targetVelocity = 1680.0;


    @Override
    public void runOpMode() throws InterruptedException {

        MSH = hardwareMap.get(DcMotorEx.class,"MSH");
        MSHII = hardwareMap.get(DcMotorEx.class,"MSHII");

        MSH.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MSH.setDirection(DcMotorSimple.Direction.FORWARD);
        MSHII.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MSHII.setDirection(DcMotorSimple.Direction.REVERSE);
        MSH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MSHII.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        PIDFCoefficients pidf = new PIDFCoefficients(P,I,D,F);

       MSH.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);
       MSHII.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);

        //pidf = MSH.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        //pidf = MSHII.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()){

            if (gamepad2.x){

                MSH.setVelocity(targetVelocity);
                MSHII.setVelocity(targetVelocity);

            }else if(gamepad2.y){

                MSH.setVelocity(0.0);
                MSHII.setVelocity(0.0);

            }

            telemetry.addData("targetVelocity",targetVelocity);
            telemetry.addData("Vel Real MSH",MSH.getVelocity());
            telemetry.addData("Power MSH",MSH.getPower());
            telemetry.update();

        }

    }
}
