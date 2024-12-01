package game.actors;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actors.Actor;
import engine.actors.attributes.BaseActorAttributes;
import engine.displays.Display;
import engine.displays.Menu;
import engine.positions.GameMap;
import engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.displays.FancyMessage;

/**
 * Class representing the Player.
 *
 * @author Jonah Yip Mathivanan
 */
public class Player extends Actor {

  /**
   * Constructor.
   *
   * @param name        Name to call the player in the UI
   * @param displayChar Character to represent the player in the UI
   * @param hitPoints   Player's starting number of hitpoints
   */
  public Player(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.HOSTILE_TO_ENEMY);
    this.addCapability(Ability.CAN_ENTER_SPACESHIP);
    this.addCapability(Ability.CAN_USE_ASTLEY);
  }

  /**
   * Creates and returns an intrinsic weapon.
   *
   * @return a freshly-instantiated IntrinsicWeapon
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(1, "punches", 5);
  }

  @Override
  public String unconscious(Actor actor, GameMap map) {
    String output = super.unconscious(actor, map);
    output += "\n\n" + FancyMessage.YOU_ARE_FIRED;
    return output;
  }

  @Override
  public String unconscious(GameMap map) {
    String output = super.unconscious(map);
    output += "\n\n" + FancyMessage.YOU_ARE_FIRED;
    return output;
  }

  /**
   * Select and return an action to perform on the current turn.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *                   conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the Action to be performed
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    display.println(name);
    display.println("HP: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/"
        + this.getAttributeMaximum(BaseActorAttributes.HEALTH));
    display.println("Balance: " + this.getBalance());

    // Handle multi-turn Actions
    if (lastAction.getNextAction() != null) {
      return lastAction.getNextAction();
    }

    // return/print the console menu
    Menu menu = new Menu(actions);
    return menu.showMenu(this, display);
  }
}
