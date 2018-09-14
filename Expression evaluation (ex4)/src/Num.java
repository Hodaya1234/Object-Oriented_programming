

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author H
 *
 */
public class Num implements Expression {

  private double num;

  /**
   * create number.
   * @param num a double
   */
  public Num(double num) {
    this.num = num;
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return this.num;
  }

  @Override
  public double evaluate() throws Exception {
    return this.num;
  }

  @Override
  public List<String> getVariables() {
    return new ArrayList<String>();
  }

  @Override
  public Expression assign(String var, Expression expression) {
    return this;
  }

  @Override
  public String toString() {
    return Double.toString(Math.round(this.num * 1000) / 1000);
  }

  @Override
  public Expression differentiate(String var) {
    return new Num(0);
  }

  @Override
  public Expression simplify() {
    return this;
  }

  @Override
  public boolean isNumber() {
    return true;
  }

  @Override
  public boolean isNumberN(double n) {
    return this.num == n;
  }
}
