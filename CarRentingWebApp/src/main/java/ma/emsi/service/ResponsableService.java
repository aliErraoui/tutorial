package ma.emsi.service;

import java.util.List;

import ma.emsi.model.Responsable;


public interface ResponsableService {
	
	public Responsable findUserByEmail(String email);
	
	public void saveResponsable(Responsable responsable);
	
	public List<Responsable> listResponsable() ;
	
	public void DeleteResponsable(Responsable responsable) ;
	
	public Responsable getResponsableById(int id) ;

}
