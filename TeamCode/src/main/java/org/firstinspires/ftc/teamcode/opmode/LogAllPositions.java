package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "LogAllPositions", group = "logging")
public class LogAllPositions extends OpMode {
    SampleMecanumDrive mecanumDrive;
    ArmSubsystem armSubsystem;

    @Override
    public void init() {
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        armSubsystem = new ArmSubsystem(hardwareMap);
    }

    @Override
    public void loop() {

        armSubsystem.PivotArm(-gamepad2.left_stick_y);
        armSubsystem.ExtendArm(-gamepad2.right_stick_y);

        telemetry.addData("PivotPosition", armSubsystem.GetPivotPosition());
        telemetry.addData("ExtendPosition", armSubsystem.GetExtendPosition());
        telemetry.addData("GrabPosition", armSubsystem.GetGrabPosition());
        telemetry.update();
    }


}
