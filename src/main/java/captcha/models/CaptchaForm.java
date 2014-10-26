package captcha.models;

import captcha.domain.Captcha;

public class CaptchaForm {

	private String id;
	private String answer;
	private String question;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}

    public boolean isValid(Captcha quiz) {
        return knownQuiz(quiz) && validAnswer(quiz);
    }

    private boolean knownQuiz(Captcha quiz) {
        return quiz != null;
    }

    private boolean validAnswer(Captcha quiz) {
        try {
            return quiz.isCorrect(Integer.parseInt(getAnswer()));
        } catch(NumberFormatException ex) {
            return false;
        }
    }
	
}
