package calculator;

import java.util.ArrayList;

public class Variable extends Expression {
	public static ArrayList<Variable> variables = new ArrayList<>();
	String name;
	Expression expression = null;

	public Variable(String name) {
		this.name = name;
		variables.add(this);
	}
	public Variable(String name, Expression e){
		this.name = name;
		expression = e;
		variables.add(this);
	}
	
	public void define(Expression e){
		expression = e;
	}
	
	public String printVar(){
		return name + " = " + toString().substring(1, toString().length()-1) + (toString().equals(evaluate().toString()) ? "" : " = " + evaluate().toString());
	}

	@Override
	public Expression evaluate() {
		if(expression != null)
			return expression.evaluate();
		return this;
	}

	@Override
	public String toString() {
		if(expression != null){
			if(expression instanceof Trigonometric || (expression instanceof Value && ((Value)expression).getValue() >= 0))
				return expression.toString();
			return "(" + expression.toString() + ")";
		}
		return name;
	}
}
