package ma.emsi.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.emsi.model.User;
import ma.emsi.model.Voiture;
import ma.emsi.service.ClientService;
import ma.emsi.service.ReservationService;
import ma.emsi.service.ResponsableService;
import ma.emsi.service.UserService;
import ma.emsi.service.VoitureService;

@Controller
public class IndexController {

	@Autowired
	VoitureService voitureService;
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	UserService userService;
	@Autowired
	ResponsableService responsableService;

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView home(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		List<Voiture> list = voitureService.listVoiture();
		if (list.size() > 5)
			model.addAttribute("voitures", list.subList(0, 6));

		modelAndView.setViewName("views/home");

		return modelAndView;
	}

	@RequestMapping("/login")
	public String login() {
		return "views/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("views/register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("views/register");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("views/register");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/access-denied")
	public String accessDenied() {
		return "views/access-denied";
	}

	@RequestMapping("/affiliation")
	public String affiliation() {
		return "views/affiliation";
	}

	@RequestMapping("/apropos")
	public String apropos() {
		return "views/apropos";
	}

	@RequestMapping("/charte")
	public String charte() {
		return "views/charte";
	}

	@RequestMapping("/compte")
	public String compte() {
		return "views/compte";
	}

	@RequestMapping("/condition")
	public String condition() {
		return "views/condition";
	}

	@RequestMapping("/contact")
	public String contact() {
		return "views/contact";
	}

	@RequestMapping("/faq")
	public String faq() {
		return "views/faq";
	}

	@RequestMapping("/villes")
	public String villes() {
		return "views/villes";
	}

	@RequestMapping("/aeroports")
	public String aeroports() {
		return "views/aeroports";
	}

}