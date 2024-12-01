package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.items.Monologuable;

/**
 * Action that allows an actor to listen to a monologue from a Monologuable object.
 *
 * @author Ashley
 */
public class MonologueAction extends Action {

    private Monologuable monologuable;

    /**
     * Constructor.
     *
     * @param monologuable the Monologuable object to listen to
     */
    public MonologueAction(Monologuable monologuable) {
        this.monologuable = monologuable;
    }

    /**
     * Executes the action of listening to the monologue.
     *
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a string describing the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return monologuable + ": \"" + monologuable.monologue(actor) + "\"";
    }

    /**
     * Returns a string describing the action.
     *
     * @param actor the actor performing the action
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + monologuable;
    }
}