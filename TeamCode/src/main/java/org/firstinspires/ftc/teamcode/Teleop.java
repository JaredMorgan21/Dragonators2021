package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Teleop extends LinearOpMode {
    Hardware robot = new Hardware();

    @Override
    public void runOpMode() {

        telemetry.addData("Mode", "waiting for start");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            robot.BLM.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            robot.BRM.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
            robot.FLM.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            robot.FRM.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
            if (gamepad2.a){
                robot.Conveyor.setPower(1);
            }
            if (gamepad2.b){
                robot.Flywheel.setPower(1);
            }
            if (gamepad2.x){
                robot.Arm.setPosition(1);
            }
            if (gamepad2.y){
                robot.Grabber.setPosition(1);
            }
        }
    }
}


