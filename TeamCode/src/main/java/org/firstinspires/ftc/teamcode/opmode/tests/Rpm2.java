package org.firstinspires.ftc.teamcode.opmode.tests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "funciona" )
public class Rpm2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotorEx MSH = hardwareMap.get(DcMotorEx.class, "MSH");
        MSH.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MSH.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        final double TICKS_POR_ROTACAO = 28 ;
        final double RPM_MAX = 6000.0;

        ElapsedTime timer = new ElapsedTime();
        double ultimoTempo = 0;
        int ultimaPosicao = 0;


        waitForStart();
        timer.reset();

        while (opModeIsActive()) {


            if (gamepad2.right_trigger > 0.1) {
                MSH.setPower(0.8);
            } else {
                MSH.setPower(0.0);
            }

            double tempoAtual = timer.seconds();
            int posicaoAtual = MSH.getCurrentPosition();

            double deltaTempo = tempoAtual - ultimoTempo;
            int deltaTicks = posicaoAtual - ultimaPosicao;

            if (deltaTempo > 0.1) {

                double rotacoesPorSegundo = (deltaTicks / TICKS_POR_ROTACAO) / deltaTempo;
                double rpm = rotacoesPorSegundo * 60.0;


                if (rpm > RPM_MAX) rpm = RPM_MAX;
                if (rpm < 0) rpm = 0;

                telemetry.addData("Ticks totais", posicaoAtual);
                telemetry.addData("RPM", "%.2f", rpm);
                telemetry.addData("Potência",MSH.getPower());
                telemetry.update();

                ultimoTempo = tempoAtual;
                ultimaPosicao = posicaoAtual;
            }
        }
    }
}

