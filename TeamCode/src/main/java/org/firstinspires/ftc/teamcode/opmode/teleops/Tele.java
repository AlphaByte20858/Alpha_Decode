package org.firstinspires.ftc.teamcode.opmode.teleops;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "Funplease")
public class Tele extends OpMode {
    DcMotorEx MDT, MDF, MET, MEF, MSH,MIT;
    double angle, axial, lateral, yaw;
    //Servo yawC, garra; //Define o nome dos servos no sistema
    boolean yawG, raw, motionType;
    ElapsedTime f = new ElapsedTime(); //define contador de tempo para as funções
    ElapsedTime tempo = new ElapsedTime(); // define  o contador do tempo decorrido para o PID

    IMU imu;
    boolean isOrientedTrue = false;

    double yawBase = 0, yawChanged = 0.37;

    public void init() {
        MET = hardwareMap.get(DcMotorEx.class, "MET");
        MDT = hardwareMap.get(DcMotorEx.class, "MDT");
        MDF = hardwareMap.get(DcMotorEx.class, "MDF");
        MEF = hardwareMap.get(DcMotorEx.class, "MEF");
        MSH = hardwareMap.get(DcMotorEx.class,"MSH");
        MIT = hardwareMap.get(DcMotorEx.class,"MIT");
        imu = hardwareMap.get(IMU.class, "imu");


        MDF.setDirection(DcMotorSimple.Direction.FORWARD);
        MDT.setDirection(DcMotorSimple.Direction.FORWARD);
        MDT.setDirection(DcMotorSimple.Direction.FORWARD);
        MET.setDirection(DcMotorSimple.Direction.REVERSE);
        MEF.setDirection(DcMotorSimple.Direction.REVERSE);
        MIT.setDirection(DcMotorSimple.Direction.REVERSE);


        MDF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MDT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MET.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MEF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MSH.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        MDF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MDT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MET.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MEF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MSH.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        MSH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Orientação do Control Hub/Expansion Hub
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        // Inicializa o giroscópio
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();

        //yawC.setPosition(0);
        motionType = true;
        //yawG = false;
        raw = false;

    }

    public void loop(){


        double triggerinv = gamepad2.left_trigger ;
        boolean triggerprs = false;
        boolean shon = false;
        double triggerrt = gamepad2.right_trigger;


        if (triggerrt > 0.1){

            MSH.setPower(0.8);


            final double TICKS_POR_ROTACAO = 28 ;
            final double RPM_MAX = 6000.0;

            ElapsedTime timer = new ElapsedTime();
            double ultimoTempo = 0;
            int ultimaPosicao = 0;

            double tempoAtual = timer.seconds();
            int posicaoAtual = MSH.getCurrentPosition();

            double deltaTempo = tempoAtual - ultimoTempo;
            int deltaTicks = posicaoAtual - ultimaPosicao;


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


        }else if (triggerinv > 0.1){

            MSH.setPower(-0.8);

        }else {
            MSH.setPower(0);
        }

        if (gamepad2.left_bumper){
            MIT.setPower(0.8);
        }

        else if (gamepad2.right_bumper) {
            MIT.setPower(-0.8);
        }

        else {
            MIT.setPower(0);
        }



        movi();


    }
    public void movi(){
        telemetry.addData("Robot + field centric", motionType);
        if (motionType){
            defaultMove();
        }
        else{
            centricMove();
        }
    }

    public void defaultMove(){
        axial   = (gamepad1.right_trigger - gamepad1.left_trigger)* 0.8;
        lateral = gamepad1.left_stick_x * 0.8;
        yaw     =  gamepad1.right_stick_x * 0.6;


        double absaxial = Math.abs(axial);
        double abslateral = Math.abs(lateral);
        double absyaw= Math.abs(yaw);
        double denominador = Math.max(absaxial + abslateral + absyaw, 1);
        double motorEsquerdoFf = (axial + lateral + yaw / denominador);
        double motorDireitoFf = (axial - lateral - yaw / denominador);
        double motorEsquerdoTf = (axial - lateral + yaw / denominador);
        double motorDireitoTf = (axial + lateral - yaw / denominador);

        if(gamepad1.right_bumper){
            MotorsPower(motorEsquerdoFf * 0.8, motorDireitoFf * 0.8, motorEsquerdoTf * 0.8, motorDireitoTf * 0.8);
        }
        else {
            MotorsPower(motorEsquerdoFf, motorDireitoFf, motorEsquerdoTf, motorDireitoTf);
        }
    }
    public void centricMove(){
        robotGyroMove(gamepad1.right_trigger-gamepad1.left_trigger,gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
    public void robotGyroMove(float axialPower, float lateralPower, float yawPower){
        axial = axialPower * 0.8;
        lateral = lateralPower;
        yaw = yawPower *0.7;

        if((yaw!=0) && (!isOrientedTrue)) {
            isOrientedTrue= true;
            imu.resetYaw();
        }
        if (isOrientedTrue){
            fieldOriented(axial,lateral);
        }
        if((yaw==0) && (isOrientedTrue)) {
            isOrientedTrue= false;
        }


        double absaxial = Math.abs(axial);
        double abslateral = Math.abs(lateral);
        double absyaw= Math.abs(yaw);
        double denominador = Math.max(absaxial + abslateral + absyaw, 1);
        double motorEsquerdoFf = ((axial + lateral + yaw) / denominador);
        double motorDireitoFf = ((axial - lateral - yaw) / denominador);
        double motorEsquerdoTf = ((axial - lateral + yaw) / denominador);
        double motorDireitoTf = ((axial + lateral - yaw) / denominador);

        MotorsPower(motorEsquerdoFf, motorDireitoFf, motorEsquerdoTf, motorDireitoTf);
    }
    private void fieldOriented(double driveP, double turnP) {
        angle = gyroCalculate();
        axial = driveP * Math.cos(angle) - turnP * Math.sin(angle);
        lateral = driveP * Math.sin(angle) + turnP * Math.cos(angle);
    }

    private double gyroCalculate() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.RADIANS);
    }

    public void MotorsPower(double p1, double p2, double p3,double p4){
        MEF.setPower(p1);
        MDF.setPower(p2);
        MET.setPower(p3);
        MDT.setPower(p4);
    }
}

