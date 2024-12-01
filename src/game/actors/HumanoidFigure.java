package game.actors;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import game.capabilities.Ability;

/**
 * Class representing a Humanoid Figure. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class HumanoidFigure extends Actor {

  /**
   * Constructor.
   */
  public HumanoidFigure() {
    super("Humanoid Figure", 'H', Integer.MAX_VALUE);
    this.addCapability(Ability.CAN_BUY_SELLABLES);
  }

  /**
   * Returns an Action to be performed during its turn. This Actor will do nothing.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return The do nothing action
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
