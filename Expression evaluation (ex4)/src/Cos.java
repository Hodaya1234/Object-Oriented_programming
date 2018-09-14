
import java.util.Map;

/**
 *
 * @author H
 *
 */
public class Cos extends UnaryExpression implements Expression {

  /**
   * Constructor.
   * @param e the expression inside the cos
   */
  public Cos(Expression e) {
    super(e);
  }

  /**
   * Constructor.
   * @param e the expression inside the cos
   */
  public Cos(double e) {
    super(e);
  }

  /**
   * Constructor.
   * @param e the expression inside the cos
   */
  public Cos(String e) {
    super(e);
  }

  /**
   * get expression.
   * @return the super class value
   */
  public Expression getE() {
    return super.getE();
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    if (this.getE().isNumberN(90)) {
      return 0;
    }
    return Math.cos(Math.toRadians(this.getE().evaluate(assignment)));
  }

  @Override
  public String toString() {
    return "cos(" + this.getE().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Mult(new Neg(new Sin(this.getE())), this.getE().differentiate(var));
  }

  @Override
  public Expression simplify() {
    return super.simplify();
  }
  @Override
  public Expression assign(String var, Expression expression) {
    return new Cos(super.getE().assign(var, expression));
  }
}
