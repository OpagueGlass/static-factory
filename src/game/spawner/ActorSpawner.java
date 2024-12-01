package game.spawner;

import engine.actors.Actor;

/**
 * A class that defines an Actor Spawner
 *
 * @author Ryan Zhi Yin Hii
 */
public interface ActorSpawner {

  /**
   * Create an actor to be spawned.
   *
   * @return an actor to be spawned
   */
  Actor createActor();

  /**
   * Returns a boolean value to determine if the actor can be spawned.
   *
   * @return true if the actor can be spawned, false otherwise
   */
  boolean canSpawn();
}
