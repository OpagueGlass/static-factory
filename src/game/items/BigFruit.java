package game.items;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.Consumable;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.capabilities.Ability;

/**
 * Class representing a BigFruit. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class BigFruit extends Item implements Consumable, Sellable {

  private static final int DEFAULT_HEALING_AMOUNT = 2;
  private static final int DEFAULT_SELLING_PRICE = 30;
  private int healingAmount;
  private int price;

  /**
   * Constructor.
   */
  public BigFruit() {
    super("Big Fruit", 'O', true);
    healingAmount = DEFAULT_HEALING_AMOUNT;
    price = DEFAULT_SELLING_PRICE;
  }

  /**
   * Returns the consume effect of the consumable.
   *
   * @return The consume effect of the consumable
   */
  @Override
  public String getConsumeEffect(Actor target) {
    return target + " consumed " + this + " and " + this + " heals " + target + " by " +
        healingAmount + " points.";
  }

  /**
   * Consumes the item and applies its effect to the target Actor.
   *
   * @param target The Actor to consume the item.
   */
  @Override
  public void consume(Actor target) {
    target.removeItemFromInventory(this);
    target.heal(healingAmount);
  }

  /**
   * Sells the item and applies the outcome to the actor.
   *
   * @param actor The actor selling the item
   * @param map The map the actor is on
   * @return The outcome of the sale
   */
  public String sell(Actor actor, GameMap map) {
    actor.removeItemFromInventory(this);
    actor.addBalance(price);
    return actor + " sold " + this + " for " + price + " credits.";
  }

  /**
   * List of allowable actions that the item can perform to the current actor.
   *
   * @param owner the actor that owns the item
   * @return an unmodifiable list of Actions
  */
  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actions = new ActionList();
    actions.add(new ConsumeAction(this));
    return actions;
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
