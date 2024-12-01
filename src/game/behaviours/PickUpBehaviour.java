package game.behaviours;

import engine.actions.Action;
import engine.actors.Actor;
import engine.actors.Behaviour;
import engine.items.Item;
import engine.positions.GameMap;
import game.utilities.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A Behaviour implementation representing the behavior of an Actor picking up items from the current location. Created by:
 *
 * @author Ashley
 */
public class PickUpBehaviour implements Behaviour {

    /**
     * Retrieves the next action for the actor based on the current state and game map.
     *
     * @param actor the Actor performing the behavior
     * @param map the GameMap where the actor is located
     * @return the next Action for the actor, which may include picking up an item
     */
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();
        List<Item> items = map.locationOf(actor).getItems();
        for (Item item : items) {
            actions.add(item.getPickUpAction(actor));
        }
        if (!actions.isEmpty()) {
            return actions.get(RandomUtil.generateRandomInt(0, actions.size()));
        } else {
            return null;
        }
    }
}

