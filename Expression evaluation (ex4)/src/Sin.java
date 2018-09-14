
import java.util.Map;
/**
 * Sine class.
 * @author H
 *
 */
public class Sin extends UnaryExpression implements Expression {

  /**
   * Constructor.
   * @param e expression
   */
  public Sin(Expression e) {
    super(e);
  }

  /**
   * Constructor.
   * @param e expression
   */
  public Sin(double e) {
    super(e);
  }

  /**
   * Constructor.
   * @param e expression
   */
  public Sin(String e) {
    super(e);
  }

  /**
   * get the expression of the super class.
   * @return the Expression
   */
  public Expression getE() {
    return super.getE();
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return Math.sin(Math.toRadians(this.getE().evaluate(assignment)));
  }

  @Override
  public String toString() {
    return "sin(" + this.getE().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Mult(new Cos(this.getE()), this.getE().differentiate(var));
  }

  @Override
  public Expression simplify() {
    return super.simplify();
  }
  @Override
  public Expression assign(String var, Expression expression) {
    return new Sin(super.getE().assign(var, expression));
  }
}
