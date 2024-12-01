package game.items;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import game.actions.Consumable;
import game.actions.ConsumeAction;

/**
 * A class that represents a Small Fruit. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class SmallFruit extends Item implements Consumable {

  private static final int DEFAULT_HEALING_AMOUNT = 1;
  private int healingAmount;

  /**
   * Constructor.
   */
  public SmallFruit() {
    super("Small Fruit", 'o', true);
    healingAmount = DEFAULT_HEALING_AMOUNT;
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
}
