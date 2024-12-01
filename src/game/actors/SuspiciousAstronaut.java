package game.actors;

import engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * A class that represents Suspicious Astronaut.
 * Suspicious Astronauts have a high precision attack that instantly kills the player.
 *
 * @author Nicolas Kai
 */
public class SuspiciousAstronaut extends Enemy {
    /**
     * Constructor for SuspiciousAstronaut.
     * Sets its name, display character, and priority for behaviors.
     * Initializes behaviors for attacking and wandering.
     * Adds capability for being hostile to the player.
     */
    public SuspiciousAstronaut() {
        super("Suspicious Astronaut", 'ඞ', 99);
        this.addBehaviour(0, new AttackBehaviour());
        this.addBehaviour(1, new WanderBehaviour());
    }

    /**
     * Retrieves the intrinsic weapon of the Suspicious Astronaut.
     * Suspicious Astronauts have a high precision attack with default damage.
     *
     * @return the intrinsic weapon of the Suspicious Astronaut
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        // Suspicious Astronaut has a high precision attack with default damage
        return new IntrinsicWeapon(Integer.MAX_VALUE, "bonks", 100);
    }
}