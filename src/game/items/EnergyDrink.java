package game.items;

import engine.items.Item;
import engine.actions.ActionList;
import engine.actors.Actor;
import game.actions.Consumable;
import game.actions.ConsumeAction;

/**
 * A class that defines an Energy Drink.
 *
 * @author Ryan Zhi Yin Hii
 */
public class EnergyDrink extends Item implements Purchasable, Consumable {

    private static final int DEFAULT_PRICE = 10;
    private static final int MULTIPLIER = 2;
    private static final double CHANCE_TO_MULTIPLY_PRICE = 0.2;
    private static final int DEFAULT_HEALING_AMOUNT = 1;
    private int price;

    /**
     * Constructor.
     */
    public EnergyDrink() {
        super("Energy Drink", '*', true);
        this.price = DEFAULT_PRICE;
    }

    /**
     * Returns the price of the Energy Drink.
     *
     * @return The price of the Energy Drink
     */
    public int getPrice() {
        return price;
    }

    /**
     * Allows the actor to purchase the Energy Drink.
     *
     * @param actor The actor purchasing the Energy Drink
     * @return The outcome of the purchase
     */
    @Override
    public String purchase(Actor actor) {
        String result;
        int currentPrice = getPrice();
        int balance = actor.getBalance();
        double chanceToMultiplyPrice = getChanceToMultiplyPrice();

        if (Math.random() < chanceToMultiplyPrice) {
            currentPrice = getMultipliedPrice();
        }

        if (balance < currentPrice) {
            result = String.format("%s does not have enough credits to purchase %s", actor, this);
        } else {
            actor.addItemToInventory(this);
            actor.deductBalance(currentPrice);
            result = String.format("%s successfully purchased %s for %d credits", actor, this, currentPrice);
        }

        return result;
    }

    /**
     * Returns the chance to multiply the price of the Energy Drink.
     *
     * @return The chance to multiply the price of the Energy Drink
     */
    private double getChanceToMultiplyPrice() {
        return CHANCE_TO_MULTIPLY_PRICE;
    }

    /**
     * Returns the multiplied price of the Energy Drink.
     *
     * @return The multiplied price of the Energy Drink
     */
    private int getMultipliedPrice() {
        return price * MULTIPLIER;
    }

    /**
     * Consumes the Energy Drink.
     *
     * @param target The actor consuming the Energy Drink
     */
    @Override
    public void consume(Actor target) {
        target.removeItemFromInventory(this);
        target.heal(DEFAULT_HEALING_AMOUNT);
      }

    /**
     * Returns a string representation of consuming the Energy Drink.
     *
     * @return A string representation of consuming the Energy Drink
     */
    @Override
    public String getConsumeEffect(Actor target) {
        return target + " drinks " + this + ". " + target + " feels energised.";
    }

    /**
     * A collection of Actions that the Energy Drink can perform on its owner.
     *
     * @param owner the Actor that owns the Energy Drink
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
