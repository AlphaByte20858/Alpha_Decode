package org.firstinspires.ftc.teamcode.opmode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name = "Shin", group = "Teste")
public class SHIN extends LinearOpMode{

    public DcMotorEx MSH,MIT    ;
    ElapsedTime time = new ElapsedTime();


    @Override
    public void runOpMode() {


        MSH = hardwareMap.get(DcMotorEx.class,"MSH");
        MIT = hardwareMap.get(DcMotorEx.class,"MIT");


        waitForStart();

           boolean triggerprs = false;
        boolean shon = false;

        while (opModeIsActive()) {


            double triggerrt = gamepad2.right_trigger;
            if (time.seconds() > 2) {


        if (triggerrt > 0.05 && !triggerprs) {
            shon = !shon;
            triggerprs = true;
        } else if (triggerrt > 0.05 && triggerprs) {
            triggerprs = false;
            shon = true;
        }
    }
            if (shon){
        MSH.setPower(0.8);
    }
            else {
        MSH.setPower(0);
    }

            if (gamepad2.left_bumper){
        MIT.setPower(-0.6);
    }else {
        MIT.setPower(0);
    }
}
    }
}
