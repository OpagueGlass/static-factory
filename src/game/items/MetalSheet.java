package game.items;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.SellAction;
import game.capabilities.Ability;

/**
 * Class representing a Metal Sheet
 *
 * @author Jonah Yip Mathivanan
 */
public class MetalSheet extends Item implements Sellable {

  private static final int DEFAULT_SELLING_PRICE = 20;
  private static final int DEFAULT_DISCOUNTED_SELLING_PRICE = 10;
  private static final double DEFAULT_DISCOUNT_CHANCE = 0.6;
  private int price;

  /**
   * Constructor.
   */
  public MetalSheet() {
    super("Metal Sheets", '%', true);
    price = DEFAULT_SELLING_PRICE;
  }

  /**
   * Sets the selling price of the Metal Sheet.
   *
   * @param price The selling price of the Metal Sheet
   */
  public void setSellingPrice(int price) {
    this.price = price;
  }

  /**
   * Sells the item and applies the outcome to the actor.
   *
   * @param actor The actor selling the item
   * @param map The map the actor is on
   * @return The outcome of the sale
   */
  @Override
  public String sell(Actor actor, GameMap map) {
    actor.removeItemFromInventory(this);
    if (Math.random() < DEFAULT_DISCOUNT_CHANCE) {
      setSellingPrice(DEFAULT_DISCOUNTED_SELLING_PRICE);
    }
    actor.addBalance(price);
    return actor + " sold " + this + " for " + price + " credits.";
  }

  /**
   * Returns a list of allowable actions for the other actor to perform on this item.
   *
   * @param otherActor the other actor
   * @param location the location of the other actor
   * @return a list of allowable actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Ability.CAN_BUY_SELLABLES)) {
      actions.add(new SellAction(this));
    }
    return actions;
  }
}