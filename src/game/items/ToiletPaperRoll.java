package game.items;

import engine.actions.ActionList;
import engine.items.Item;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.SellAction;
import game.capabilities.Ability;

/**
 * A class that defines a Toilet Paper Roll.
 *
 * @author Ryan Zhi Yin Hii
 */
public class ToiletPaperRoll extends Item implements Purchasable, Sellable{
    private static final int DEFAULT_PRICE = 5;
    private static final int NEW_PRICE = 1;
    private static final double CHANCE_FOR_NEW_PRICE = 0.75;
    private static final int DEFAULT_SELLING_PRICE = 1;
    private static final double CHANCE_FOR_DEATH = 0.5;
    private int price;
    private int sellingPrice;

    /**
     * Constructor.
     */
    public ToiletPaperRoll() {
        super("Toilet Paper Roll", 's', true);
        this.price = DEFAULT_PRICE;
        this.sellingPrice = DEFAULT_SELLING_PRICE;
    }

    /**
     * Returns the price of the Toilet Paper Roll.
     *
     * @return The price of the Toilet Paper Roll
     */
    public int getPrice() {
        return price;
    }

    /**
     * Allows the actor to purchase the Toilet Paper Roll.
     *
     * @param actor The actor purchasing the Toilet Paper Roll
     * @return The outcome of the purchase
     */
    @Override
    public String purchase(Actor actor) {
        String result;
        int currentPrice = getPrice();
        int balance = actor.getBalance();
        double chanceForNewPrice = getChanceForNewPrice();

        if (Math.random() < chanceForNewPrice) {
            currentPrice = getNewPrice();
        }

        if (balance < currentPrice) {
            result = String.format("%s does not have enough credits to purchase %s", actor, this);
        } else {
            actor.addItemToInventory(this);
            actor.deductBalance(currentPrice);
            result =  String.format("%s successfully purchased %s for %d credits", actor, this, currentPrice);
        }
        return result;
    }

    /**
     * Returns the chance for a new price of the Toilet Paper Roll.
     *
     * @return The chance for a new price of the Toilet Paper Roll
     */
    private double getChanceForNewPrice() {
        return CHANCE_FOR_NEW_PRICE;
    }

    /**
     * Returns the new price of the Toilet Paper Roll.
     *
     * @return The new price of the Toilet Paper Roll
     */
    private int getNewPrice() {
        return NEW_PRICE;
    }

    /**
     * Sells the item and applies the outcome to the actor.
     *
     * @param actor The actor selling the item
     * @param map The map the actor is on
     * @return The outcome of the sale
     */
    @Override
    public String sell(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        if (Math.random() < CHANCE_FOR_DEATH) {
            return actor.unconscious(map);
        }
        actor.addBalance(sellingPrice);
        return actor + " sold " + this + " for " + sellingPrice + " credits.";
    }

    /**
     * Returns a list of allowable actions for the other actor to perform on this item.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.CAN_BUY_SELLABLES)) {
            actions.add(new SellAction(this));
        }
        return actions;
    }
}
