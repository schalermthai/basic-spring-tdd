package captcha.domain;

public enum Operator {
	PLUS("+"), MINUS("-"), MULTIPLY("*");
	
	private String sign;

	Operator(String sign) {
		this.sign = sign;
	}
	
	public String getSign() {
		return sign;
	}

	public int calculate(int leftOperand, int rightOperand) {
		
		switch (this) {
			case PLUS : return leftOperand + rightOperand;
			case MINUS : return leftOperand - rightOperand;
			case MULTIPLY : return leftOperand * rightOperand;
		}

		throw new IllegalArgumentException();
	}
}
