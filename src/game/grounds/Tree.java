package game.grounds;

import java.util.TreeMap;

import engine.positions.Ground;
import engine.positions.Location;
import game.behaviours.PlantBehaviour;

/**
 * A class that defines a tree.
 *
 * @author Ryan Zhi Yin Hii
 */
public abstract class Tree extends Ground {

    private TreeMap<Integer, PlantBehaviour> behaviours;

    /**
     * Constructor.
     *
     * @param displayChar the character to display for this tree
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.behaviours = new TreeMap<>();
    }

    /**
     * Add a behaviour to the tree.
     *
     * @param priority the priority of the behaviour
     * @param behaviour the behaviour to add
     */
    public void addBehaviour(int priority, PlantBehaviour behaviour) {
        behaviours.put(priority, behaviour);
    }

    /**
     * Execute the behaviours of the tree.
     *
     * @param location the location of the tree
     */
    @Override
    public void tick(Location location) {
        for (PlantBehaviour behaviour : behaviours.values()) {
            Boolean isSuccess = behaviour.execute(location);
            if (isSuccess) {
                return;
            }
        }
    }
}
