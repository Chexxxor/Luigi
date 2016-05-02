package calculator;

public class Combination extends Expression {
	public static enum Type{
		DEFINITION("=", "\\="),
		PAR_L("(", "\\("),
		PAR_R(")", "\\)"),
		ADD("+", "\\+"),
		SUBTRACT("-", "\\-"),
		MULTIPLY("*", "\\*"),
		DIVIDE("/", "\\/"),
		POWER("^", "\\^");
		
		private String text;
		private String regex;
		
		private Type(String text, String regex){
			this.text = text;
			this.regex = regex;
		}
		
		public String getReg(){
			return regex;
		}
		public String toString(){
			return text;
		}
		
	};
	Expression left;
	Expression right;
	Type type;

	public Combination(String exp) {
		String[] exps = new String[2];
		exp = exp.trim();
		for(Type t : Type.values()){
			if(exp.substring(1).contains(t.toString())){
				type = t;
				/*if(t == Combination.Type.PAR_R){
					//exps[0] = exp.substring(0, exp.lastIndexOf(')'));
					//exps[1] = exp.substring(exp.lastIndexOf(')') + 1);
				}
				else*/ if(t == Combination.Type.SUBTRACT && exp.charAt(0) == '-')
					exps = exp.split("([^-]-)", 2);
				else
					exps = exp.split(t.getReg(), 2);
				break;
			}
		}
		left = create(exps[0]);
		right = create(exps[1]);
		if(type == Type.DEFINITION && left instanceof Variable){
			((Variable)left).define(right);
			//Variable.variables.add((Variable) left);
		}
	}

	@Override
	public Expression evaluate() {
		if((left.evaluate() instanceof Value) && (right.evaluate() instanceof Value)){
			double n1 = ((Value)left.evaluate()).getValue();
			double n2 = ((Value)right.evaluate()).getValue();
			switch(type){
			case ADD:
				return new Value(n1 + n2);
			case SUBTRACT:
				return new Value(n1 - n2);
			case DIVIDE:
				return new Value(n1 / n2);
			case MULTIPLY:
				return new Value(n1 * n2);
			case POWER:
				return new Value(Math.pow(n1,n2));
			default:
				break;
			}
			return this;
		}
		return new Combination(left.evaluate().toString() + type.toString() + right.evaluate().toString());
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return left.toString() + " " + type.toString() + " " + right.toString();
	}
}
