package game.capabilities;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 */
public enum Status {
    /**
     * Status of the actor when they are hostile to the enemy.
     */
    HOSTILE_TO_ENEMY,

    /**
     * Status of the actor when they are hostile to the player.
     */
    HOSTILE_TO_PLAYER,
}
