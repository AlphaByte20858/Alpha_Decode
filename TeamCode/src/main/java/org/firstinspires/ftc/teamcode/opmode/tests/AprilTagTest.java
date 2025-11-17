package org.firstinspires.ftc.teamcode.opmode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.subsytems.AprilTagCam;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous
public class AprilTagTest extends OpMode {

    AprilTagCam aprilTagCam = new AprilTagCam();
    @Override
    public void init() {
        aprilTagCam.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        //atualiza o vision portal
        aprilTagCam.update();
        AprilTagDetection id21 = aprilTagCam.getTagID(21);
        telemetry.addData("id21 String", id21.toString());

    }
}