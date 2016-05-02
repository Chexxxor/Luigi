package calculator;

public class Trigonometric extends Expression {
	public static enum Type{
		SIN("sin"), COS("cos"), TAN("tan")/*, ASIN("asin"), ACOS("acos"), ATAN("atan")*/;
		
		String text;
		
		private Type(String text){
			this.text = text;
		}
		@Override
		public String toString(){
			return text;
		}
	};

	Type type;
	boolean inverse;
	Expression core;
	
	public Trigonometric(Type type, Expression e, boolean inverse){
		this.type = type;
		core = e;
		this.inverse = inverse;
	}
	
	public Expression getCore(){
		return core;
	}
	
	@Override
	public Expression evaluate() {
		if(core.evaluate() instanceof Value){
			double val = ((Value)core.evaluate()).getValue();
			switch(type){
			case SIN:
				val = inverse ? Math.asin(val) : Math.sin(val);
				break;
			case COS:
				val = inverse ? Math.acos(val) : Math.cos(val);
				break;
			case TAN:
				val = inverse ? Math.atan(val) : Math.tan(val);
				break;
			}
			return new Value(val);
		}
		return new Trigonometric(type, core.evaluate(), inverse);
	}

	@Override
	public String toString() {
		return type.toString() + "(" + core.toString() + ")";
	}

}
