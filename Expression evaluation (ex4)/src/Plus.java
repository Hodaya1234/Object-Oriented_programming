
import java.util.Map;

/**
 * Plus class.
 * @author H
 *
 */
public class Plus extends BinaryExpression implements Expression {

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(Expression e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(Expression e1, double e2) {
    super(e1, e2);    }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(Expression e1, String e2) {
    super(e1, e2);
    }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(double e1, double e2) {
    super(e1, e2);
  }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(double e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(double e1, String e2) {
    super(e1, e2);
  }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(String e1, String e2) {
    super(e1, e2);
  }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(String e1, Expression e2) {
    super(e1, e2);
  }

  /**
   * constructor.
   * @param e1 first Expression
   * @param e2 second Expression
   */
  public Plus(String e1, double e2) {
    super(e1, e2);
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return (super.getE1().evaluate(assignment) + super.getE2().evaluate(assignment));
  }

  @Override
  public String toString() {
    return "(" + super.getE1().toString() + " + " + super.getE2().toString() + ")";
  }

  @Override
  public Expression differentiate(String var) {
    return new Plus(this.getE1().differentiate(var), this.getE2().differentiate(var));
  }

  @Override
  public Expression simplify() {
    super.simplify();
    if (super.getE1().isNumberN(0)) {
      return super.getE2();
    }
    if (super.getE2().isNumberN(0)) {
      return super.getE1();
    }
    if (super.getE1().toString().compareTo(super.getE2().toString()) > 0) {
      Expression temp = super.getE1();
      super.setE1(super.getE2());
      super.setE2(temp);
    }
    if (this.areStringsEqual()) {
      return new Mult(new Num(2), super.getE1());
    }
    return this;
  }
  @Override
  public Expression assign(String var, Expression expression) {
    return new Plus(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
  }
}