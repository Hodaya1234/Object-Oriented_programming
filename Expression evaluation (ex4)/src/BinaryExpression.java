
import java.util.ArrayList;
import java.util.List;

/**
 * Binary.
 * @author H
 *
 */
public abstract class BinaryExpression extends BaseExpression {

  private Expression e1;
  private Expression e2;

  /**
   * get E1.
   * @return the first expression
   */
  protected Expression getE1() {
    return this.e1;
  }

  /**
   * get E2.
   * @return the second expression
   */
  protected Expression getE2() {
    return this.e2;
  }

  /**
   * set E1.
   * @param first to set the expression
   */
  protected void setE1(Expression first) {
    this.e1 = first;
  }

  /**
   * set E2.
   * @param second to set the expression
   */
  protected void setE2(Expression second) {
    this.e2 = second;
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(Expression e1, Expression e2) {
    this.e1 = e1;
    this.e2 = e2;
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(Expression e1, double e2) {
    this.e1 = e1;
    this.e2 = new Num(e2);
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(Expression e1, String e2) {
    this.e1 = e1;
    this.e2 = new Var(e2);
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(double e1, double e2) {
    this.e1 = new Num(e1);
    this.e2 = new Num(e2);
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(double e1, Expression e2) {
    this.e1 = new Num(e1);
    this.e2 = e2;
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(double e1, String e2) {
    this.e1 = new Num(e1);
    this.e2 = new Var(e2);
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(String e1, String e2) {
    this.e1 = new Var(e1);
    this.e2 = new Var(e2);
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(String e1, Expression e2) {
    this.e1 = new Var(e1);
    this.e2 = e2;
  }

  /**
   * Constructor.
   * @param e1 Expression 1
   * @param e2 Expression 2
   */
  public BinaryExpression(String e1, double e2) {
    this.e1 = new Var(e1);
    this.e2 = new Num(e2);
  }

  @Override
  public List<String> getVariables() {
    List<String> list = new ArrayList<String>();
    list.addAll(e1.getVariables());
    list.addAll(e2.getVariables());
    return this.noRepetitionVariables(list);
  }

  @Override
  public Expression simplify() {
    this.e1 = this.e1.simplify();
    this.e2 = this.e2.simplify();
    if (this.e1.isNumber()) {
      try {
        this.e1 = new Num(this.e1.evaluate());
      } catch (Exception e) {
        System.out.println("evaluate error in simplify expression");
      }
    }
    if (this.e2.isNumber()) {
      try {
        this.e2 = new Num(this.e2.evaluate());
      } catch (Exception e) {
        System.out.println("evaluate error in simplify expression");
      }
    }
    return this;
  }

  /**
   * check if to expression strings are identical.
   * @return true if they are
   */
  public boolean areStringsEqual() {
    return this.e1.toString().equals(this.e2.toString());
  }

}
