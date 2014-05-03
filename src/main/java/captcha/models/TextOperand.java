package captcha.models;

public class TextOperand implements Operand {
	
	private TextValues txtValue;

	public TextOperand(int value){
		this.txtValue = TextValues.valueOf(value);
	}

	public int getValue() {
		return txtValue.value;
	}

	public String toText() {
		return txtValue.text;
	}
	
	private enum TextValues {
		
		ZERO(0, "Zero"), ONE(1, "One"),
		TWO(2, "Two"), THREE(3, "Three"), 
		FOUR(4, "Four"), FIVE(5, "Five"),
		SIX(6, "Six"), SEVEN(7, "Seven"),
		EIGHT(8, "Eight"), NINE(9, "Nine");
		
		private int value;
		private String text;

		TextValues(int value, String text) {
			this.value = value;
			this.text = text;
		}
		
		public static TextValues valueOf(int i) {
			for (TextValues v : TextValues.values()) {
				if (v.value == i) return v;
			}
			
			throw new IllegalArgumentException();
		}
	}

}
