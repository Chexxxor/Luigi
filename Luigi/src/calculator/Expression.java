package calculator;

public abstract class Expression {
	
	public static void main(String[] args){
		Expression exp = create("Variable = 11 - (2 - 3) + 1");
		create("x=3");
		Variable exp2 = new Variable("Height", create("2*x + 1"));
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
				int first = exp.indexOf("(");
				int last = exp.lastIndexOf(")");
				String varName = "Sub" + Variable.variables.size();
				new Combination(varName + " = " + exp.substring(first+1, last));
				exp = exp.replace(exp.substring(first, last+1), varName);
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
	
	public abstract Expression evaluate();
	
	public abstract String toString();
}
