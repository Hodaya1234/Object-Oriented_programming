import java.util.Map;

/**
 * Divide class.
 * @author H
 *
 */
public class Div extends BinaryExpression implements Expression {

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(Expression e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(Expression e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(Expression e1, String e2) {
    super(e1, e2);
    }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(double e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(double e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(double e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(String e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(String e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 divided
   * @param e2 divider
   */
  public Div(String e1, double e2) {
    super(e1, e2);
  }

  @Override
  public Expression assign(String var, Expression expression) {
    return new Div(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    if (super.getE2().evaluate(assignment) == 0) {
      throw new Exception("Divide by zero");
    }
    return super.getE1().evaluate(assignment) / super.getE2().evaluate(assignment);
  }

  @Override
  public String toString() {
    return "(" + super.getE1().toString() + " / " + super.getE2().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Div(new Minus(new Mult(super.getE1().differentiate(var), super.getE2()),
        new Mult(super.getE1(), super.getE2().differentiate(var))), new Pow(super.getE2(), new Num(2)));
  }

  @Override
  public Expression simplify() {
    super.simplify();
    if (super.getE2().isNumberN(1)) {
      return super.getE1();
    }
    if (super.getE1().isNumberN(0)) {
      return new Num(0);
    }
    if (super.getE1().toString().equals(super.getE2().toString())) {
      return new Num(1);
    }
    if (super.getE1().isNumber() && super.getE2().isNumber()) {
      try {
      return new Num(this.evaluate());
    } catch (Exception e) {
      System.out.println("Error in Div simplify");
    }
    }
    return this;
  }

}
