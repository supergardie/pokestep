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
    private TextView TvTotalSteps;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;

    Random rand = new Random();

    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private static final String TEXT_TOTAL_STEPS = "Total steps: ";

    private String[] trainerTypes = {
            "Biker",
            "Bird Keeper",
            "Blackbelt",
            "Bug Catcher",
            "Cooltrainer",
            "Fisher",
            "Hiker",
            "Psychic",
            "Rocker",
            "Youngster"
    };

    private String[] trainerNames = {
            "Joey",
            "Claire",
            "Ash",
            "Misty",
            "Brock",
            "Joy",
            "Oak",
            "Sabrina",
            "Gary",
            "Erika"
    };

    private int numSteps;
    private int totalSteps = 0;
    private int randNum;
    private int money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = (TextView) findViewById(R.id.tv_steps);
        TvNotice = (TextView) findViewById(R.id.tv_notice);
        TvTotalSteps = (TextView) findViewById(R.id.tv_totalSteps);
        Button BtnStart = (Button) findViewById(R.id.btn_start);
        Button BtnStop = (Button) findViewById(R.id.btn_stop);

        TvTotalSteps.setText(TEXT_TOTAL_STEPS + totalSteps);


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
        totalSteps++;
        TvSteps.setText(TEXT_NUM_STEPS +  numSteps);
        TvTotalSteps.setText(TEXT_TOTAL_STEPS + totalSteps);
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


    // STEP EVENTS
    // Battle (wild or trainer), found item, found city

    public void battle() {
        randNum = rand.nextInt(2) + 1;

        if(randNum % 2 == 0) {
            wildBattle();
        } else {
            trainerBattle();
        }

        TvNotice.append("\nTap next!");
    }

    public void trainerBattle() {
        Trainer trainer = new Trainer();
        TvNotice.setText("You're in a trainer battle with " + trainer.name + "!\n");
        TvNotice.append(trainer.name + " has these pokemon: ");

        for (int ii = 0; ii < trainer.pokemon.length; ii++) {
            TvNotice.append(trainer.pokemon[ii]);

            if(ii == trainer.pokemon.length - 1) {
                TvNotice.append(".\n");
            } else {
                TvNotice.append(", ");
            }
        }

        TvNotice.append("You will get $" + trainer.money + " from this trainer!");
    }

    public void wildBattle() {
        TvNotice.setText("You're in a wild battle!");
    }

    public void foundItem() {
        TvNotice.setText("You found an item!\nTap next!");
    }

    public void foundCity() {

    }




    public void startCounter() {
        randNum = rand.nextInt(10) + 1;
        numSteps = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
        TvNotice.setText("Random number is " + randNum);
    }

    public void restartCounter() {
        TvNotice.setText("");
        TvSteps.setText(TEXT_NUM_STEPS + 0);

        startCounter();
    }

}