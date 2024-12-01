package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;

/**
 * Class that represents ConsumeAction. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class ConsumeAction extends Action {

  private Consumable consumable;

  /**
   * Constructor.
   *
   * @param consumable the Consumable item to be consumed
   */
  public ConsumeAction(Consumable consumable) {
    this.consumable = consumable;
  }

  /**
   * Consumes the consumable item and applies the effect to the actor.
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return A string describing the consumption of the consumable item.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    consumable.consume(actor);
    return consumable.getConsumeEffect(actor);
  }

  /**
   * Display Method to show the ConsumeAction in the menu with a description
   *
   * @param actor The actor performing the action.
   * @return The menu description as a String.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " consumes " + consumable;
  }
}
