package game.grounds;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.actors.attributes.ActorAttributeOperations;
import engine.actors.attributes.BaseActorAttributes;
import engine.positions.Ground;
import engine.positions.Location;
import game.actions.Consumable;
import game.actions.ConsumeAction;

/**
 * Class representing a Puddle
 *
 * @author Jonah Yip Mathivanan
 */
public class Puddle extends Ground implements Consumable {

  private static final int DEFAULT_MAX_HEALTH_AMOUNT = 1;
  private int maxHealthAmount;

  /**
   * Constructor.
   */
  public Puddle() {
    super('~');
    this.maxHealthAmount = DEFAULT_MAX_HEALTH_AMOUNT;
  }

  /**
   * Returns the consume effect of the consumable.
   *
   * @return The consume effect of the consumable
   */
  @Override
  public String getConsumeEffect(Actor target) {
    return target + " drinks from " + this + ".. " + target + " feels invigorated.";
  }

  /**
   * Consumes the consumable and applies its effect to the target Actor.
   *
   * @param target The Actor to consume the consumable.
   */
  @Override
  public void consume(Actor target) {
    target.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,
        maxHealthAmount);
  }

  /**
   * List of allowable actions that the item can perform to the current actor
   *
   * @param actor the Actor acting
   * @param location the current Location
   * @param direction the direction of the Ground from the Actor
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();
    if (location.containsAnActor()) {
      actions.add(new ConsumeAction(this));
    }
    return actions;
  }

  /**
   * Returns a string representation of the consumable.
   *
   * @return A string representation of the consumable.
   */
  @Override
  public String toString() {
    return "A random puddle of water.";
  }
}
