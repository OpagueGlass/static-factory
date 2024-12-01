package game.actors;

import engine.actions.Action;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import game.behaviours.PickUpBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Ability;
import game.utilities.RandomUtil;

import java.util.ArrayList;

/**
 * A class that represents an Alien Bug. Created by:
 * @author Nicolas Kai
 * Alien Bugs have the ability to pick up scraps from the ground, follow the player, and wander randomly.
 * When an Alien Bug becomes unconscious, it drops all items from its inventory.
 */
public class AlienBug extends Enemy {

  /**
   * Constructor for AlienBug class.
   * Initializes the Alien Bug with a random name prefixed with "Feature-" followed by a 3-digit random number,
   * and adds behaviors for picking up scraps, following the player, and wandering randomly.
   * Also adds capabilities for being hostile to the player and entering spaceships.
   */
  public AlienBug() {
    super("Feature-" + RandomUtil.generateRandomInt(100, 999), 'a', 2);
    this.addBehaviour(0, new PickUpBehaviour());
    this.addBehaviour(1, new FollowBehaviour());
    this.addBehaviour(2, new WanderBehaviour());
    this.addCapability(Ability.CAN_ENTER_SPACESHIP);
  }

  /**
   * Executes the actions when the AlienBug becomes unconscious.
   * Drops all items from its inventory.
   *
   * @param actor the actor who made the AlienBug unconscious
   * @param map the game map
   * @return the message to be displayed when the AlienBug becomes unconscious
   */
  @Override
  public String unconscious(Actor actor, GameMap map) {
    ArrayList<Action> actions = new ArrayList<>();
    for (Item item : getItemInventory()) {
      actions.add(item.getDropAction(this));
    }
    for (Action action : actions) {
      action.execute(this, map);
    }
    return super.unconscious(actor, map);
  }
}
