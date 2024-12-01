package game.behaviours;

import engine.positions.Location;
import game.grounds.Tree;

/**
 * A class that defines the grow behaviour of a plant.
 *
 * @author Ryan Zhi Yin Hii
 */
public class GrowBehaviour implements PlantBehaviour {

    private int age;
    private int tickDuration;
    private Tree nextStage;

    /**
     * Constructor.
     *
     * @param tickDuration the duration of the current stage
     * @param nextStage the next stage of the plant
     */
    public GrowBehaviour(int tickDuration, Tree nextStage) {
        this.tickDuration = tickDuration;
        this.nextStage = nextStage;
    }

    /**
     * Execute the behaviour.
     *
     * @param location the location of the plant
     * @return true if the behaviour is executed, false otherwise
     */
    @Override
    public boolean execute(Location location) {
        age++;
        if (this.age == this.tickDuration) {
            location.setGround(this.nextStage);
            return true;
        }
        return false;
    }
}
