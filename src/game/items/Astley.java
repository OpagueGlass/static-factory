package game.items;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.actors.attributes.BaseActorAttributes;
import engine.items.Item;
import engine.positions.Location;
import game.actions.MonologueAction;
import game.capabilities.Ability;
import game.utilities.RandomUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents an AI device called Astley that can be purchased by an actor.
 *
 * @author Ashley
 */
public class Astley extends Item implements Purchasable, Monologuable {

    private static final int DEFAULT_SUBSCRIPTION_INTERVAL = 5;
    private static final int DEFAULT_SUBSCRIPTION_FEE = 1;
    private static final int DEFAULT_PRICE = 50;
    private static final int INVENTORY_SIZE = 10;
    private static final int CREDITS = 50;
    private static final int HEALTH = 2;

    private Subscription subscription;
    private int price;

    /**
     * Constructor.
     */
    public Astley() {
        super("Astley, an AI device", 'z', true);
        this.subscription = new Subscription(DEFAULT_SUBSCRIPTION_FEE, DEFAULT_SUBSCRIPTION_INTERVAL);
        this.price = DEFAULT_PRICE;
    }

    /**
     * Performs the purchase of the AI device by an actor.
     *
     * @param actor the actor purchasing the AI device
     */
    @Override
    public String purchase(Actor actor) {
        String result;
        int balance = actor.getBalance();

        if (balance < price) {
            result = String.format("%s does not have enough credits to purchase %s", actor, this);
        } else {
            actor.addItemToInventory(this);
            actor.deductBalance(price);
            subscription.setActive(true);
            result = String.format("%s successfully purchased %s for %d credits", actor, this, price);
        }
        return result;
    }

    /**
     * Processes the subscription cycle of the AI device.
     *
     * @param currentLocation the current location of the actor
     * @param actor the actor that owns the AI device
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.hasCapability(Ability.CAN_USE_ASTLEY)) {
            subscription.processSubscriptionCycle(actor);
        }
    }

    /**
     * Returns a monologue from the AI device.
     *
     * @param actor the actor listening to the monologue
     * @return a string representing the monologue
     */
    @Override
    public String monologue(Actor actor) {
        List<String> monologues = new ArrayList<>();
        List<Item> inventory = actor.getItemInventory();
        int inventorySize = inventory.size();
        int credits = actor.getBalance();
        int health = actor.getAttribute(BaseActorAttributes.HEALTH);

        // Default monologues
        monologues.add("The factory will never gonna give you up, valuable intern!");
        monologues.add("We promise we never gonna let you down with a range of staff benefits.");
        monologues.add("We never gonna run around and desert you, dear intern!");

        // Conditional monologues
        if (inventorySize > INVENTORY_SIZE) {
            monologues.add("We never gonna make you cry with unfair compensation.");
        }
        if (credits > CREDITS) {
            monologues.add("Trust is essential in this business. We promise we never gonna say goodbye to a valuable intern like you.");
        }
        if (health < HEALTH) {
            monologues.add("Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.");
        }

        int monologuesSize = monologues.size();
        int index = RandomUtil.generateRandomInt(0, monologuesSize);
        return monologues.get(index);
    }

    /**
     * List of allowable actions that the item can perform to the current actor.
     *
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        if (subscription.isActive() && owner.hasCapability(Ability.CAN_USE_ASTLEY)) {
            actions.add(new MonologueAction(this));
        }
        return actions;
    }
}