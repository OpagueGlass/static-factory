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
 * Class representing a Jar of Pickles. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class JarOfPickles extends Item implements Consumable, Sellable{

  private static final double EXPIRED_CHANCE = 0.5;
  private static final int DEFAULT_HEALING_AMOUNT = 1;
  private static final int DEFAULT_DAMAGE_AMOUNT = 1;
  private static final int DEFAULT_SELLING_PRICE = 25;
  private static final int DEFAULT_INCREASED_SELLING_PRICE = DEFAULT_SELLING_PRICE * 2;
  private static final double INCREASED_SELLING_CHANCE = 0.5;
  private String consumeEffect;
  private int healingAmount;
  private int damageAmount;
  private int price;

  /**
   * Constructor.
   */
  public JarOfPickles() {
    super("Jar of Pickles", 'n', true);
    this.healingAmount = DEFAULT_HEALING_AMOUNT;
    this.damageAmount = DEFAULT_DAMAGE_AMOUNT;
    this.price = DEFAULT_SELLING_PRICE;
  }

  /**
   * Returns the consume effect of the consumable.
   *
   * @return The consume effect of the consumable
   */
  @Override
  public String getConsumeEffect(Actor target) {
    return consumeEffect;
  }

  /**
   * Consumes the expired item and applies its effect to the target Actor.
   *
   * @param target The Actor to consume the item.
   */
  public void consumeExpiredItem(Actor target) {
    consumeEffect = target + " consumed an expired " + this + ". " + target + " feels sick.";
    target.hurt(damageAmount);
  }

  /**
   * Consumes the fresh item and applies its effect to the target Actor.
   *
   * @param target The Actor to consume the item.
   */
  public void consumeFreshItem(Actor target) {
    consumeEffect = target + " consumed a " + this + ". " + target + " feels better.";
    target.heal(healingAmount);
  }

  /**
   * Consumes the item and applies its effect to the target Actor.
   *
   * @param target The Actor to consume the item.
   */
  @Override
  public void consume(Actor target) {
    target.removeItemFromInventory(this);
    if (Math.random() < EXPIRED_CHANCE) {
      consumeFreshItem(target);
    } else {
      consumeExpiredItem(target);
    }
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
    if (Math.random() < INCREASED_SELLING_CHANCE) {
      price = DEFAULT_INCREASED_SELLING_PRICE;
    }
    actor.addBalance(price);
    return actor + " sold " + this + " for " + price + " credits.";
  }

  /**
   * List of allowable actions that the item can perform to the current actor
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
