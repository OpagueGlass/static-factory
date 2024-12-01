package game.grounds;

import engine.positions.Ground;
import engine.positions.Location;
import engine.actors.Actor;
import game.spawner.ActorSpawner;

/**
 * A class that represents a Crater. Created by:
 *
 * @author Ryan Zhi Yin Hii
 */
public class Crater extends Ground {

    private ActorSpawner spawner;

    /**
     * Constructor.
     *
     * @param spawner The spawner that will spawn the actor.
     */
    public Crater(ActorSpawner spawner) {
        super('u');
        this.spawner = spawner;
    }

    /**
     * Spawn an actor in the location with a probability.
     *
     * @param location The location to spawn the actor.
     */
    private void spawn(Location location) {
        boolean canSpawn = spawner.canSpawn();
        boolean containsAnActor = location.containsAnActor();

        if (canSpawn && !containsAnActor) {
            Actor actor = spawner.createActor();
            location.addActor(actor);
        }
    }

    /**
    * Represents the passage of time.
    *
    * @param location The location of the Ground.
    */
    @Override
    public void tick(Location location) {
        spawn(location);
    }
}
