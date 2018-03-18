package com.example.samantha.stepcounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView TvSteps;
    private TextView TvNotice;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private int totalSteps;
    private int randNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise total steps
        totalSteps = 0;

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = (TextView) findViewById(R.id.tv_steps);
        TvNotice = (TextView) findViewById(R.id.tv_notice);
        Button BtnStart = (Button) findViewById(R.id.btn_start);
        Button BtnStop = (Button) findViewById(R.id.btn_stop);



        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startCounter();
            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                restartCounter();
            }
        });



    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }

        checkForStepEvent();
    }

    @Override
    public void step(long timeNs) {
         numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS +  numSteps);
    }

    public void checkForStepEvent() {

        if(numSteps == randNum) {
            if(numSteps % 2 == 0) {
                battle();
            } else {
                foundItem();
            }

            // Stop counting steps until event is over and user taps the Next button
            // Walking while using your phone is dangerous, kids!
            sensorManager.unregisterListener(MainActivity.this);
        }

    }

    public void battle() {
        TvNotice.setText("You're in a battle!\nTap next!");
    }

    public void foundItem() {
        TvNotice.setText("You found an item!\nTap next!");
    }

    public void startCounter() {
        Random rand = new Random();
        randNum = rand.nextInt(10) + 5;
        numSteps = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
        TvNotice.setText("Random number is " + randNum);
    }

    public void restartCounter() {
        TvNotice.setText("");

        startCounter();
    }

}