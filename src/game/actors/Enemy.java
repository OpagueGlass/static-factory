package game.actors;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.actors.Behaviour;
import engine.displays.Display;
import engine.positions.GameMap;
import game.actions.AttackAction;
import game.capabilities.Status;
import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract class representing an enemy in the game. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public abstract class Enemy extends Actor {

  private Map<Integer, Behaviour> behaviours = new TreeMap<>();

  /**
   * Constructor.
   *
   * @param name        The name to call the enemy in the UI
   * @param displayChar Character to represent the enemy in the UI
   * @param hitPoints   Enemy's starting number of hitpoints
   */
  public Enemy(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.HOSTILE_TO_PLAYER);
  }

  /**
   * Add a behaviour to the enemy.
   *
   * @param key   The priority of the behaviour. Lower values are higher priority.
   * @param value The behaviour to add.
   */
  public void addBehaviour(int key, Behaviour value) {
    behaviours.put(key, value);
  }

  /**
   * At each turn, select a valid action to perform.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *                   conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the valid action that can be performed in that iteration or null if no valid action is
   * found
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    for (Behaviour behaviour : behaviours.values()) {
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        return action;
      }
    }
    return new DoNothingAction();
  }

  /**
   * Returns a new collection of the Actions that the otherActor can do to the HuntsmanSpider.
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return A collection of Actions.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      actions.add(new AttackAction(this, direction));
    }
    return actions;
  }
}
