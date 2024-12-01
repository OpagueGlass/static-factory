package game.items;

import engine.actors.Actor;
import engine.displays.Display;

/**
 * A class that represents a subscription.
 *
 * @author Ashley
 */
public class Subscription {

    private static final int STARTING_CYCLE_COUNT = 0;
    private static final boolean STARTING_STATE = true;

    private int fee;
    private int interval;
    private int cycleCount;
    private Display display;
    private boolean isActive;

    /**
     * Constructor.
     *
     * @param fee      the fee for the subscription
     * @param interval the interval at which the subscription fee is charged
     */
    public Subscription(int fee, int interval) {
        this.fee = fee;
        this.interval = interval;
        this.cycleCount = STARTING_CYCLE_COUNT;
        this.display = new Display();
        this.isActive = STARTING_STATE;
    }

    /**
     * Processes a subscription cycle for an actor.
     *
     * @param actor the actor with the subscription
     */
    public void processSubscriptionCycle(Actor actor) {
        cycleCount++;

        if (cycleCount % interval == 0) {
            if (actor.getBalance() >= fee) {
                actor.deductBalance(fee);
                display.println("Subscription payment received!");
                setActive(true);
            } else {
                display.println("Insufficient credits. Subscription paused.");
                setActive(false);
            }
        }
    }

    /**
     * Returns the status of the subscription.
     *
     * @return true if the subscription is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the status of the subscription.
     *
     * @param isActive true if the subscription is active, false otherwise
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
