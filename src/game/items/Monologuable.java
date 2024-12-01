package game.items;

import engine.actors.Actor;

/**
 * An interface for objects that can deliver a monologue.
 *
 * @author Ashley
 */
public interface Monologuable {

    /**
     * Returns a monologue from the object.
     *
     * @param actor the actor listening to the monologue
     * @return a string representing the monologue
     */
    String monologue(Actor actor);

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    String toString();
}