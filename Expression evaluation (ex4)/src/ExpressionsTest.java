import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author H
 *
 */
public class ExpressionsTest {
  /**
   * main.
   * @param args non
   */
  public static void main(String[] args) {
    Expression e = new Plus(new Plus(new Mult(2, "x"), new Sin(new Mult(4, "y"))), new Pow("e", "x"));
    System.out.println(e);
    Map<String, Double> map = new TreeMap<String, Double>();
    map.put("x", 2.0);
    map.put("y", 0.25);
    map.put("e", 2.71);
    try {
      System.out.println(e.evaluate(map));
    } catch (Exception e1) {
    System.out.println("Assigning variables error");
  }
    System.out.println(e.differentiate("x"));
    try {
    System.out.println(e.differentiate("x").evaluate(map));
  } catch (Exception e1) {
    System.out.println("Assignment variables in differentiated expression error");
  }
    System.out.println(e.differentiate("x").simplify());
  }
}
