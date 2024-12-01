package game.spawner;

import engine.actors.Actor;
import game.actors.HuntsmanSpider;

/**
 * A class that defines a HuntsmanSpiderSpawner. Created by:
 *
 * @author Ryan Zhi Yin Hii
 */
public class HuntsmanSpiderSpawner implements ActorSpawner {

  private static final double SPAWN_PROBABILITY = 0.05;

  /**
   * Create a HuntsmanSpider actor
   *
   * @return a HuntsmanSpider actor
   */
  @Override
  public Actor createActor() {
    return new HuntsmanSpider();
  }

  /**
   * Returns a boolean value to determine if the HuntsmanSpider can be spawned.
   *
   * @return true if the HuntsmanSpider can be spawned, false otherwise
   */
  @Override
  public boolean canSpawn() {
    return Math.random() < SPAWN_PROBABILITY;
  }
}
