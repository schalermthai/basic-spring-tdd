package captcha.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import captcha.models.Captcha;
import captcha.validators.CaptchaValidator;

@Controller
@Scope("prototype")
public class CatchaController {

	@Autowired
	Captcha captcha;
	
	@Autowired
	CaptchaValidator captchaValidator;

	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public String show(Model model) {
		setCaptchaModel(model, new CaptchaForm());
		return "captchaForm";
	}

	@RequestMapping(value = "/captcha", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("captchaForm") CaptchaForm captchaForm, Errors errors, Model model) {

		if (errors.hasErrors()) {
			setCaptchaModel(model, captchaForm);
			return "captchaForm";
		}

		return "captchaCorrect";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(captchaValidator);
	}
	
	private void setCaptchaModel(Model model, CaptchaForm captchaForm) {
		captchaForm.setId(captcha.getId());
		model.addAttribute("captchaForm", captchaForm);
		model.addAttribute("captchaQuestion", captcha.getText());
	}
	
}
