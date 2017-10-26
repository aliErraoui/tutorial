package ma.emsi.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import ma.emsi.dao.VoitureRepository;
import ma.emsi.model.Voiture;

@Service("voitureService")
public class VoitureServiceImpl implements VoitureService{
	
	@Autowired
	private VoitureRepository voitureRepository;
	
	
	@Override
	public void saveVoiture(Voiture voiture){
		
		voitureRepository.save(voiture) ;
		
	}


	@Override
	public List<Voiture> listVoiture() {
		// TODO Auto-generated method stub
		 List<Voiture> voitures=voitureRepository.findAll() ;
		List<Voiture> cars=new ArrayList<Voiture>() ;
		 for(int i=0;i<voitures.size();i++){
			  Voiture voiture = voitures.get(i) ;
		  if(voiture.getRes()==0)
			  cars.add(voiture);
			   
		}
		return 	cars ;
		
		
	}
	
	
	@Override
	public Voiture getVoitureById(int voitureId) {
		// TODO Auto-generated method stub
		List<Voiture> voitures = voitureRepository.findAll() ;
		for(int i=0;i<voitures.size();i++){
			  Voiture voiture = voitures.get(i) ;
		  if(voiture.getVoitureId()==voitureId)
			  return voiture ;
		}
		return null ;
	}


	@Override
	public void DeleteVoiture(Voiture voiture) {
		// TODO Auto-generated method stub
		
		voitureRepository.delete(voiture);
	}


	@Override
	public List<Voiture> listVoitureAdmin() {
		// TODO Auto-generated method stub
		return voitureRepository.findAll();
	}
	
	
	
	
	
	
    
	
	
	
	
	

}
