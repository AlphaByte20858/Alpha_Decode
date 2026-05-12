package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config

public class PIDMSH {
    private DcMotorEx MSH,MSH2;
    private Telemetry telemetry;

    public static double P = 30.67;
    public static double I = 0;
    public static double D = 0.1;
    public static double F = 11.50;
    public static double targetVelocity = 1680.0;

    public PIDMSH(HardwareMap hardwareMap, Telemetry telemetry){
       this.telemetry = telemetry;

        MSH = hardwareMap.get(DcMotorEx.class,"MSH");
        MSH2 = hardwareMap.get(DcMotorEx.class,"MSH2");

        MSH.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MSH.setDirection(DcMotorSimple.Direction.REVERSE);
        MSH2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MSH2.setDirection(DcMotorSimple.Direction.FORWARD);
        MSH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MSH2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double velocity = MSH.getCurrentPosition();
        double error = targetVelocity - velocity;

        PIDFCoefficients pidf = new PIDFCoefficients(P,I,D,F);

        MSH.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);
        MSH2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);



        }

        void ligar(){

            MSH.setVelocity(targetVelocity);
            MSH2.setVelocity(targetVelocity);

            telemetry.addData("TargetVel ", targetVelocity);
            telemetry.addData("Vel atual: ", MSH.getVelocity());
            telemetry.addData("power ", MSH.getPower());
            telemetry.update();
        }

        void desligar(){
        MSH.setVelocity(0);
        MSH2.setVelocity(0);
        }

        public void telemetria(){
            double v1 = MSH.getVelocity();
            double v2 = MSH2.getVelocity();

            telemetry.addData("Target: ", targetVelocity);
            telemetry.addData("Vel atual : ",v1);
            telemetry.addData("Vel atual2: ", v2);
            telemetry.addData("Erro: ", targetVelocity - v1);

        }

    }


