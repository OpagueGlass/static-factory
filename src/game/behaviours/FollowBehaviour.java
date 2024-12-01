package game.behaviours;

import engine.actors.Actor;
import engine.actions.Action;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.actions.MoveActorAction;
import engine.actors.Behaviour;
import game.capabilities.Status;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 *
 * @author Ashley
 */
public class FollowBehaviour implements Behaviour {

    private Actor target;

    /**
     * Gets the action for the actor to follow the target actor.
     *
     * @param actor the actor performing the action
     * @param map the game map containing the actor and target
     * @return a MoveActorAction to move the actor closer to the target, or null if no target is found
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (target == null) {
            searchTarget(actor, map);
        }
        if (target != null) {
            return followTarget(actor, map);
        }
        return null;
    }

    /**
     * Searches for a target actor to follow.
     *
     * @param actor the actor performing the action
     * @param map the game map containing the actor and potential targets
     */
    public void searchTarget(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (!destination.getActor().hasCapability(Status.HOSTILE_TO_PLAYER)) {
                    target = destination.getActor();
                }
            }
        }
    }

    /**
     * Moves the actor closer to the target actor.
     *
     * @param actor the actor performing the action
     * @param map the game map containing the actor and target
     * @return a MoveActorAction to move the actor closer to the target, or null if no valid move is available
     */
    public Action followTarget(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
