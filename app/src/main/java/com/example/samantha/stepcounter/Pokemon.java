package com.example.samantha.stepcounter;

import java.util.Random;

/**
 * Created by samantha on 18/03/18.
 */

public class Pokemon {
    private static final String[] types = {
        "Water",
        "Grass",
        "Fire"
    };

    private static final String[] pokemon = {
        "Charmander",
        "Bulbasaur",
        "Squirtle"
    };

    public String name;
    public String type;
    public int level;
    public String[] moves;
    public int exp;

    public Pokemon() {
        Random rand = new Random();
        int randNum = rand.nextInt(pokemon.length - 1) + 1;
        this.name = pokemon[randNum];

        randNum = rand.nextInt(types.length - 1) + 1;
        this.type = types[randNum];

        this.level = 2;
        this.exp = 0;
        this.moves = new String[4];
    }

}
