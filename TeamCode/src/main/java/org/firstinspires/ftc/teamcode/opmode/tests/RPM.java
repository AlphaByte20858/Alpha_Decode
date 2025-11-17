package org.firstinspires.ftc.teamcode.opmode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "RPM")
public class RPM extends LinearOpMode {

    private DcMotor MSH;

    @Override
    public void runOpMode() throws InterruptedException {
        MSH = hardwareMap.get(DcMotor.class, "MSH");
        MSH.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MSH.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        final int Ticks_per_REV = 28;
        MSH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        long lastTime = System.currentTimeMillis();
        int lastTicks = MSH.getCurrentPosition();


            while (opModeIsActive()) {

                if (gamepad2.right_trigger > 0.1){

                    MSH.setPower(0.8);

                int currentTicks = MSH.getCurrentPosition();
                long currentTime = System.currentTimeMillis();

                int deltaTicks = currentTicks - lastTicks;
                double deltaTime = (currentTime - lastTime) / 1000.0; // em segundos

                if (deltaTime > 0);
                // ⚙️ Cada rotação = 4 ticks no motor HD Hex
                double rotacoesPorSegundo = (deltaTicks / (double)Ticks_per_REV) / deltaTime;
                double rpm = rotacoesPorSegundo * 60;

                telemetry.addData("Ticks totais", currentTicks);
                telemetry.addData("RPM", rpm);
                telemetry.update();

                // atualiza pra próxima leitura
                lastTicks = currentTicks;
                lastTime = currentTime;
            }else {
                    MSH.setPower(0);
                }
        }

        }
}