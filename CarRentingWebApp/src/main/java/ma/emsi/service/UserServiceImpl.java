package ma.emsi.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.dao.RoleRepository;
import ma.emsi.dao.UserRepository;
import ma.emsi.model.Role;
import ma.emsi.model.User;







@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
       
        Role userRole = roleRepository.findByRole("ROLE_USER");
       // System.out.println(userRole.getId());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void DeleteUser(User user) {
		// TODO Auto-generated method stub
		Set<Role> roles = null ;
		user.setRoles(roles);
		userRepository.delete(user);
		
	}

	@Override
	public void DeleteReservUser(int id) {
		// TODO Auto-generated method stub
		List<User> users=userRepository.findAll();
		for(int i=0;i<users.size();i++){
			  User user = users.get(i) ;
			  System.out.println(user.getId());
		  if(user.getId()==id)
			  userRepository.delete(user);
		}
		
		
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		
		List<User> users = userRepository.findAll() ;
		for(int i=0;i<users.size();i++){
			  User user = users.get(i) ;
		  if(user.getId()==id)
			  return user ;
		}
		return null ;
		
	}

	@Override
	public void saveResponsable(User user) {
		// TODO Auto-generated method stub
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
       
        Role userRole = roleRepository.findByRole("RESPONSABLE");
       // System.out.println(userRole.getId());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	

}