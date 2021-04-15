package cs5004.animator.model;

/**
 * This enum represents a set of names for different shapes.
 */
public enum NameOfShape {
  R("rectangle"), C("oval");

  private final String str;

  /**
   * Construct a NameOfShape object which takes in a string input.
   */
  NameOfShape(String type) {
    str = type;
  }

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  public String toString() {
    return str;
  }
}
