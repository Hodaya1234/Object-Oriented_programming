
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Minus class.
 * @author H
 *
 */
public class Minus extends BinaryExpression implements Expression {

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(Expression e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(Expression e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(Expression e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(double e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(double e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(double e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(String e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(String e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first expression
   * @param e2 second expression
   */
  public Minus(String e1, double e2) {
    super(e1, e2);
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return super.getE1().evaluate(assignment) - super.getE2().evaluate(assignment);
  }

  @Override
  public List<String> getVariables() {
    List<String> list = new ArrayList<String>();
    list.addAll(super.getE1().getVariables());
    list.addAll(super.getE2().getVariables());
    return list;
  }

  @Override
  public String toString() {
    return "(" + super.getE1().toString() + " - " + super.getE2().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Minus(super.getE1().differentiate(var), super.getE2().differentiate(var));
  }

  @Override
  public Expression simplify() {
    super.simplify();
    if (super.getE2().isNumberN(0)) {
      return super.getE1();
    }
    if (super.getE1().isNumberN(0)) {
      return new Neg(super.getE2());
    }
    if (super.getE1().toString().equals(super.getE2().toString())) {
      return new Num(0);
    }

    return this;
  }

@Override
  public Expression assign(String var, Expression expression) {
    return new Minus(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
  }
}