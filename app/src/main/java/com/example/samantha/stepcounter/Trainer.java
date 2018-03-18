package com.example.samantha.stepcounter;

import java.util.Random;

/**
 * Created by samantha on 18/03/18.
 */

public class Trainer {

    private static final String[] trainerNames = {
            "Brock",
            "Misty",
            "Lt. Surge",
            "Erika",
            "Koga",
            "Sabrina",
            "Blaine",
            "Giovanni",
            "Lilliana"
    };

    public String type;
    public String name;
    public String[] pokemon;
    public int money;

    public Trainer() {
        Random rand = new Random();
        int randNum = rand.nextInt(Types.trainers.length);

        this.type = Types.trainers[randNum];

        randNum = rand.nextInt(10);

        this.name = String.format("%s %s", this.type, trainerNames[randNum]);

        this.pokemon = new String[3];

        this.pokemon[0] = "Charmander";
        this.pokemon[1] = "Squirtle";
        this.pokemon[2] = "Bulbasaur";

        randNum = rand.nextInt(400) + 100;
        this.money = randNum;
    }
}
