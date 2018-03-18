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

        // So I need to differentiate this by type... I guess I need a moves class?
        // How should I set that up? I don't need to be creating new moves. I need a set number of moves already created from which to choose,
        // based on having a matching/compatible type with this pokemon.
        // Should I have a moves class, with predefined types, names, pp, and power?
        // Should I create these move objects immediately upon loading the game?
        this.moves = new String[4];

        this.moves[0] = "move 1";
        this.moves[1] = "move 2";
        this.moves[2] = "move 3";
        this.moves[3] = "move 4";
    }

}
