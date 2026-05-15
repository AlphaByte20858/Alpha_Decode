package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
@TeleOp(name = "shooter")
public class ShooterTest extends LinearOpMode {

    private DcMotorEx MSH,MSH2;

    public static double P = 12.6;
    public static double I = 0;
    public static double D = 0;
    public static double F = 13.34;
    public static double targetVelocity = 1680.0;


    @Override
    public void runOpMode() throws InterruptedException {

        MSH = hardwareMap.get(DcMotorEx.class,"MSH");
        MSH2 = hardwareMap.get(DcMotorEx.class,"MSH2");

        MSH.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MSH.setDirection(DcMotorSimple.Direction.REVERSE);
        MSH2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MSH2.setDirection(DcMotorSimple.Direction.FORWARD);
        MSH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MSH2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        PIDFCoefficients pidf = new PIDFCoefficients(P,I,D,F);

        MSH.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);
        MSH2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);


        waitForStart();

        while (opModeIsActive()){

            if (gamepad2.x){

                MSH.setVelocity(targetVelocity);
                MSH2.setVelocity(targetVelocity);

            }else if(gamepad2.y){

                MSH.setVelocity(0.0);
                MSH2.setVelocity(0.0);

            }

            telemetry.addData("targetVelocity",targetVelocity);
            telemetry.addData("Vel Real MSH",MSH.getVelocity());
            telemetry.addData("Power MSH",MSH.getPower());
            telemetry.update();

        }

    }
}