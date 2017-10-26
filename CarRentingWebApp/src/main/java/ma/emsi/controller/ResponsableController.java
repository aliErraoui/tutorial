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
import ma.emsi.service.ReservationService;
import ma.emsi.service.ResponsableService;
import ma.emsi.service.VoitureService;
@Controller
public class ResponsableController {
	@Autowired
	VoitureService voitureService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	ResponsableService responsableService;

	@RequestMapping(value = "/responsable/listReservation", method = RequestMethod.GET)
	public ModelAndView listReservation(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		model.addAttribute("reservations", reservationService.listReservationRespo());
		model.addAttribute("voitures", voitureService.listVoiture());
		modelAndView.setViewName("views/responsable/listReservation");

		return modelAndView;
	}

	@RequestMapping(value = "responsable/ajouterReservationResponsable/{reservationId}")
	public String ajoutReservationResponsable(@PathVariable int reservationId, Model model,
			@ModelAttribute("reservation") Reservation reservation) {
		reservation = reservationService.getReservationById(reservationId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Responsable responsable = responsableService.findUserByEmail(auth.getName());
		reservation.setResponsable(responsable);

		int a = 1;
		reservation.setResp(a);
		reservationService.saveReservation(reservation);

		return "redirect:/responsable/listReservation";
		// return modelAndView;
	}
}
