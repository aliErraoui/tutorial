package ma.emsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.emsi.model.Reservation;
import ma.emsi.model.Responsable;
import ma.emsi.model.User;
import ma.emsi.model.Voiture;
import ma.emsi.service.ReservationService;
import ma.emsi.service.ResponsableService;
import ma.emsi.service.UserService;
import ma.emsi.service.VoitureService;

@Controller
public class AdminController {
	@Autowired
	ResponsableService responsableService;
	@Autowired
	VoitureService voitureService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/admin/home")
	public String adminHome() {
		return "views/admin/home";
	}

	@RequestMapping(value = "/admin/deleteResponsable/{id}")
	public String deleteResponsable(@PathVariable int id, Model model,
			@ModelAttribute("responsable") Responsable responsable) {
		responsable = responsableService.getResponsableById(id);
		responsableService.DeleteResponsable(responsable);

		return "redirect:/admin/listResponsable";
	}

	@RequestMapping(value = "/admin/deleteReservation/{reservationId}")
	public String deleteReservation(@PathVariable int reservationId, Model model,
			@ModelAttribute("reservation") Reservation reservation) {
		reservation = reservationService.getReservationById(reservationId);
		reservationService.DeleteReservation(reservation);

		return "redirect:/admin/listReservation";
	}

	@RequestMapping(value = "/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable int id, Model model, @ModelAttribute("user") User user) {
		ModelAndView modelAndView = new ModelAndView();
		reservationService.DeleteReservUser(id);

		user = userService.getUserById(id);
		userService.DeleteUser(user);

		modelAndView.setViewName("user");

		return "redirect:/admin/listUser";
	}

	@RequestMapping(value = "/admin/deleteVoiture/{voitureId}")
	public String deleteVoiture(@PathVariable int voitureId, Model model, @ModelAttribute("voiture") Voiture voiture) {
		ModelAndView modelAndView = new ModelAndView();
		reservationService.DeleteReservation(voitureId);
		voiture = voitureService.getVoitureById(voitureId);
		voitureService.DeleteVoiture(voiture);

		modelAndView.setViewName("voiture");

		// return modelAndView;
		return "redirect:/admin/listVoiture";
	}

	@RequestMapping(value = "/admin/modifierVoiture", method = RequestMethod.POST)
	public String modifierVoiture(@ModelAttribute("voiture") Voiture voiture) {

		ModelAndView modelAndView = new ModelAndView();

		voitureService.saveVoiture(voiture);
		modelAndView.setViewName("listVoiture");

		return "redirect:/admin/listVoiture";
	}

	@RequestMapping(value = { "/admin/afficherPourModifierVoiture/{voitureId}" }, method = RequestMethod.GET)
	public ModelAndView newModification(@PathVariable int voitureId, Model model,
			@ModelAttribute("voiture") Voiture voiture) {
		// System.out.println(voitureId);

		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("voiture", voitureService.getVoitureById(voitureId));

		modelAndView.setViewName("views/admin/afficherPourModifierVoiture");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/listReservation", method = RequestMethod.GET)
	public ModelAndView listReservationn(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		model.addAttribute("reservations", reservationService.listReservation());
		modelAndView.setViewName("views/admin/listReservation");

		return modelAndView;
	}

	@RequestMapping(value = "/admin/listUser", method = RequestMethod.GET)
	public ModelAndView listUser(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		model.addAttribute("users", userService.listUser());

		modelAndView.setViewName("views/admin/listUser");

		return modelAndView;
	}

	@RequestMapping(value = "/admin/listVoiture", method = RequestMethod.GET)
	public ModelAndView listVoituree(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		model.addAttribute("voitures", voitureService.listVoitureAdmin());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println(currentPrincipalName);

		modelAndView.setViewName("views/admin/listVoiture");

		return modelAndView;
	}

	@RequestMapping(value = { "/admin/voiture" }, method = RequestMethod.GET)
	public ModelAndView newVoiture(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("voiture", new Voiture());

		modelAndView.setViewName("views/admin/voiture");

		return modelAndView;
	}

	@RequestMapping(value = "/admin/saveVoiture", method = RequestMethod.POST)
	public String saveVoiture(@ModelAttribute("voiture") Voiture voiture) {

		ModelAndView modelAndView = new ModelAndView();

		voitureService.saveVoiture(voiture);
		modelAndView.setViewName("voiture");

		// return modelAndView;
		return "redirect:/admin/listVoiture";

	}

	@RequestMapping(value = { "/admin/newResponsable" }, method = RequestMethod.GET)
	public ModelAndView newResponsable(Model model) {

		ModelAndView modelAndView = new ModelAndView();
		Responsable responsable = new Responsable();
		model.addAttribute("responsable", responsable);
		modelAndView.setViewName("views/admin/responsable");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/saveResponsable", method = RequestMethod.POST)
	public String saveResponsable(@ModelAttribute("responsable") Responsable responsable) {

		ModelAndView modelAndView = new ModelAndView();

		responsableService.saveResponsable(responsable);

		modelAndView.setViewName("responsable");
		return "redirect:/admin/listResponsable";
	}

	@RequestMapping(value = { "/admin/afficherPourModifierResponsable/{id}" }, method = RequestMethod.GET)
	public ModelAndView newModificationResponsable(@PathVariable int id, Model model,
			@ModelAttribute("responsable") Responsable responsable) {

		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("responsable", responsableService.getResponsableById(id));

		modelAndView.setViewName("views/admin/afficherPourModifierResp");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/modifierResponsable", method = RequestMethod.POST)
	public String modifierResponsable(@ModelAttribute("responsable") Responsable responsable) {

		ModelAndView modelAndView = new ModelAndView();

		responsableService.saveResponsable(responsable);
		modelAndView.setViewName("responsable");

		return "redirect:/admin/listResponsable";
	}

	@RequestMapping(value = "/admin/listResponsable", method = RequestMethod.GET)
	public ModelAndView listResponsable(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		model.addAttribute("responsables", responsableService.listResponsable());

		modelAndView.setViewName("views/admin/listResponsable");

		return modelAndView;
	}
}
