package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.interfaces.SubsystemBase;
import org.firstinspires.ftc.teamcode.hardware.robot.RobotHardware;

public class TorretSubsystem implements SubsystemBase {
    private RobotHardware robot;
    private ElapsedTime timer = new ElapsedTime();


    public TorretSubsystem(RobotHardware robot) {
        this.robot = robot;
    }

    @Override
    public void init() {
        
        robot.servo.setPosition(0.5); 
    }

    @Override
    public void periodic() {
        
    }

    
    public void setTorretPosition(double position) {
        robot.servo.setPosition(position);
    }
    
    
    public void stageI() {
        setTorretPosition(0.25);
    }
    
    public void stageII() {
        setTorretPosition(0.10);
    }
}
