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
    private boolean start = true;

    private TextView TvSteps;
    private TextView TvNotice;
    private TextView TvTotalSteps;

    private Button BtnOne;
    private Button BtnTwo;
    private Button BtnThree;
    private Button BtnAction;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;

    Random rand = new Random();

    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private static final String TEXT_TOTAL_STEPS = "Total steps: ";

    private Player player;
    private int numSteps;
    private int totalSteps = 0;
    private int stepsToNextTown = 20;
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
        BtnAction = (Button) findViewById(R.id.btn_action);
        BtnOne = (Button) findViewById(R.id.btn_one);
        BtnTwo = (Button) findViewById(R.id.btn_two);
        BtnThree = (Button) findViewById(R.id.btn_three);

        TvTotalSteps.setText(TEXT_TOTAL_STEPS + totalSteps);


        BtnAction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(start) {
                    start = !start;
                    player = new Player("Sam", new PokeCharmander());
                    TvNotice.setText("Hello " + player.name + "! You are starting with a " + player.pokemon[0].name + ".\n");

                    startCounter();
                    BtnAction.setText("Next");
                } else {
                    restartCounter();
                }
            }
        });


//        BtnStop.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                restartCounter();
//            }
//        });



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
        stepsToNextTown--;
        TvSteps.setText(TEXT_NUM_STEPS +  numSteps);
        TvTotalSteps.setText(TEXT_TOTAL_STEPS + totalSteps);
    }

    public void checkForStepEvent() {

        if(stepsToNextTown == 0) {
            foundCity();
            sensorManager.unregisterListener(MainActivity.this);
        } else if(numSteps == randNum) {
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
        TvNotice.append("You will get $" + trainer.money + " from this trainer!");
    }

    public void wildBattle() {
//        Pokemon wildPokemon = new Pokemon();
        PokeCharmander wildPokemon = new PokeCharmander();
        TvNotice.setText("A wild " + wildPokemon.name + " appeared!\n");
        TvNotice.append("HP: " + wildPokemon.hp + "\n");
        TvNotice.append("Level: " + wildPokemon.level + "\n");

        if(player.pokemon[0].currentHP > 0 && wildPokemon.currentHP > 0) {
            wildPokemon.takeDamage(player.pokemon[0].att);
            TvNotice.append("Damage: " + player.pokemon[0].att);
            TvNotice.append("HP: " + wildPokemon.currentHP);
        }
    }

    public void foundItem() {
        TvNotice.setText("You found an item!\nTap next!");
    }

    /**
     * What to do in cities? Heal, shop, gym, choose next destination.
     */
    public void foundCity() {

        BtnAction.setText("HEAL");
        
        BtnOne.setText("SHOP");
        BtnOne.setVisibility(View.VISIBLE);

        BtnTwo.setText("GYM");
        BtnTwo.setVisibility(View.VISIBLE);

        BtnThree.setText("DESTINATION");
        BtnThree.setVisibility(View.VISIBLE);

        TvNotice.setText("You're in NEWCITY City!");
        stepsToNextTown = 20;

    }


    /***********************************************************************************************
     * Counter starting and restarting
     ***********************************************************************************************/


    public void startCounter() {
        randNum = rand.nextInt(10) + 1;
        numSteps = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
        TvNotice.append("Steps to next objective:  " + randNum);
    }

    public void restartCounter() {
        TvNotice.setText("");
        TvSteps.setText(TEXT_NUM_STEPS + 0);

        startCounter();
    }

}