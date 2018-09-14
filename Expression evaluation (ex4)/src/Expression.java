import java.util.List;
import java.util.Map;

/**
 *
 * @author H
 *
 */
public interface Expression {
   /**
    * calculate value.
    * @param assignment a map of variable to value
    * @return result
    * @throws Exception if there are no variables
    */
   double evaluate(Map<String, Double> assignment) throws Exception;

   /**
    * calculate value.
    * @return result
    * @throws Exception if there are no variables
    */
   double evaluate() throws Exception;

   /**
    * Returns a list of the variables in the expression.
    * @return the list
    */
   List<String> getVariables();

   /**
    * Returns a string representation of the expression.
    * @return the String
    */
   String toString();

   /**
    * Returns a new expression in which all occurrences of a variable
    * are replaced with the provided expression (Does not modify the
    current expression).
    * @param var the variable to assign
    * @param expression the value to assign the variable
    * @return the Expression
    */
   Expression assign(String var, Expression expression);

   /**
    * calculate the differential of the Expression.
    * @param var the differential is done on the Variable var
    * @return result
    */
   Expression differentiate(String var);

   /**
    * simplify a complicated expression.
    * @return a shorter simple version
    */
   Expression simplify();

   /**
    * check if an expression is a number.
    * @return true if it is
    */
   boolean isNumber();

   /**
    * check if an expression is a certain number.
    * @param n the number
    * @return true if it is
    */
   boolean isNumberN(double n);
}
