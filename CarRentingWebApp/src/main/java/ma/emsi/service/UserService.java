package ma.emsi.service;

import java.util.List;

import ma.emsi.model.User;

public interface UserService {
	
	public User findUserByEmail(String email);
	
	public void saveUser(User user);
	
	public void saveResponsable(User user);
	
	public List<User> listUser() ;
		
	public void DeleteUser(User user) ;
	
	public void DeleteReservUser(int id) ;
	
	public User getUserById(int id) ;
}