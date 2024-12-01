package game.grounds;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.Ground;
import engine.positions.Location;
import game.actions.PurchaseAction;
import game.actions.TravelAction;
import game.items.Purchasable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class that defines a Computer Terminal.
 *
 * @author Ryan Zhi Yin Hii
 */
public class ComputerTerminal extends Ground {

  private Map<String, Location> travelLocations;
  private List<Purchasable> items;

  /**
   * Constructor.
   *
   * @param items The items that can be purchased from the Computer Terminal.
   * @param travelLocations The locations in the different maps that the actor can travel to from
   *                        the Computer Terminal.
   */
  public ComputerTerminal(ArrayList<Purchasable> items, Map<String, Location> travelLocations) {
    super('=');
    this.items = items;
    this.travelLocations = travelLocations;
  }

  /**
   * A collection of Actions that each item in the Computer Terminal can perform.
   *
   * @param actor     the Actor acting
   * @param location  the current Location
   * @param direction the direction of the Ground from the Actor
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();
    for (Purchasable item : items) {
      actions.add(new PurchaseAction(item));
    }
    for (String name : travelLocations.keySet()) {
      Location destination = travelLocations.get(name);
      if (location.map() != destination.map()) {
        actions.add(new TravelAction(destination, name));
      }
    }
    return actions;
  }
}
