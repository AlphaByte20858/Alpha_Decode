package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.robot.RobotHardware;
import org.firstinspires.ftc.teamcode.hardware.subsytems.DriveBaseSubsytem;

@TeleOp(name = "Movi")
public class TeleOpDrive extends LinearOpMode {

    private RobotHardware robot;
    private DriveBaseSubsytem driveBase;

    @Override
    public void runOpMode() {

        robot    = new RobotHardware(this);
        driveBase = new DriveBaseSubsytem(robot);

        robot.init();
        driveBase.init();

        waitForStart();

        while (opModeIsActive()) {

            float axial   = -gamepad1.left_stick_y; 
            float lateral =  gamepad1.left_stick_x;
            float yaw     =  gamepad1.right_stick_x;

            driveBase.manualControl(axial, lateral, yaw);

        }
    }
}
