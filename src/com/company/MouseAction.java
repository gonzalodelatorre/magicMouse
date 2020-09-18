package com.company;

import java.awt.*;
import java.util.Random;

public class MouseAction implements Runnable {


    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;


    public void run() {
        try {
            Robot robot = new Robot();
            Random random = new Random();

            while (true) {
                robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
                Thread.sleep(FIVE_SECONDS);
            }

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
