package com.example.samantha.stepcounter;

import java.util.Random;

/**
 * Created by samantha on 18/03/18.
 */


public class Pokemon {
    public Types.pokemon name;
    public Types.type type;

    public int level;
    public int exp;
    public int expUp;

    public int hp;
    public int currentHP;
    public int att;
    public int def;

    // So I need to differentiate this by type... I guess I need a moves class?
    // How should I set that up? I don't need to be creating new moves. I need a set number of moves already created from which to choose,
    // based on having a matching/compatible type with this pokemon.
    // Should I have a moves class, with predefined types, names, pp, and power?
    // Should I create these move objects immediately upon loading the game?

    Pokemon(Types.pokemon pkmn, Types.type type) {

    }

    public Pokemon newPokemon(Types.pokemon pokemon) {
        Pokemon pkmn;
        Types.type type;

        switch(pokemon) {
            case ALAKAZAM:
            case ABRA:
            case KADABRA:
            case DROWZEE:
            case HYPNO:
            case MEWTWO:
            case MEW: type = Types.type.PSYCHIC; break;
            case RATTATA:
            case RATICATE:
            case MEOWTH:
            case PERSIAN:
            case LICKITUNG:
            case CHANSEY:
            case KANGASKHAN:
            case TAUROS:
            case DITTO:
            case EEVEE:
            case PORYGON:
            case SNORLAX: type = Types.type.NORMAL; break;
            case MANKEY:
            case PRIMAPE:
            case MACHOP:
            case MACHOKE:
            case MACHAMP:
            case HITMONLEE:
            case HITMONCHAN: type = Types.type.FIGHTING; break;
            case PIDGEY:
            case PIDGEOTTO:
            case PIDGEOT:
            case SPEAROW:
            case FEAROW:
            case FARFETCHD:
            case DODUO:
            case DODRIO: type = Types.type.FLYING; break;
            case EKANS:
            case ARBOK:
            case NIDORANF:
            case NIDORINA:
            case NIDOQUEEN:
            case NIDORANM:
            case NIDORINO:
            case NIDOKING:
            case ZUBAT:
            case GOLBAT:
            case GRIMER:
            case MUK:
            case KOFFING:
            case WEEZING: type = Types.type.POISON; break;
            case SANDSHREW:
            case SANDSLASH:
            case DIGLETT:
            case DUGTRIO:
            case CUBONE:
            case MAROWAK: type = Types.type.GROUND;
            case GEODUDE:
            case GRAVELER:
            case GOLEM:
            case ONIX:
            case RHYHORN:
            case RHYDON: type = Types.type.ROCK; break;
            case CATERPIE:
            case METAPOD:
            case BUTTERFREE:
            case WEEDLE:
            case KAKUNA:
            case BEEDRILL:
            case PARAS:
            case PARASECT:
            case VENONAT:
            case VENOMOTH:
            case SCYTHER:
            case PINSIR: type = Types.type.BUG; break;
            case GASTLY:
            case HAUNTER:
            case GENGAR: type = Types.type.GHOST; break;
        }

        return new Pokemon(pokemon, type);
    }


    public void levelUp() {
        if(exp >= expUp) {
            this.level++;
        }
    }

    public void takeDamage(int attack) {
        this.currentHP -= attack;
    }

    public void heal() {
        this.currentHP = this.hp;
    }
}
