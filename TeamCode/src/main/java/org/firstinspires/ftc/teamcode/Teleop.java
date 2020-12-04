package org.firstinspires.ftc.teamcode;

    import com.qualcomm.hardware.rev.RevTouchSensor;
    import com.qualcomm.robotcore.hardware.TouchSensor;
    import com.qualcomm.robotcore.util.ElapsedTime;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.hardware.bosch.BNO055IMU;
    import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
    import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
    import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
    import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.hardware.Gyroscope;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.Blinker;
    import com.qualcomm.robotcore.hardware.CRServo;
    import com.qualcomm.robotcore.hardware.Servo;

    import com.qualcomm.robotcore.hardware.DcMotorSimple;

class Teleop extends LinearOpMode {



            private DcMotor BLM;
            private DcMotor BRM;
            private DcMotor FLM;
            private DcMotor FRM;
            BNO055IMU Imu;


            @Override
            public void runOpMode() {
                //Getting Motor Name From Phone
                BLM = hardwareMap.dcMotor.get("BLM");
                BRM = hardwareMap.dcMotor.get("BRM");
                FLM = hardwareMap.dcMotor.get("FLM");
                FRM = hardwareMap.dcMotor.get("FRM");

                // showing which direction the robot moves
                FRM.setDirection(DcMotorSimple.Direction.REVERSE);
                BRM.setDirection(DcMotorSimple.Direction.REVERSE);

                //setting run mode
                FRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                FLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                BRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                BLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



                telemetry.addData("Mode", "waiting for start");
                telemetry.addData("imu calibration status", Imu.getCalibrationStatus().toString());
                telemetry.update();


                waitForStart();
                if (opModeIsActive()) {
                    BLM.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
                    BRM.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
                    FLM.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
                    FRM.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);


                }

            }




        }


