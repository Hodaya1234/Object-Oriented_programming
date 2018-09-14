
import java.util.Map;
/**
 * Negative class.
 * @author H
 *
 */
public class Neg extends UnaryExpression implements Expression {

  /**
   * Constructor.
   * @param e Expression to negate
   */
  public Neg(Expression e) {
    super(e);
  }

  /**
   * Constructor.
   * @param e Expression to negate
   */
  public Neg(double e) {
    super(e);
  }

  /**
   * Constructor.
   * @param e Expression to negate
   */
  public Neg(String e) {
    super(e);
  }

  @Override
  public Expression getE() {
    return super.getE();
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return -1 * this.getE().evaluate(assignment);
  }

  @Override
  public String toString() {
    return "(-" + this.getE().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Neg(this.getE().differentiate(var));
  }

  @Override
  public Expression simplify() {
    super.simplify();
    if (this.getE().isNumberN(0)) {
      return new Num(0);
    }
    if (this.getE().isNumber()) {
      try {
        if (this.getE().evaluate() < 0) {
          return new Num(-1 * this.getE().evaluate());
        }
      } catch (Exception e1) {
        System.out.println("Neg simplify error");;
      }
    }
    return this;
  }

  @Override
  public Expression assign(String var, Expression expression) {
    return new Neg(super.getE().assign(var, expression));
  }
}
