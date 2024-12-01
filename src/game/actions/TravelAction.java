package game.actions;

import engine.actions.Action;
import engine.actions.MoveActorAction;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;

/**
 * A class that travels the actor to a location.
 *
 * @author Jonah Yip Mathivanan
 */
public class TravelAction extends Action {

  private Location location;
  private String name;

  /**
   * Constructor.
   *
   * @param location the location to travel to
   * @param name     the name of the location
   */
  public TravelAction(Location location, String name) {
    this.location = location;
    this.name = name;
  }

  /**
   * Allows the actor to travel to the location.
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return The outcome of the travel.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    String result = "Travel failed due to something blocking the travel destination";
    if (!location.containsAnActor()) {
      MoveActorAction moveActorAction = new MoveActorAction(location, name);
      moveActorAction.execute(actor, map);
      result = actor + " arrived at " + location + " in " + name;
    }
    return result;
  }

  /**
   * Display Method to show the TravelAction in the menu with a description
   *
   * @param actor The actor performing the action.
   * @return The menu description as a String.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " travels to " + name;
  }
}
