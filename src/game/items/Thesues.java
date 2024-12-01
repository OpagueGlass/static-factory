package game.items;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.TravelAction;
import game.utilities.RandomUtil;

/**
 * A class that represents the Thesues item
 *
 * @author Jonah Yip Mathivanan
 */
public class Thesues extends Item implements Purchasable {

  private static final int DEFAULT_PRICE = 100;
  private int price;

  /**
   * Constructor.
   */
  public Thesues() {
    super("THESEUS", '^', true);
    this.price = DEFAULT_PRICE;
  }

  /**
   * Allows the Actor to purchase the Theseus.
   *
   * @param actor The actor purchasing the Theseus.
   * @return The outcome of the purchase.
   */
  @Override
  public String purchase(Actor actor) {
    String result;
    int balance = actor.getBalance();

    if (balance < price) {
      result = String.format("%s does not have enough credits to purchase %s", actor, this);
    } else {
      actor.addItemToInventory(this);
      actor.deductBalance(price);
      result = String.format("%s successfully purchased %s for %d credits", actor, this, price);
    }
    return result;
  }

  /**
   * Finds a random location to teleport to.
   *
   * @param map The map to teleport in.
   * @return The location to teleport to.
   */
  public Location findTeleportLocation(GameMap map) {
    int posX = RandomUtil.generateRandomInt(0, map.getXRange().max() + 1);
    int posY = RandomUtil.generateRandomInt(0, map.getYRange().max() + 1);
    Location location = map.at(posX, posY);
    return location;
  }

  /**
   * A collection of Actions that the actor can perform on the Theseus when on the ground
   *
   * @param location the location of the ground on which the item lies.
   * @return A list of Actions.
   */
  @Override
  public ActionList allowableActions(Location location) {
    ActionList actions = new ActionList();
    Location teleportLocation = findTeleportLocation(location.map());
    actions.add(new TravelAction(teleportLocation, "current map"));
    return actions;
  }
}

