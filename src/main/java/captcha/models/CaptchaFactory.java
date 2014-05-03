package captcha.models;

import java.util.Random;
import java.util.WeakHashMap;

public class CaptchaFactory {
	
	Random randomizer = new Random(System.nanoTime());
	private WeakHashMap<String, Captcha> storage = new WeakHashMap<String, Captcha>();

	public Captcha random() {
		Captcha c = generateCaptcha();
		storage.put(c.getId(), c);
		return c;
	}

	private Captcha generateCaptcha() {
		int left = randomLeftOperand();
		int right = randomRightOperand();
		Operator op = randomOperator();
		
		if (isStartWithText()) {
			return new Captcha(new TextOperand(left), op, new NumberOperand(right));
		} else {
			return new Captcha(new NumberOperand(left), op, new TextOperand(right));
		}
	}

	protected int randomRightOperand() {
		return randomizer.nextInt(10);
	}

	protected int randomLeftOperand() {
		return randomRightOperand();
	}

	protected Operator randomOperator() {
		Operator[] ops = Operator.values();
		return ops[randomizer.nextInt(ops.length)];
	}
	
	protected boolean isStartWithText() {
		return randomizer.nextBoolean();
	}
	
	public WeakHashMap<String, Captcha> getStorage() {
		return storage;
	}


}
