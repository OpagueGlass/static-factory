package game.grounds;

import engine.positions.Ground;
import game.capabilities.Ability;
import engine.actors.Actor;

/**
 * A class that represents the floor inside a building.
 *
 * @author Ryan Zhi Yin Hii
 */
public class Floor extends Ground {

    /**
     * Constructor.
     */
    public Floor() {
        super('_');
    }

    /**
     * Returns true if the actor can enter the spaceship.
     *
     * @param actor The actor that wants to enter the spaceship.
     * @return true if the actor can enter the spaceship.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Ability.CAN_ENTER_SPACESHIP);
    }
}
