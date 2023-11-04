package org.firstinspires.ftc.teamcode.armsubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ArmSubsystem {

    private DcMotorEx pivotMotor, extendMotor;
    private Servo grabServo, pivotServo, launchServo;

    private List<Integer> lastEncoderPositions = new ArrayList<>();
    private List<Integer> lastEncoderVelocities = new ArrayList<>();

    private MotorConfigurationType pivotMotorConfig;
    private MotorConfigurationType extendMotorConfig;

    public ArmSubsystem(HardwareMap hardwareMap) {
        pivotMotor = hardwareMap.get(DcMotorEx.class, "pivotMotor");
        extendMotor = hardwareMap.get(DcMotorEx.class, "extendMotor");

        grabServo = hardwareMap.get(Servo.class, "grabServo");
        pivotServo = hardwareMap.get(Servo.class, "pivotServo");
        launchServo = hardwareMap.get(Servo.class, "launchServo");

        pivotMotorConfig = pivotMotor.getMotorType().clone();
        extendMotorConfig = extendMotor.getMotorType().clone();

        pivotMotorConfig.setAchieveableMaxRPMFraction(1.0);
        extendMotorConfig.setAchieveableMaxRPMFraction(1.0);

        pivotMotor.setMotorType(pivotMotorConfig);
        extendMotor.setMotorType(extendMotorConfig);

        pivotMotor.setTargetPosition(0);
        extendMotor.setTargetPosition(0);

        pivotMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        extendMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    private void wait(int sleeptime) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (timer.milliseconds() < sleeptime) {

        }
    }

    public void ApplyPower(double asyncPower) {
        pivotMotor.setPower(asyncPower);
        extendMotor.setPower(asyncPower);
    }

    public void PivotArm(double power) {
        pivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivotMotor.setPower(power);
    }

    public void ExtendArm(double power) {
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendMotor.setPower(power);
    }

    public void GrabPixel() {
        grabServo.setPosition(ArmConstants.ARM_POSITIONS.GRAB_SERVO.GRAB);
    }

    public void ReleasePixel() {
        grabServo.setPosition(ArmConstants.ARM_POSITIONS.GRAB_SERVO.RELEASE);
    }

    public void HomePosition() {
        int waitTime = 1000;
        grabServo.setPosition(ArmConstants.ARM_POSITIONS.GRAB_SERVO.RELEASE);
        pivotServo.setPosition(ArmConstants.ARM_POSITIONS.PIVOT_SERVO.HOME);
        extendMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.EXTEND_MOTOR.HOME_POSITION);
        wait(waitTime);
        pivotMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.PIVOT_MOTOR.HOME_POSITION);
    }

    public void PickupPosition() {
        int waitTime = 1000;
        grabServo.setPosition(ArmConstants.ARM_POSITIONS.GRAB_SERVO.RELEASE);
        pivotServo.setPosition(ArmConstants.ARM_POSITIONS.PIVOT_SERVO.PICKUP);
        extendMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.EXTEND_MOTOR.PICKUP_POSITION);
        wait(waitTime);
        pivotMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.PIVOT_MOTOR.PICKUP_POSITION);
    }

    public void ScoreLowPosition() {
        int waitTime = 1000;
        pivotMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.PIVOT_MOTOR.SCORE_LOW);
        wait(waitTime);
        extendMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.EXTEND_MOTOR.SCORE_LOW);
        pivotServo.setPosition(ArmConstants.ARM_POSITIONS.PIVOT_SERVO.SCORE_LOW);
    }

    public void ScoreMidPosition() {
        int waitTime = 1000;
        pivotMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.PIVOT_MOTOR.SCORE_MID);
        wait(waitTime);
        extendMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.EXTEND_MOTOR.SCORE_MID);
        pivotServo.setPosition(ArmConstants.ARM_POSITIONS.PIVOT_SERVO.SCORE_MID);
    }

    public void ScoreHighPosition() {
        int waitTime = 1000;
        pivotMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.PIVOT_MOTOR.SCORE_HIGH);
        wait(waitTime);
        extendMotor.setTargetPosition(ArmConstants.ARM_POSITIONS.EXTEND_MOTOR.SCORE_HIGH);
        pivotServo.setPosition(ArmConstants.ARM_POSITIONS.PIVOT_SERVO.SCORE_HIGH);
    }

    public void LaunchPlane() {
        launchServo.setPosition(0.5);
    }

    public int GetPivotPosition() {
        return pivotMotor.getCurrentPosition();
    }

    public int GetExtendPosition() {
        return extendMotor.getCurrentPosition();
    }

    public double GetGrabPosition() {
        return grabServo.getPosition();
    }

}