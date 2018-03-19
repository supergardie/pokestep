package com.example.samantha.stepcounter;

/**
 * Created by samantha on 18/03/18.
 */

public class Move {
    public String name;
    public int pp;
    public int power;

    public Move() {
        this.name = "Nameless";
        this.pp = 0;
        this.power = 0;
    }

    public Move(String name, int pp, int power) {
        this.name = name;
        this.pp = pp;
        this.power = power;
    }
}
