package game.actions;

import engine.actors.Actor;

/**
 * An interface that represents an item that can be consumed by an Actor. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public interface Consumable {

  /**
   * Consumes the item and applies its effect to the target Actor.
   *
   * @param target The Actor to consume the item.
   */
  void consume(Actor target);

  /**
   * Returns a string representation of the consumable.
   *
   * @return A string representation of the consumable.
   */
  String toString();

  /**
   * Returns the consume effect the consumable.
   *
   * @param target The Actor to consume the item.
   * @return The consume effect of the consumable
   */
  String getConsumeEffect(Actor target);
}
