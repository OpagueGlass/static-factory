package game.spawner;

import engine.actors.Actor;
import game.actors.AlienBug;

/**
 * A class that defines a AlienBugSpawner. Created by:
 *
 * @author Nicolas Kai
 */
public class AlienBugSpawner implements ActorSpawner {

  private static final double SPAWN_PROBABILITY = 0.10;

  /**
   * Create a AlienBug actor
   *
   * @return a AlienBug actor
   */
  @Override
  public Actor createActor() {
    return new AlienBug();
  }

  /**
   * Returns a boolean value to determine if the AlienBug can be spawned.
   *
   * @return true if the AlienBug can be spawned, false otherwise
   */
  @Override
  public boolean canSpawn() {
    return Math.random() < SPAWN_PROBABILITY;
  }
}
