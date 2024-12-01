package game.behaviours;

import engine.positions.Location;

/**
 * A class that defines a Plant Behaviour.
 *
 * @author Ryan Zhi Yin Hii
 */
public interface PlantBehaviour {

    /**
     * Execute the behaviour.
     *
     * @param location the location of the plant
     * @return true if the behaviour is executed, false otherwise
     */
    boolean execute(Location location);
}
