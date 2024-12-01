package game;

import engine.displays.Display;
import engine.positions.FancyGroundFactory;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.positions.World;
import game.actors.HumanoidFigure;
import game.actors.HuntsmanSpider;
import game.actors.Player;
import game.behaviours.DropBehaviour;
import game.behaviours.GrowBehaviour;
import game.displays.FancyMessage;
import game.grounds.ComputerTerminal;
import game.grounds.Crater;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.MatureInheritree;
import game.grounds.YoungInheritree;
import game.items.Astley;
import game.items.EnergyDrink;
import game.items.JarOfPickles;
import game.items.LargeBolt;
import game.items.MetalSheet;
import game.items.PotOfGold;
import game.items.Purchasable;
import game.items.Thesues;
import game.items.ToiletPaperRoll;
import game.grounds.SaplingInheritree;
import game.grounds.SproutInheritree;
import game.grounds.Tree;
import game.grounds.Puddle;
import game.grounds.Wall;
import game.spawner.AlienBugSpawner;
import game.spawner.HuntsmanSpiderSpawner;
import game.spawner.SmallFruitSpawner;
import game.spawner.BigFruitSpawner;
import game.spawner.SuspiciousAstronautSpawner;
import game.weapons.DragonSlayerSword;
import game.weapons.MetalPipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main class to start the game. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class Application {

  /**
   * The main method to start the game.
   *
   * @param args The arguments passed to the main method.
   */
  public static void main(String[] args) {

    World world = new World(new Display());
    // Create the ground factories
    FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
        new Wall(), new Floor(), new Puddle());

    FancyGroundFactory staticFactory = new FancyGroundFactory(new Dirt(),
        new Wall(), new Floor());

    FancyGroundFactory connascence = new FancyGroundFactory(new Dirt(),
        new Wall(), new Floor(), new Puddle());

    // Rows of each map
    List<String> polymorphiaMap = Arrays.asList(
        "...~~~~.........~~~...........",
        "...~~~~.......................",
        "...~~~........................",
        "..............................",
        ".............#####............",
        ".............#___#...........~",
        ".............#___#..........~~",
        ".............##_##.........~~~",
        ".................~~........~~~",
        "................~~~~.......~~~",
        ".............~~~~~~~........~~",
        "......~.....~~~~~~~~.........~",
        ".....~~~...~~~~~~~~~..........",
        ".....~~~~~~~~~~~~~~~~........~",
        ".....~~~~~~~~~~~~~~~~~~~....~~");

    List<String> staticFactoryMap = Arrays.asList(
        ".......",
        ".#####.",
        ".#___#.",
        ".#___#.",
        ".##_##.",
        ".......",
        ".......",
        ".......",
        ".......",
        ".......");

    List<String> connascenceMap = Arrays.asList(
        "..........................~~~~",
        "..........................~~~~",
        "..........................~~~~",
        "~..........................~..",
        "~~...........#####............",
        "~~~..........#___#............",
        "~~~..........#___#............",
        "~~~..........##_##............",
        "~~~..................~~.......",
        "~~~~................~~~~......",
        "~~~~...............~~~~~......",
        "..~................~~~~.......",
        "....................~~........",
        ".............~~...............",
        "............~~~~..............");

    // Create the game maps
    GameMap polymorphiaGameMap = new GameMap(groundFactory, polymorphiaMap);
    GameMap staticFactoryGameMap = new GameMap(staticFactory, staticFactoryMap);
    GameMap connascenceGameMap = new GameMap(connascence, connascenceMap);

    // Add the game maps to the world
    world.addGameMap(polymorphiaGameMap);
    world.addGameMap(staticFactoryGameMap);
    world.addGameMap(connascenceGameMap);

    // Add the purchasables into an items list
    ArrayList<Purchasable> items = new ArrayList<>();
    items.add(new DragonSlayerSword());
    items.add(new EnergyDrink());
    items.add(new ToiletPaperRoll());
    items.add(new Thesues());
    items.add(new Astley());

    // Add the travel locations
    Map<String, Location> travelLocations = new HashMap<>();
    travelLocations.put("Polymorphia", polymorphiaGameMap.at(15, 6));
    travelLocations.put("Connascence", connascenceGameMap.at(15, 6));
    travelLocations.put("the Static Factory", staticFactoryGameMap.at(3, 3));

    // Set the grounds in the Polymorphia map
    polymorphiaGameMap.at(15, 5).setGround(new ComputerTerminal(items, travelLocations));
    polymorphiaGameMap.at(21, 11).setGround(new Crater(new HuntsmanSpiderSpawner()));
    polymorphiaGameMap.at(10, 13).setGround(new Crater(new HuntsmanSpiderSpawner()));
    polymorphiaGameMap.at(3, 6).setGround(new Crater(new AlienBugSpawner()));
    polymorphiaGameMap.at(13, 1).setGround(new Crater(new SuspiciousAstronautSpawner()));

    // Add items to the Polymorphia map
    polymorphiaGameMap.at(5, 7).addItem(new LargeBolt());
    polymorphiaGameMap.at(5, 8).addItem(new MetalSheet());
    polymorphiaGameMap.at(5, 9).addItem(new MetalPipe());
    polymorphiaGameMap.at(5, 10).addItem(new JarOfPickles());
    polymorphiaGameMap.at(5, 11).addItem(new PotOfGold());

    // Add actors to the Polymorphia map
    polymorphiaGameMap.at(7, 9).addActor(new HuntsmanSpider());

    // Set the grounds in the Parking Lot map
    staticFactoryGameMap.at(3, 2).setGround(new ComputerTerminal(items, travelLocations));
    staticFactoryGameMap.at(3,9).addActor(new HumanoidFigure());

    // Set the grounds in the Refactorio map
    connascenceGameMap.at(15, 5).setGround(new ComputerTerminal(items, travelLocations));

    // Add the player to the game in the Polymorphia map
    Player player = new Player("Intern", '@', 4);
    world.addPlayer(player, polymorphiaGameMap.at(15, 6));
    player.addBalance(1000);

    // Add a tree in Polymorphia
    Tree saplingInheritree = new SaplingInheritree();
    Tree matureInheritree = new MatureInheritree();

    polymorphiaGameMap.at(15, 12).setGround(saplingInheritree);

    saplingInheritree.addBehaviour(0, new GrowBehaviour(5, matureInheritree));
    saplingInheritree.addBehaviour(1, new DropBehaviour(new SmallFruitSpawner()));

    matureInheritree.addBehaviour(0, new DropBehaviour(new BigFruitSpawner()));

    // Add a tree in Connascence
    Tree sproutInheritree2 = new SproutInheritree();
    Tree saplingInheritree2 = new SaplingInheritree();
    Tree youngInheritree2 = new YoungInheritree();
    Tree matureInheritree2 = new MatureInheritree();

    connascenceGameMap.at(15, 11).setGround(sproutInheritree2);

    sproutInheritree2.addBehaviour(0, new GrowBehaviour(3, saplingInheritree2));

    saplingInheritree2.addBehaviour(0, new GrowBehaviour(6, youngInheritree2));
    saplingInheritree2.addBehaviour(1, new DropBehaviour(new SmallFruitSpawner()));

    youngInheritree2.addBehaviour(0, new GrowBehaviour(5, matureInheritree2));

    matureInheritree2.addBehaviour(0, new DropBehaviour(new BigFruitSpawner()));

    // Display the title
    for (String line : FancyMessage.TITLE.split("\n")) {
      new Display().println(line);
      try {
        Thread.sleep(200);
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    world.run();
  }
}
