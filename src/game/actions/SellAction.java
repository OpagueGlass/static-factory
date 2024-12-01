package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.items.Sellable;

/**
 * A class that defines a Sell Action.
 *
 * @author Ryan Zhi Yin Hii
 */
public class SellAction extends Action {

  private Sellable item;

  /**
   * Constructor.
   *
   * @param item The item to be sold.
   */
  public SellAction(Sellable item) {
    this.item = item;
  }

  /**
   * Executes the action of selling the item.
   *
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return the outcome of selling the item.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    return item.sell(actor, map);
  }

  /**
   * Returns a description of the action.
   *
   * @param actor The actor performing the action.
   * @return a string description of the action.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " sells " + item;
  }
}
