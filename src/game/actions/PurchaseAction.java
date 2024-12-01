package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.items.Purchasable;

/**
 * A class that defines a Purchase Action.
 *
 * @author Jonah Yip Mathivanan
 */
public class PurchaseAction extends Action {

    private Purchasable item;

    /**
     * Constructor.
     *
     * @param item The item to be purchased.
     */
    public PurchaseAction(Purchasable item) {
        this.item = item;
    }

    /**
     * Perform the Purchase Action.
     *
     * @param actor The actor performing the action.
     * @return A description of the action after it is executed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = item.purchase(actor);
        return result;
    }

    /**
     * Returns a description of the action.
     *
     * @param actor The actor performing the action.
     * @return A string description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + item;
    }
}
