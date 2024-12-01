package game.spawner;

import engine.actors.Actor;
import game.actors.SuspiciousAstronaut;

/**
 * A class that defines a SuspiciousAstronautSpawner. Created by:
 *
 * @author Nicolas Kai
 */
public class SuspiciousAstronautSpawner implements ActorSpawner {

  private static final double SPAWN_PROBABILITY = 0.05;

  /**
   * Create a SuspiciousAstronaut actor
   *
   * @return a SuspiciousAstronaut actor
   */
  @Override
  public Actor createActor() {
    return new SuspiciousAstronaut();
  }

  /**
   * Returns a boolean value to determine if the SuspiciousAstronaut can be spawned.
   *
   * @return true if the SuspiciousAstronaut can be spawned, false otherwise
   */
  @Override
  public boolean canSpawn() {
    return Math.random() < SPAWN_PROBABILITY;
  }
}