package filesinput;

import java.awt.Color;

/**
 *
 * @author H
 *
 */
public class ColorsParser {

  /**
   * returning the color represented by the string.
   * @param s
   *          string describing a color in name / RGB form
   * @return java color
   */
  public java.awt.Color colorFromString(String s) {
    String colorString = s.substring(s.lastIndexOf("(") + 1, s.indexOf(")"));
    switch (colorString.trim()) {
    case "black":
      return Color.black;
    case "blue":
      return Color.blue;
    case "cyan":
      return Color.cyan;
    case "gray":
      return Color.gray;
    case "lightGray":
      return Color.lightGray;
    case "green":
      return Color.green;
    case "orange":
      return Color.orange;
    case "pink":
      return Color.pink;
    case "red":
      return Color.red;
    case "white":
      return Color.white;
    case "yellow":
      return Color.yellow;
    default:
      return fromRGB(s);
    }
  }

  /**
   * turning RGB presentation to a java color object.
   * @param s
   *          string with RGB description of a color
   * @return the color associated with it
   */
  private Color fromRGB(String s) {
    String numString = s.substring(s.lastIndexOf("(") + 1, s.indexOf(")"));
    if (!s.contains("RGB")) {
      System.out.println(s);
      throw new RuntimeException("illegal color argument");
    }
    String[] numArray = numString.split(",");
    if (numArray.length != 3) {
      throw new RuntimeException("illegal color argument");
    }
    int[] numInt = new int[3];
    for (int i = 0; i < 3; i++) {
      numInt[i] = Integer.parseInt(numArray[i].trim());
    }
    return new Color(numInt[0], numInt[1], numInt[2]);
  }
}
