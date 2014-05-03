package captcha.models;

import java.util.UUID;

public class Captcha {

	private String id;
	
	private Operand leftOperand;
	private Operator operator;
	private Operand rightOperand;

	public Captcha(Operand leftOperand, Operator operator, Operand rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
		
		id = UUID.randomUUID().toString();
	}

	public int getAnswer() {
		return operator.calculate(leftOperand.getValue(), rightOperand.getValue());
	}
	
	public boolean isCorrect(int answer) {
		return getAnswer() == answer;
	}

	public String getText() {
		return String.format("%s %s %s = ?", leftOperand.toText(), operator.getSign(), rightOperand.toText());
	}

	public String getId() {
		return id;
	}
}
