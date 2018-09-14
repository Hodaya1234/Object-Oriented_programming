import java.util.Map;

/**
 * Multiple class.
 * @author H
 *
 */
public class Mult extends BinaryExpression implements Expression {

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(Expression e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(Expression e1, double e2) {
    super(e1, e2);    }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(Expression e1, String e2) {
    super(e1, e2);
    }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(double e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(double e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(double e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(String e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(String e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 first multiplier
   * @param e2 second multiplier
   */
  public Mult(String e1, double e2) {
    super(e1, e2);
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return super.getE1().evaluate(assignment) * super.getE2().evaluate(assignment);
  }

  @Override
  public String toString() {
    return "(" + super.getE1().toString() + " * " + super.getE2().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Plus(new Mult(super.getE1().differentiate(var), super.getE2()),
        new Mult(super.getE2().differentiate(var), super.getE1()));
  }

  @Override
  public Expression simplify() {
    super.simplify();
    if ((super.getE1().isNumberN(0)) || (super.getE2().isNumberN(0))) {
      return new Num(0);
    }
    if (super.getE1().isNumberN(1)) {
      return super.getE2();
    }
    if (super.getE2().isNumberN(1)) {
      return super.getE1();
    }
    if (super.getE1().toString().compareTo(super.getE2().toString()) > 0) {
      Expression temp = super.getE1();
      super.setE1(super.getE2());
      super.setE2(temp);
    }
    if (this.areStringsEqual()) {
      return new Pow(super.getE1(), new Num(2));
    }
        return this;
  }

  @Override
  public Expression assign(String var, Expression expression) {
    return new Mult(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
  }
}
