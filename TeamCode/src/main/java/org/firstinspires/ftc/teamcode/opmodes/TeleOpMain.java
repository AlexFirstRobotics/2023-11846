package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.auxsubsystem.AuxSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intakesubsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.mecanumsubsystem.MecanumSubsystem;

@TeleOp(name = "TeleOpMain", group = "Competition")
public class TeleOpMain extends OpMode {

    ArmSubsystem armSubsystem;
    AuxSubsystem auxSubsystem;
    IntakeSubsystem intakeSubsystem;

    MecanumSubsystem mecanumSubsystem;

    private int currentIntakeMode = 0;
    private int intakeOn = -1;

    @Override
    public void init() {

        mecanumSubsystem = new MecanumSubsystem(hardwareMap);

        armSubsystem = new ArmSubsystem(hardwareMap);
        auxSubsystem = new AuxSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

        armSubsystem.setMotorAsync(1);
        intakeSubsystem.setMotorAsync(1);
        armSubsystem.PickupInPixel();
        armSubsystem.ReleasePixel();
    }

    @Override
    public void loop() {

        mecanumSubsystem.TeleOperatedDrive(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x
        );

        /* Arm Positions */
        if (gamepad2.x) {
            armSubsystem.PickupIn();
//        }else if(gamepad2.b) {
//            armSubsystem.PickupOut();
        }else if(gamepad2.a) {
            armSubsystem.Home();
        }else if(gamepad2.y) {
            armSubsystem.Score();
        }

        /* Grab Servo */
        if (gamepad2.left_bumper) {
            armSubsystem.ReleasePixel();
        }else if (gamepad2.right_bumper) {
            armSubsystem.GrabPixel();
        }

        /* Score Pixel */
        if (gamepad2.dpad_down) {
            armSubsystem.ScorePixelDrop();
        }

        /* AuxSubsystem */
        if(gamepad1.a) {
            auxSubsystem.Launch();
        }

        if(gamepad1.b) {
            intakeOn = intakeOn * (-1);
        }
        if (intakeOn == -1) {
            intakeSubsystem.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeSubsystem.RawIntakeMotor(0);
        }
        if (intakeOn == 1) {
            intakeSubsystem.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeSubsystem.RawIntakeMotor(1.0);
        }

//        if (gamepad1.b) {
//            intakeSubsystem.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//            intakeSubsystem.RawIntakeMotor(1.0);
//            currentIntakeMode = 1;
//        } else if (currentIntakeMode == 1) {
//            currentIntakeMode = 2;
//            intakeSubsystem.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//            int currentPosition = intakeSubsystem.GetIntakePosition()%288; // Get position in 0-288 range
//            int target = intakeSubsystem.GetIntakePosition()-currentPosition; // Target from currentPosInfRange - currentPos0-288 range
//            intakeSubsystem.SetPosition(target);
//            intakeSubsystem.setMotorAsync(1.0);
//        } else if (currentIntakeMode == 2) {
//            currentIntakeMode = 0;
//            intakeSubsystem.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//            intakeSubsystem.RawIntakeMotor(0);
//        }

    }
}
