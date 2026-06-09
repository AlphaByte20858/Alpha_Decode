package org.firstinspires.ftc.teamcode.opmode.tests;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Angulador {
   private Servo servo;

    public Angulador(HardwareMap hardwareMap){
        servo = hardwareMap.get(Servo.class,"servo");
    }
    void torret(double valor){
        servo.setPosition(valor);
    }


            void stage3(){
                torret(0);
            }

             void stage0(){
                 torret(0.65);
             }

             void stage1(){
                 torret(0.5);
             }

             void stage2(){
                torret(0.25);
            }

        }

