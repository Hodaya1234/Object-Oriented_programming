
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author H
 *
 */
public class Var implements Expression {

  private String var;

  /**
   * create variable.
   * @param var a string
   */
  public Var(String var) {
    this.var = var;
  }

  @Override
  public String toString() {
    return this.var;
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    if (this.var.equals("e")) {
      return 2.718;
    }
    if (this.var.equals("pi")) {
      return 3.141;
    }
    if (assignment.containsKey(this.var)) {
      return assignment.get(this.var);
    }
    throw new Exception("Variable not assigned");
  }

  @Override
  public double evaluate() throws Exception {
    throw new Exception("Can't evaluate variable");
  }

  @Override
  public List<String> getVariables() {
    List<String> list = new ArrayList<String>();
    list.add(this.var);
    return list;
  }

  @Override
  public Expression assign(String v, Expression e) {
    if (v.equals(this.var)) {
      return e;
    }
    return this;
  }

  @Override
  public Expression differentiate(String v) {
    if (this.var.equals(v)) {
      return new Num(1);
    }
    return new Num(0);
  }

  @Override
  public Expression simplify() {
    return this;
  }

  @Override
  public boolean isNumber() {
    return false;
  }

  @Override
  public boolean isNumberN(double n) {
    return false;
  }

}
