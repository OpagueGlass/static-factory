package game.weapons;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.items.Purchasable;

/**
 * A class that defines a Dragon Slayer Sword.
 *
 * @author Ryan Zhi Yin Hii
 */
public class DragonSlayerSword extends WeaponItem implements Purchasable {
    private static final int DEFAULT_WEAPON_DAMAGE = 50;
    private static final int DEFAULT_WEAPON_HIT_RATE = 75;
    private static final int DEFAULT_PRICE = 100;
    private static final double CHANCE_TO_MALFUNCTION = 0.5;
    private int price;


    /**
     * Constructor.
     */
    public DragonSlayerSword() {
        super("Dragon Slayer Replica", 'x', DEFAULT_WEAPON_DAMAGE, "slashes",
            DEFAULT_WEAPON_HIT_RATE);
        this.price = DEFAULT_PRICE;
    }

    /**
     * Returns the price of the Dragon Slayer Sword.
     *
     * @return The price of the Dragon Slayer Sword
     */
    public int getPrice() {
        return price;
    }

    /**
     * Allows the actor to purchase the Dragon Slayer Sword.
     *
     * @param actor The actor purchasing the Dragon Slayer Sword
     * @return The outcome of the purchase
     */
    @Override
    public String purchase(Actor actor) {
        String result;
        int currentPrice = getPrice();
        int balance = actor.getBalance();

        if (balance < currentPrice) {
            result = String.format("%s does not have enough credits to purchase %s", actor, this);
        } else {
            actor.deductBalance(currentPrice);
            double chanceToMalfunction = getChanceToMalfunction();

            if (Math.random() < chanceToMalfunction) {
                result = String.format("%d credits are taken from %s, but %s doesn't receive anything in return!", getPrice(), actor, actor);
            } else {
                actor.addItemToInventory(this);
                result = String.format("%s successfully purchased %s for %d credits", actor, this, getPrice());
            }
        }

        return result;
    }

    /**
     * Returns the chance for the purchase to malfunction.
     *
     * @return The chance for the purchase to malfunction
     */
    private double getChanceToMalfunction() {
        return CHANCE_TO_MALFUNCTION;
    }

    /**
     * A collection of Actions that the Dragon Slayer Sword allows its owner to perform on other actors.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            return new ActionList(new AttackAction(otherActor, location.toString(), this));
        }
        return new ActionList();
	}
}
