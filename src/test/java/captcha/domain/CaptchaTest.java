package captcha.domain;

import org.junit.Assert;
import org.junit.Test;

import captcha.domain.Captcha;
import captcha.domain.NumberOperand;
import captcha.domain.Operator;
import captcha.domain.TextOperand;

public class CaptchaTest {

    @Test
    public void printCaptcha() {
        Captcha captcha = new Captcha(new TextOperand(3), Operator.MINUS, new NumberOperand(5));
        Assert.assertEquals("Three - 5 = ?", captcha.getText());
    }

	@Test
	public void captchaWithNumberOperand() {
		Captcha captcha = new Captcha(new NumberOperand(1), Operator.PLUS, new NumberOperand(1));
		Assert.assertEquals(2, captcha.getAnswer());
	}
	
	@Test
	public void captchaWithTextOperand() {
		Captcha captcha = new Captcha(new TextOperand(1), Operator.PLUS, new NumberOperand(1));
		Assert.assertEquals(2, captcha.getAnswer());
	}
	
	@Test
	public void captchaWithMinusOperator() {
		Captcha captcha = new Captcha(new TextOperand(1), Operator.MINUS, new NumberOperand(1));
		Assert.assertEquals(0, captcha.getAnswer());
	}

	@Test
	public void captchaWithMultiplyOperator() {
		Captcha captcha = new Captcha(new TextOperand(1), Operator.MULTIPLY, new NumberOperand(1));
		Assert.assertEquals(1, captcha.getAnswer());
	}

    @Test
    public void isCorrect_validResult() {
        Captcha captcha = new Captcha(new TextOperand(1), Operator.PLUS, new NumberOperand(1));
        Assert.assertTrue(captcha.isCorrect(2));
    }

    @Test
    public void isCorrect_invalidResult() {
        Captcha captcha = new Captcha(new TextOperand(1), Operator.PLUS, new NumberOperand(1));
        Assert.assertFalse(captcha.isCorrect(0));
    }
}
