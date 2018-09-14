import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 *
 * @author H
 *
 */
public abstract class BaseExpression implements Expression {

  /**
  * calculate the value of an expression.
  * @param assignment a map of variables and their values
  * @return the answer
  * @throws Exception if a variable is not assigned
  */
  public abstract double evaluate(Map<String, Double> assignment) throws Exception;

  /**
 * calculate the Expression.
 * @return the value
 * @throws Exception if a variable is not assigned
 */
  public double evaluate() throws Exception {
    Map<String, Double> assignment = new TreeMap<String, Double>();
    return this.evaluate(assignment);
  }

  /**
   * is an Expression without variables.
   * @return true if no variables
   */
   public boolean isNumber() {
    return (this.getVariables().isEmpty());
  }

   /**
    * is an Expression equal to a number.
    * @param n the number
    * @return true if equal
    */
   public boolean isNumberN(double n) {
     if (this.isNumber()) {
       try {
      return (this.evaluate() == n);
    } catch (Exception e) {
      System.out.println("is Number n error");;
    }
     }
     return false;
   }

   /**
    * get the variables without repetition.
    * @param list old variables list
    * @return new list
    */
   public List<String> noRepetitionVariables(List<String> list) {
     List<String> newList = new ArrayList<String>();
     for (String s : list) {
       if (!newList.contains(s)) {
         newList.add(s);
       }
     }
     return newList;

   }

}
