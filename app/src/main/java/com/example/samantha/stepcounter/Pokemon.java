package com.example.samantha.stepcounter;

/**
 * Created by samantha on 18/03/18.
 */

public interface Pokemon {
    public String name = "";
    public String type = "";

    public int level = 0;
    public int exp = 0;

    public Moves[] moves = {};

    public int hp = 0;
    public int att = 0;
    public int special = 0;
    public int def = 0;

        // So I need to differentiate this by type... I guess I need a moves class?
        // How should I set that up? I don't need to be creating new moves. I need a set number of moves already created from which to choose,
        // based on having a matching/compatible type with this pokemon.
        // Should I have a moves class, with predefined types, names, pp, and power?
        // Should I create these move objects immediately upon loading the game?

    public void levelUp();