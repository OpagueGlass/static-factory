package game.utilities;

/**
 * Utility class for common useful methods. Created by:
 *
 * @author Jonah Yip Mathivanan
 */
public class RandomUtil {

  /**
   * Generates a random integer between the lower and upper bounds.
   *
   * @param lower the lower bound of the generated number as an integer
   * @param upper the upper bound of the generated number as an integer
   * @return the randomly generated number as an integer
   */
  public static int generateRandomInt(int lower, int upper) {
    int number = (int) (Math.random() * (upper - lower)) + lower;
    return number;
  }
}
