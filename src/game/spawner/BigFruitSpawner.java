package game.spawner;

import engine.items.Item;
import game.items.BigFruit;

/**
 * A class that defines a Big Fruit Spawner.
 *
 * @author Ryan Zhi Yin Hii
 */
public class BigFruitSpawner implements ItemSpawner {

  private static final double SPAWN_PROBABILITY = 0.2;

  /**
   * Create a Big Fruit item.
   *
   * @return a Big Fruit item
   */
  @Override
  public Item createItem() {
    return new BigFruit();
  }

  /**
   * Returns a boolean value to determine if the Big Fruit item can be spawned.
   *
   * @return true if the Big Fruit item can be spawned, false otherwise
   */
  @Override
  public boolean canSpawn() {
    return Math.random() < SPAWN_PROBABILITY;
  }
}
