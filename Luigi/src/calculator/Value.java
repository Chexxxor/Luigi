package calculator;

public class Value extends Expression {
	private double value;

	public Value(double value) {
		this.value = value;
	}
	
	public double getValue(){
		return value;
	}

	@Override
	public Expression evaluate() {
		return this;
	}

	@Override
	public String toString() {
		if(value == Math.rint(value))
			return "" + (int)Math.rint(value);
		return "" + value;
	}
}
