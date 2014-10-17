package captcha.domain;

import java.util.Random;
import java.util.WeakHashMap;

public class CaptchaFactory {

    private final CaptchaGenerator generator;
	private WeakHashMap<String, Captcha> storage = new WeakHashMap<String, Captcha>();

    public CaptchaFactory(CaptchaGenerator generator) {
        this.generator = generator;
    }

	public Captcha random() {
		Captcha captcha = generateCaptcha();
		storage.put(captcha.getId(), captcha);
		return captcha;
	}

	protected Captcha generateCaptcha() {
		int left = generator.randomLeftOperand();
		int right = generator.randomRightOperand();
		Operator op = generator.randomOperator();
		
		if (generator.isStartWithText()) {
			return new Captcha(new TextOperand(left), op, new NumberOperand(right));
		} else {
			return new Captcha(new NumberOperand(left), op, new TextOperand(right));
		}
	}

	public Captcha find(String id) {
		return storage.get(id);
	}
}
