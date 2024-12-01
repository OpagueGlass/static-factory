package game.spawner;

import engine.items.Item;

/**
 * A class that defines an Item Spawner.
 *
 * @author Ryan Zhi Yin Hii
 */
public interface ItemSpawner {

  /**
   * Create an item.
   *
   * @return an item
   */
  Item createItem();

  /**
   * Returns a boolean value to determine if the item can be spawned.
   *
   * @return true if the item can be spawned, false otherwise
   */
  boolean canSpawn();
}
