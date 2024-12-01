package game.grounds;

import engine.actors.Actor;
import engine.positions.Ground;

/**
 * A class that represents a wall.
 *
 * @author Ryan Zhi Yin Hii
 */
public class Wall extends Ground {

    /**
     * Constructor.
     */
    public Wall() {
        super('#');
    }

    /**
     * Prevents actor from entering the wall.
     *
     * @param actor The actor that wants to enter the wall.
     * @return false to prevent actor from entering the wall.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
