package game.actors;

import engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * A class that represents Huntsman Spider. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class HuntsmanSpider extends Enemy {

  /**
   * Constructor.
   */
  public HuntsmanSpider() {
    super("Huntsman Spider", '8', 1);
    super.addBehaviour(0, new AttackBehaviour());
    super.addBehaviour(999, new WanderBehaviour());
  }

  /**
   * Creates and returns an intrinsic weapon.
   *
   * @return a freshly-instantiated IntrinsicWeapon
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(1, "kicks", 25);
  }
}
