package org.firstinspires.ftc.teamcode.opmode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.interfaces.OptimizedTeleOp;
import org.firstinspires.ftc.teamcode.hardware.robot.RobotHardware;
import org.firstinspires.ftc.teamcode.hardware.robot.RobotTelemetry;
import org.firstinspires.ftc.teamcode.hardware.subsytems.DriveBaseSubsytem;

@TeleOp(name = "Astrid")
public class TeleopWithOOP extends OptimizedTeleOp {

    RobotHardware Robot;
    DriveBaseSubsytem DriveBase;
    RobotTelemetry robotTelemetry;

    public void init(){

        Robot = new RobotHardware(this);
        DriveBase = new DriveBaseSubsytem(Robot);
        robotTelemetry = new RobotTelemetry(Robot);
        DriveBase.init();
        robotTelemetry.init();
    }
    public void loop(){
        DriveBase.periodic();
        keybinds();
        robotTelemetry.periodic();
    }

    public void keybinds(){
        gamepad1Keybinds();
        gamepad2Keybinds();
    }


    public void gamepad1Keybinds(){
        DriveBase.robotGyroMove(gamepad1.right_trigger-gamepad1.left_trigger,gamepad1.left_stick_x, gamepad1.right_stick_x);
    }

    public void gamepad2Keybinds() {


        if (gamepad2.a){
        }
    }


}
