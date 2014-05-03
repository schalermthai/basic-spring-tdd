package captcha.models;

import org.junit.Assert;
import org.junit.Test;

import captcha.models.TextOperand;

public class TextOperandTest {

	@Test
	public void testTextOperand_value() {
		TextOperand operand = new TextOperand(2);
		Assert.assertEquals(2, operand.getValue());
	}
	
	@Test
	public void testTextOperand_text() {
		Assert.assertEquals("Zero", new TextOperand(0).toText());
		Assert.assertEquals("One", new TextOperand(1).toText());
		Assert.assertEquals("Two", new TextOperand(2).toText());
		Assert.assertEquals("Three", new TextOperand(3).toText());
		Assert.assertEquals("Four", new TextOperand(4).toText());
		Assert.assertEquals("Five", new TextOperand(5).toText());
		Assert.assertEquals("Six", new TextOperand(6).toText());
		Assert.assertEquals("Seven", new TextOperand(7).toText());
		Assert.assertEquals("Eight", new TextOperand(8).toText());
		Assert.assertEquals("Nine", new TextOperand(9).toText());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTextOperand_badValue_maxBound() {
		new TextOperand(10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTextOperand_badValue_minBound() {
		new TextOperand(-1);
	}
}
