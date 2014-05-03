package captcha.models;

public class NumberOperand implements Operand {

	private final int value;

	public NumberOperand(int value) {
		if (value < 0 || value > 9) {
			throw new IllegalArgumentException();
		}
		
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toText() {
		return value + "";
	}
}
