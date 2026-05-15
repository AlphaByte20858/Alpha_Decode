package org.firstinspires.ftc.teamcode.hardware.subsytems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Testmovi")
public class MecanumMoveSubsystem extends LinearOpMode {

    private DcMotor MEF = null;
    private DcMotor MDF = null;
    private DcMotor MET = null;
    private DcMotor MDT = null;

    @Override
    public void runOpMode() {

        MEF = hardwareMap.get(DcMotor.class, "MEF");
        MDF = hardwareMap.get(DcMotor.class, "MDF");
        MET = hardwareMap.get(DcMotor.class, "MET");
        MDT = hardwareMap.get(DcMotor.class, "MDT");

        MEF.setDirection(DcMotorSimple.Direction.REVERSE);
        MET.setDirection(DcMotorSimple.Direction.REVERSE);
        MDF.setDirection(DcMotorSimple.Direction.FORWARD);
        MDT.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {

            double axial   = -gamepad1.left_stick_y * 0.9;
            double lateral =  gamepad1.left_stick_x * 0.9;
            double yaw     =  gamepad1.right_stick_x * 0.7;

            double pMEF = axial + lateral + yaw;
            double pMDF = axial - lateral - yaw;
            double pMET = axial - lateral + yaw;
            double pMDT = axial + lateral - yaw;

            double max = Math.max(Math.abs(pMEF), Math.abs(pMDF));
            max = Math.max(max, Math.abs(pMET));
            max = Math.max(max, Math.abs(pMDT));

            if (max > 1.0) {
                pMEF /= max;
                pMDF /= max;
                pMET /= max;
                pMDT /= max;
            }

            double multiplicador = gamepad1.right_bumper ? 0.4 : 1.0;


            MotorsPower(pMEF * multiplicador, pMDF * multiplicador,
                    pMET * multiplicador, pMDT * multiplicador);


            telemetry.addData("Inputs", "A:%.2f, L:%.2f, Y:%.2f", axial, lateral, yaw);
            telemetry.addData("Motores", "EF:%.2f, DF:%.2f, ET:%.2f, DT:%.2f", pMEF, pMDF, pMET, pMDT);
            telemetry.update();
        }
    }


    public void MotorsPower(double p1, double p2, double p3, double p4) {
        MEF.setPower(p1);
        MDF.setPower(p2);
        MET.setPower(p3);
        MDT.setPower(p4);
    }
}