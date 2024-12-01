package game.behaviours;

import java.util.List;

import engine.items.Item;
import engine.positions.Exit;
import engine.positions.Location;
import game.spawner.ItemSpawner;
import game.utilities.RandomUtil;

/**
 * A class that defines the drop behaviour of a plant.
 *
 * @author Ryan Zhi Yin Hii
 */
public class DropBehaviour implements PlantBehaviour {

    private ItemSpawner fruitSpawner;

    /**
     * Constructor.
     *
     * @param fruitSpawner the spawner of the fruit
     */
    public DropBehaviour(ItemSpawner fruitSpawner) {
        this.fruitSpawner = fruitSpawner;
    }

    /**
     * Execute the behaviour.
     *
     * @param location the location of the plant
     * @return true if the behaviour is executed, false otherwise
     */
    @Override
    public boolean execute(Location location) {
        Boolean canSpawn = fruitSpawner.canSpawn();
        if (canSpawn) {
            List<Exit> exits = location.getExits();
            Boolean hasExits = !exits.isEmpty();

            if (hasExits) {
                int exitsSize = exits.size();
                int randomIndex = RandomUtil.generateRandomInt(0, exitsSize);
                Exit randomExit = exits.get(randomIndex);
                Location spawnLocation = randomExit.getDestination();
                Item fruit = fruitSpawner.createItem();
                spawnLocation.addItem(fruit);
                return true;
            }
        }
        return false;
    }
}
