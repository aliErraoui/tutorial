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
import ma.emsi.model.User;
import ma.emsi.model.Voiture;
import ma.emsi.service.ReservationService;
import ma.emsi.service.UserService;
import ma.emsi.service.VoitureService;

@Controller
public class UserController {
	@Autowired
	VoitureService voitureService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/user/listVoiture", method = RequestMethod.GET)
	public ModelAndView listVoiture(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		model.addAttribute("voitures", voitureService.listVoiture());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println(currentPrincipalName);

		modelAndView.setViewName("views/user/listVoiture");

		return modelAndView;
	}

	@RequestMapping(value = "/user/saveReservation", method = RequestMethod.POST)
	public String saveReservation(@ModelAttribute("reservation") Reservation reservation) {

		int a = reservationService.saveOrUpdateReservation();
		reservation.setReservationId(a);
		// System.out.println("a"+ reservation.getReservationId());
		reservationService.updateReservation(reservation);

		// odelAndView.setViewName("views/user/reservation");
		return "redirect:/user/listVoiture";

		// return modelAndView;
	}

	@RequestMapping(value = { "user/reservation/{voitureId}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView newReservation(@PathVariable int voitureId, Model model,
			@ModelAttribute("reservation") Reservation reservation) {
		// System.out.println(voitureId);
		Voiture voiture = voitureService.getVoitureById(voitureId);

		ModelAndView modelAndView = new ModelAndView();
		// model.addAttribute("voiture", new Voiture());
		model.addAttribute("reservation", new Reservation());
		// System.out.println(voitureId);


		reservation.setVoiture(voiture);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		reservation.setUser(user);
		reservation.getVoiture().setRes(1);
		reservationService.saveReservation(reservation);

		modelAndView.setViewName("views/user/reservation");
		return modelAndView;
	}

}
