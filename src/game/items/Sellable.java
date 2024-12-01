package game.items;

import engine.actors.Actor;
import engine.positions.GameMap;

/**
 * Interface representing a sellable item
 *
 * @author Jonah Yip Mathivanan
 */
public interface Sellable {

  /**
   * Sells the item and applies the outcome to the actor.
   *
   * @param actor The actor selling the item
   * @param map The map the actor is on
   * @return The outcome of the sale
   */
  String sell(Actor actor, GameMap map);
}


