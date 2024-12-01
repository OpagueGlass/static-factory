package game.spawner;

import engine.items.Item;
import game.items.SmallFruit;

/**
 * A class that defines a Small Fruit Spawner.
 *
 * @author Ryan Zhi Yin Hii
 */
public class SmallFruitSpawner implements ItemSpawner {

  private static final double SPAWN_PROBABILITY = 0.3;

  /**
   * Create a Small Fruit item.
   *
   * @return a Small Fruit item
   */
  @Override
  public Item createItem() {
    return new SmallFruit();
  }

  /**
   * Returns a boolean value to determine if the Small Fruit item can be spawned.
   *
   * @return true if the Small Fruit item can be spawned, false otherwise
   */
  @Override
  public boolean canSpawn() {
    return Math.random() < SPAWN_PROBABILITY;
  }
}
