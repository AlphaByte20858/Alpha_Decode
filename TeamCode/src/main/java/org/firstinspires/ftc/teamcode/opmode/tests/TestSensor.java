package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.teamcode.opmode.tests.Pid2;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
@TeleOp(name = "TesteSpindex")
public class TestSensorPID extends OpMode {

    private DcMotorEx MIT;
    private DistanceSensor sensor;

    public static double velKp = 35.0;
    public static double velKi = 0;
    public static double velKd = 0.01;
    public static double velKf = 30.0;
    public static double posKp = 5.0;
    public static int    OFFSET_TICKS  = 180;
    public static double VELOCIDADE_MAX = 0.5;

    private boolean detectLast = false;

    @Override
    public void init() {
        MIT    = hardwareMap.get(DcMotorEx.class,      "MIT");
        sensor = hardwareMap.get(DistanceSensor.class, "sensor");

        MIT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MIT.setTargetPosition(0);
        MIT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        MIT.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        MIT.setVelocityPIDFCoefficients(velKp, velKi, velKd, velKf);
        MIT.setPositionPIDFCoefficients(posKp);
    }

    @Override
    public void loop() {
        double  distancia  = sensor.getDistance(DistanceUnit.CM);
        boolean detectNow  = (distancia > 1 && distancia < 9);

        if (detectNow && !detectLast) {
            
            MIT.setVelocityPIDFCoefficients(velKp, velKi, velKd, velKf);
            MIT.setPositionPIDFCoefficients(posKp);

            int destino = MIT.getCurrentPosition() + OFFSET_TICKS;
            MIT.setTargetPosition(destino);
            MIT.setPower(VELOCIDADE_MAX);
        }

        detectLast = detectNow;

        telemetry.addData("Distancia (cm)",      distancia);
        telemetry.addData("Posicao do motor",    MIT.getCurrentPosition());
        telemetry.addData("Destino do motor",    MIT.getTargetPosition());
        telemetry.addData("Motor ocupado?",      MIT.isBusy());
        telemetry.update();
    }
}
