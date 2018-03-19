package com.example.samantha.stepcounter;

/**
 * Created by samantha on 18/03/18.
 */

public abstract class Pokemon {
    public String name;

    public int level;
    public int exp;

    public int hp;
    public int currentHP;
    public int att;
    public int def;

    // So I need to differentiate this by type... I guess I need a moves class?
    // How should I set that up? I don't need to be creating new moves. I need a set number of moves already created from which to choose,
    // based on having a matching/compatible type with this pokemon.
    // Should I have a moves class, with predefined types, names, pp, and power?
    // Should I create these move objects immediately upon loading the game?

    public void levelUp() {
        this.level++;
        // other stuff
    }

    public void takeDamage(int attack) {
        this.currentHP -= attack;
    }

    public void heal() {
        this.currentHP = this.hp;
    }
}
