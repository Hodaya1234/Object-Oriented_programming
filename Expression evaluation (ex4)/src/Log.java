import java.util.Map;

/**
 * Logarithm.
 * @author H
 *
 */
public class Log extends BinaryExpression implements Expression {

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(Expression e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(Expression e1, double e2) {
    super(e1, e2);    }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(Expression e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(double e1, double e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(double e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(double e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(String e1, String e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(String e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * Constructor.
   * @param e1 base of the log
   * @param e2 result of the log
   */
  public Log(String e1, double e2) {
    super(e1, e2);
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    double base = super.getE1().evaluate(assignment);
    double x = super.getE2().evaluate(assignment);
    if ((base <= 0) || (base == 1) || (x < 0))  {
      throw new Exception("Illegal logarithm");
    }
    return Math.log(x) / Math.log(base);
  }

  @Override
  public String toString() {
    return "(log(" + super.getE1().toString() + ", " + super.getE2().toString() + "))";
  }

  @Override
  public Expression differentiate(String var) {
    if (!super.getE1().getVariables().contains(var)) {
      return new Log(super.getE2().differentiate(var), new Mult(super.getE2(), new Log(new Var("e"), super.getE1())));
    }
    return new Log(new Log(new Var("e"), super.getE2()), new Log(new Var("e"), super.getE1())).differentiate(var);
  }

  @Override
  public Expression simplify() {
    super.simplify();
    if (super.getE2().isNumberN(1)) {
      return new Num(0);
    }
    if (this.areStringsEqual()) {
      return new Num(1);
    }
    return this;
  }

  @Override
  public Expression assign(String var, Expression expression) {
    return new Log(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
  }
}