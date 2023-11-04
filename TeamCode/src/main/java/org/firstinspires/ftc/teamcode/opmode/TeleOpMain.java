package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "TeleOpMain", group = "TeleOp")
public class TeleOpMain extends OpMode {

    SampleMecanumDrive mecanumDrive;
    ArmSubsystem armSubsystem;

    @Override
    public void init() {
        addData("Startup", "TeleOpMain Init");
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        armSubsystem = new ArmSubsystem(hardwareMap);
        addData("Startup", "Subsystem finished building");
        armSubsystem.ApplyPower(1);
        addData("ArmSubsystem", "Applied Arm Power");
        addData("Startup", "Startup Complete!");
    }

    @Override
    public void loop() {

        mecanumDrive.setWeightedDrivePower(
                new Pose2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x*.75,
                        -gamepad1.right_stick_x
                )
        );

        if (gamepad2.a) {
            armSubsystem.ScoreLowPosition();
        } else if (gamepad2.b) {
            armSubsystem.ScoreMidPosition();
        } else if (gamepad2.y) {
            armSubsystem.ScoreHighPosition();
        }

        if (gamepad2.x) {
            armSubsystem.LaunchPlane();
        }

//        if (gamepad2.right_bumper) {
//            armSubsystem.OpenGrabServo();
//        } else if (gamepad2.left_bumper) {
//            armSubsystem.CloseGrabServo();
//        }
//
//        if (gamepad2.dpad_up) {
//            armSubsystem.ArmUpForClimb();
//        } else if (gamepad2.dpad_down) {
//            armSubsystem.Climb();
//        }

    }

    private void addData(String caption, String string) {
        telemetry.addData(caption, string);
        telemetry.update();
    }

    private void addData(String caption, Object object) {
        telemetry.addData(caption, object);
        telemetry.update();
    }
}
