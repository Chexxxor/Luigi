package calculator;

public abstract class Expression {
	
	public static void main(String[] args){
		//Expression exp = create("Variable = 11 - (2 - 3) + 1");
		create("x=3y");
		Variable exp2 = new Variable("Height", create("sin(2*x + 1) + 3"));
		System.out.println(exp2.printVar());
		System.out.println(exp2.evaluate());
		//System.out.println(exp.toString() + " = " + exp.evaluate().toString());
	}

	protected Expression(){}

	public static Expression create(String exp){
		exp = exp.trim();
		try{
			return new Value(Integer.parseInt(exp));
		}
		catch(NumberFormatException e){
			if(exp.contains("(") && exp.contains(")") && exp.lastIndexOf(")") > exp.indexOf("(")){
				exp = substitute(exp);
			}
			for(Combination.Type t : Combination.Type.values()){
				if(exp.contains(t.toString())) //Add Parenthesis substitution with variable (definition)
					if(t != Combination.Type.SUBTRACT || exp.lastIndexOf(t.toString()) != 0)
						return new Combination(exp);
			}
			for(Variable v : Variable.variables){
				if(v.name.equals(exp))
					return v;
			}
			return new Variable(exp);
		}
	}
	
	public static String substitute(String exp){
		int first = exp.indexOf("(");
		int last = exp.lastIndexOf(")");
		String varName = "Sub" + Variable.variables.size();
		if(first > 2){
			for(Trigonometric.Type t : Trigonometric.Type.values()){
				if(first > 2 && exp.substring(first-3, first+1).equals(t.toString() + "(")){
					boolean inverse = false;
					char pre = 'x';
					if(first > 3)
						pre = exp.charAt(first - 4);
					if(pre == 'a' || pre == 'A'){
						inverse = true;
					}
					new Variable(varName, new Trigonometric(t, create(exp.substring(first+1, last)), inverse));
					return exp.replace(exp.substring(first - (inverse ? 4 : 3), last+1), varName);
					//Move definition-adding to variable and make variable with above line
					//System.out.println(exp.substring(first-3, first + 1));
				}
			}
		}
		new Variable(varName, create(exp.substring(first+1, last)));
		//new Combination(varName + " = " + exp.substring(first+1, last));
		return exp.replace(exp.substring(first, last+1), varName);
	}
	
	public abstract Expression evaluate();
	
	public abstract String toString();
}
