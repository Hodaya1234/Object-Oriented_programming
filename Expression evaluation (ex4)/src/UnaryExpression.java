
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Unary class.
 * @author H
 *
 */
public abstract class UnaryExpression extends BaseExpression {

  private Expression e;

  /**
   * Constructor.
   * @param e the expression to be inside the current one
   */
  public UnaryExpression(Expression e) {
    this.e = e;
  }

  /**
   * Constructor.
   * @param e the expression to be inside the current one
   */
  public UnaryExpression(double e) {
    this.e = new Num(e);
  }

  /**
   * Constructor.
   * @param e the expression to be inside the current one
   */
  public UnaryExpression(String e) {
    this.e = new Var(e);
  }

  /**
   * get the expression inside the current one.
   * @return Expression
   */
  protected Expression getE() {
    return this.e;
  }

  /**
   * set a certain expression.
   * @param ex the Expression
   */
  protected void setE(Expression ex) {
    this.e = ex;
  }

  @Override
  public double evaluate() throws Exception {
    Map<String, Double> assignment = new TreeMap<String, Double>();
    return this.evaluate(assignment);
  }


  @Override
  public List<String> getVariables() {
    List<String> list = new ArrayList<String>();
    list.addAll(this.e.getVariables());
    return this.noRepetitionVariables(list);
  }

  @Override
  public Expression simplify() {
    this.e = this.e.simplify();
    if (this.e.isNumber()) {
      try {
        return new Num(this.evaluate());
      } catch (Exception e1) {
        System.out.println("Unary expression simplify error");
      }
    }
    return this;
  }
}
