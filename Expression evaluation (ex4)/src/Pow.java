import java.util.Map;

/**
 * Power class.
 * @author H
 *
 */
public class Pow extends BinaryExpression implements Expression {

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(Expression e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(Expression e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(Expression e1, String e2) {
    super(e1, e2);
    }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(double e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(double e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(double e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(String e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(String e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base
   * @param e2 to the power of
   */
  public Pow(String e1, double e2) {
    super(e1, e2);
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return Math.pow(super.getE1().evaluate(assignment), super.getE2().evaluate(assignment));
  }

  @Override
  public String toString() {
    return "(" + super.getE1().toString() + "^" + super.getE2().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Mult(new Pow(super.getE1(), super.getE2()), new Plus(new Mult(super.getE1().differentiate(var),
        new Div(super.getE2(), super.getE1())),
        new Mult(super.getE2().differentiate(var), new Log("e", super.getE1()))));
  }

  @Override
  public Expression simplify() {
    super.simplify();
    if (super.getE1().isNumberN(0)) {
      return new Num(0);
    }
    if (super.getE1().isNumberN(1)) {
      return new Num(1);
    }
    if (super.getE2().isNumberN(0)) {
      return new Num(1);
    }
    if (super.getE2().isNumberN(1)) {
      return super.getE1();
    }
    return this;
  }
  @Override
  public Expression assign(String var, Expression expression) {
    return new Pow(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
  }
}