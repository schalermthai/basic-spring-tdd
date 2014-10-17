package captcha.domain;

public enum Operator {
	PLUS("+"), MINUS("-"), MULTIPLY("*");
	
	private String symbol;

	Operator(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
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
