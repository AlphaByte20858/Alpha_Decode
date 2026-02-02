package org.firstinspires.ftc.teamcode.opmode.tests;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TorretFunciona")
public class ServoTest extends LinearOpMode {
    Servo servo;
    ElapsedTime time = new ElapsedTime();
    void torret(double valor){
        servo.setPosition(valor);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        servo = hardwareMap.get(Servo.class,"servo");

        waitForStart();
        while (opModeIsActive()){

            if (gamepad2.dpad_up  ){
                torret(0.10);
            } else if (gamepad2.dpad_down) {
                torret(0.7);
            } else if (gamepad2.dpad_left) {
                torret(0.5);
            }else if (gamepad2.dpad_right){
                torret(0.25);
            }

        }
    }
}
