package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

class Hardware {
    public DcMotor BLM;
    public DcMotor BRM;
    public DcMotor FLM;
    public DcMotor FRM;
    public DcMotor Conveyor;
    public DcMotor Flywheel;
    public Servo Grabber;
    public Servo Arm;

    public void Initialize(HardwareMap hardwareMap) {
        BLM = hardwareMap.dcMotor.get("BLM");
        BRM = hardwareMap.dcMotor.get("BRM");
        FLM = hardwareMap.dcMotor.get("FLM");
        FRM = hardwareMap.dcMotor.get("FRM");
        Conveyor = hardwareMap.dcMotor.get("Conveyor");
        Flywheel = hardwareMap.dcMotor.get("FLywheel");
        Grabber = hardwareMap.servo.get("Grabber");
        Arm = hardwareMap.servo.get("Arm");

        FRM.setDirection(DcMotorSimple.Direction.REVERSE);
        BRM.setDirection(DcMotorSimple.Direction.REVERSE);

        FRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public Hardware (){

    }


}
