package game.behaviours;

import engine.actions.Action;
import engine.actors.Actor;
import engine.actors.Behaviour;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.AttackAction;
import game.capabilities.Status;

/**
 * Class that represents AttackBehaviour
 *
 * @author Jonah Yip Mathivanan
 */
public class AttackBehaviour implements Behaviour {

  /**
   * Returns an AttackAction to actor an actor in the vicinity, if possible. If no attack is
   * possible, returns null.
   *
   * @param actor the Actor enacting the behaviour
   * @param map   the map that actor is currently on
   * @return an Action, or null if no AttackAction is possible
   */
  public Action getAction(Actor actor, GameMap map) {
    Action action = null;

    for (Exit exit : map.locationOf(actor).getExits()) {
      Location destination = exit.getDestination();
      if (destination.containsAnActor()) {
        if (!destination.getActor().hasCapability(Status.HOSTILE_TO_PLAYER)) {
          action = new AttackAction(destination.getActor(), exit.getName());
        }
      }
    }
    return action;
  }
}
