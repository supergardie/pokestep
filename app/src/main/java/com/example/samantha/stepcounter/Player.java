package com.example.samantha.stepcounter;

/**
 * Created by samantha on 18/03/18.
 */

public class Player {
    public String name;
    public Pokemon[] pokemon;
    public int money;
    public int numPokemon = 0;

    public Player() {
        this.name = "NAME";
        this.pokemon = new Pokemon[6];
        this.money = 0;
    }

    public Player(String name, Pokemon starter) {
        this.name = name;
        this.pokemon = new Pokemon[6];
        pokemon[0] = starter;
        this.money = 0;
        this.numPokemon++;
    }
}
