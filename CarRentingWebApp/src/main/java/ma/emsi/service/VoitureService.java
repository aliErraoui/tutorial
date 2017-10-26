package ma.emsi.service;

import java.util.List;

import ma.emsi.model.Voiture;

public interface VoitureService {
	
	public void saveVoiture(Voiture voiture) ;

	public List<Voiture> listVoiture() ;
	
	public List<Voiture> listVoitureAdmin() ;
	
	public Voiture getVoitureById(int voitureId) ;
	
	public void DeleteVoiture(Voiture voiture) ;

}
