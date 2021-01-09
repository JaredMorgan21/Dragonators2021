package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;


public class RingDetector extends OpenCvPipeline{

    Mat imgHSV = new Mat();
    Mat imgBlur = new Mat();
    Mat mask = new Mat();

    Boolean detected;

    public RingDetector(){
    }

    @Override
    public Mat processFrame(Mat input) {
        mask.setTo(new Scalar(0, 0, 0));
        Imgproc.cvtColor(input, imgHSV, Imgproc.COLOR_BGR2HSV);
        Imgproc.GaussianBlur(imgHSV, imgBlur, new Size(7, 7), 10);

        //HSV
        Core.inRange(imgBlur, new Scalar(0, 152, 66), new Scalar(15, 255, 255), mask);
        Core.bitwise_and(input, input, input, mask);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();

        //Chain approx none means that you take all the points that make up the contour
        //Chain approx simple or complex means that you take all the points and then you turn them into a line
        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
        String objectType;

        for(int i = 0; i < contours.size(); i++){
            double area = Imgproc.contourArea(contours.get(i));
            //System.out.println(area);
            if(area > 100){
                Imgproc.drawContours(input, contours, i, new Scalar(255, 0, 0), 3);

                MatOfPoint2f contour = new MatOfPoint2f(contours.get(i).toArray());
                MatOfPoint2f approx = new MatOfPoint2f();

                double perimeter  = Imgproc.arcLength(contour, true);
                Imgproc.approxPolyDP(contour, approx, 0.02 * perimeter, true);

                Rect rect = Imgproc.boundingRect(approx);

                double x = rect.x; // x of top left corner of rectangle
                double y = rect.y; // y of top left corner of rectangle
                double w = rect.width; // width of rectangle
                double h = rect.height; // height of rectangle

                double aspRatio = w / h;

                // 0.95 < aspRatio < 1.05
                detected = aspRatio > 9.5 && aspRatio < 3;

                Imgproc.rectangle(input, rect, new Scalar(0, 0, 255), 2);
                Imgproc.putText(input, String.valueOf(detected), new Point(x + w, y + h), Imgproc.FONT_HERSHEY_PLAIN, 1.5, new Scalar(0, 0, 0), 2);
                //Imgproc.rectangle(finalImage, new Point(x, y), new Point(x + w, y + h), new Scalar(0, 0, 255), 2);
            }
            else{
                detected = false;
            }
        }

        return input;
    }
}
