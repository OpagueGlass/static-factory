package game.weapons;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.items.Sellable;

/**
 * A class that defines a MetalPipe.
 *
 * @author Ryan Zhi Yin Hii
 */
public class MetalPipe extends WeaponItem implements Sellable {

  private static final int DEFAULT_WEAPON_DAMAGE = 1;
  private static final int DEFAULT_WEAPON_HIT_RATE = 20;
  private static final int DEFAULT_SELLING_PRICE = 35;
  private int price;

  /**
   * Constructor.
   */
  public MetalPipe() {
    super("Metal Pipe", '!', DEFAULT_WEAPON_DAMAGE, "whacks", DEFAULT_WEAPON_HIT_RATE);
    price = DEFAULT_SELLING_PRICE;
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
    actor.addBalance(price);
    return actor + " sold " + this + " for " + price + " credits.";
  }

  /**
   * List of allowable actions that the item allows its owner do to other actor.
   *
   * @param otherActor the other actor
   * @param location   the location of the other actor
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
      actions.add(new AttackAction(otherActor, location.toString(), this));
    }
    if (otherActor.hasCapability(Ability.CAN_BUY_SELLABLES)) {
      actions.add(new SellAction(this));
    }
    return actions;
  }
}
