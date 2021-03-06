package com.example.samantha.stepcounter;

import java.util.Random;

/**
 * Created by samantha on 18/03/18.
 */

public class PokeCharmander extends Pokemon {
    PokeCharmander() {
        int randNum = 0;
        Random rand = new Random();

        this.name = "Charmander";
        this.exp = 0;

        randNum = rand.nextInt(5) + 1;
        this.att = randNum;

        randNum = rand.nextInt(5) + 1;
        this.def = randNum;

        randNum = rand.nextInt(6) + 10;
        this.hp = randNum;
        this.currentHP = this.hp;

        randNum = rand.nextInt(9) + 2;
        this.level = randNum;
    }
}
