package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.*;

import java.util.Locale;

@TeleOp(name = "WebCamBase", group="Test")
public class WebCamBase extends LinearOpMode {

    OpenCvCamera phoneCam;

    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        phoneCam.openCameraDevice();

        DetectorBase base = new DetectorBase();
        phoneCam.setPipeline(base);

        phoneCam.startStreaming(640, 480);

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Object detected: ", base.detected);

            telemetry.addData("Frame Count", phoneCam.getFrameCount());
            telemetry.addData("FPS", String.format(Locale.US, "%.2f", phoneCam.getFps()));
            telemetry.update();
        }

        phoneCam.closeCameraDevice();
    }
}
