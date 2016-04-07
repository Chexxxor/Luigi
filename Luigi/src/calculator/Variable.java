package calculator;

import java.util.ArrayList;

public class Variable extends Expression {
	public static ArrayList<Variable> variables = new ArrayList<>();
	String name;
	Expression expression = null;

	public Variable(String name) {
		this.name = name;
	}
	public Variable(String name, Expression e){
		this.name = name;
		expression = e;
	}
	
	public void define(Expression e){
		expression = e;
	}
	
	public String printVar(){
		return name + " = " + toString() + (toString().equals(evaluate().toString()) ? "" : " = " + evaluate().toString());
	}

	@Override
	public Expression evaluate() {
		if(expression != null)
			return expression.evaluate();
		return this;
	}

	@Override
	public String toString() {
		if(expression != null)
			return "(" + expression.toString() + ")";
		return name;
	}
}
