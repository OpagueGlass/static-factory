package game.items;

import engine.actors.Actor;

/**
 * An interface that defines a Purchasable item.
 *
 * @author Ryan Zhi Yin Hii
 */
public interface Purchasable {
    /**
     * Allows the actor to purchase the Purchasable item.
     *
     * @param actor The actor purchasing the Purchasable item.
     * @return The outcome of the purchase
     */
    String purchase(Actor actor);
}
