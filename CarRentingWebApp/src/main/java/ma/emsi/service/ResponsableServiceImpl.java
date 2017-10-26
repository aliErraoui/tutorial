package ma.emsi.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.dao.ResponsableRepository;
import ma.emsi.dao.RoleRepository;
import ma.emsi.model.Responsable;
import ma.emsi.model.Role;



@Service("responsableService")
public class ResponsableServiceImpl implements ResponsableService {

	@Autowired
    private ResponsableRepository responsableRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
	@Override
	public Responsable findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return responsableRepository.findByEmail(email);
	}

	@Override
	public void saveResponsable(Responsable responsable) {
		// TODO Auto-generated method stub
		
		responsable.setPassword(bCryptPasswordEncoder.encode(responsable.getPassword()));
        responsable.setActive(1);
       
        Role userRole = roleRepository.findByRole("ROLE_RESPONSABLE");
       // System.out.println(userRole.getId());
        responsable.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        responsableRepository.save(responsable) ;
		
		
	}

	@Override
	public List<Responsable> listResponsable() {
		// TODO Auto-generated method stub
		return responsableRepository.findAll();
	}

	@Override
	public void DeleteResponsable(Responsable responsable) {
		// TODO Auto-generated method stub
		
		Set<Role> roles = null ;
		responsable.setRoles(roles);
		responsableRepository.delete(responsable);
		
	}

	@Override
	public Responsable getResponsableById(int id) {
		// TODO Auto-generated method stub
		List<Responsable> responsables = responsableRepository.findAll() ;
		for(int i=0;i<responsables.size();i++){
			  Responsable responsable= responsables.get(i) ;
		  if(responsable.getId()==id)
			  return responsable ;
		}
		return null ;
	}
	
	
	
	
	
	
	
	
	
	
	

}
