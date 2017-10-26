package ma.emsi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.dao.ReservationRepository;
import ma.emsi.model.Reservation;
import ma.emsi.model.User;
import ma.emsi.model.Voiture;



@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public void saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub
				reservationRepository.save(reservation) ;
				
	}

	@Override
	public int saveOrUpdateReservation() {
		// TODO Auto-generated method stub
		int	max=0  ;
		List<Reservation> reservations=reservationRepository.findAll() ;
		for(int i=0;i<reservations.size();i++){
			Reservation reservationn = reservations.get(i) ;
		      // System.out.println(reservationn.getReservationId());
		       
		        if(reservationn.getReservationId()>max)
		       	max=reservationn.getReservationId() ;
		        
		       
		       
		}
		
		System.out.println("max"+max);
		return max;
	}

	@Override
	public void updateReservation(Reservation reservation) {
		
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			List<Reservation> reservations = reservationRepository.findAll() ;
			for(int i=0;i<reservations.size();i++){
				Reservation reservationn = reservations.get(i) ;
			//System.out.println(reservation.getReservationId());
			 if(reservation.getReservationId()==reservationn.getReservationId()){
				 String date=reservation.getDate() ;
				 String dateDebut=reservation.getDateDebut() ;
				  String dateFin=reservation.getDateFin() ;
				  boolean promotion=false ;
				  reservationn.setDate(date);
				  reservationn.setDateDebut(dateDebut);
				  reservationn.setDateFin(dateFin);
				  reservationn.setPromotion(promotion);
				  
				 
				  reservationRepository.saveAndFlush(reservationn) ;
				//System.out.println(reservationn.getReservationId());
				//  reservationRepository.delete(reservationn.getReservationId());
				 
			}
		
		
		
	}
	
	

}

	@Override
	public List<Reservation> listReservation() {
		// TODO Auto-generated method stub
		return 	reservationRepository.findAll() ;
	}

	@Override
	public Reservation getReservationById(int reservationId) {
		// TODO Auto-generated method stub
		List<Reservation> reservations= reservationRepository.findAll() ;
		for(int i=0;i<reservations.size();i++){
			 Reservation reservation = reservations.get(i) ;
		  if(reservation.getReservationId()==reservationId)
			  return reservation ;
		}
		return null;
	}

	@Override
	public void DeleteReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		Voiture voiture=new Voiture() ;
		reservation.setVoiture(voiture);
		User user=new User() ;
		reservation.setUser(user);
		
		reservationRepository.delete(reservation);
		
		
	}

	@Override
	public void DeleteReservation(int voitureId) {
		// TODO Auto-generated method stub
		
		List<Reservation> reservations= reservationRepository.findAll() ;
		for(int i=0;i<reservations.size();i++){
			 Reservation reservation = reservations.get(i) ;
			 User user= new User() ;
		  Voiture voiture=new Voiture() ;
		  Voiture v=new Voiture() ;
		  voiture=reservation.getVoiture() ;
		 // System.out.println(voiture.getVoitureId());
		  if(voiture.getVoitureId()==voitureId){
			  reservation.setUser(user);
			  reservation.setVoiture(v);
			  reservationRepository.delete(reservation);
		  }
		}
		
		
	}

	
	@Override
	public void DeleteReservUser(int id) {
		// TODO Auto-generated method stub
		
		List<Reservation> reservations= reservationRepository.findAll() ;
		for(int i=0;i<reservations.size();i++){
			Voiture voiture=new Voiture() ;
			 Reservation reservation = reservations.get(i) ;
		     User u=new User() ;
             System.out.println(reservation.getUser().getId());
		     if(reservation.getUser().getId()==id)
		     {
			  reservation.setVoiture(voiture);
			  reservation.setUser(u);
			    reservationRepository.delete(reservation);
		     }
			  
		}
		
	}

	@Override
	public List<Reservation> listReservationRespo() {
		// TODO Auto-generated method stub
		
		List<Reservation> reservations= reservationRepository.findAll() ;
		List<Reservation> reservationss=new ArrayList<Reservation>() ;
		for(int i=0;i<reservations.size();i++){
			 Reservation reservation = reservations.get(i) ;
		  if(reservation.getResponsable().getId()==0)
			  reservationss.add(reservation) ;
		}
		return reservationss;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
