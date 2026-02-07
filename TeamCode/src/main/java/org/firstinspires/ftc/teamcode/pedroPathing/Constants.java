package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
        .mass(15);

     public static MecanumConstants driveConstants = new MecanumConstants()
        .maxPower(1)
        .rightFrontMotorName("MDF")
        .rightRearMotorName("MDT")
        .leftRearMotorName("MET")
        .leftFrontMotorName("MEF")
        .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
        .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
        .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
        .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)

  public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-5) //definir distancia
            .strafePodX(0.5) //definir distancia
            .distanceUnit(DistanceUnit.MM) //mudei a unidade de distancia de polegadas para milimetros
            .hardwareMapName("pinpoint") //conferir
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD) //ver se a resolucao ta correta
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD) //testar pra ver se vai ser necessario inverter algum deles
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDriveTrain(driveConstants)
                .build();
    }
}
