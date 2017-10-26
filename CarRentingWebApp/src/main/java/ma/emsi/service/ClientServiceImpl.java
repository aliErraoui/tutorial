package ma.emsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.dao.ClientRepository;
import ma.emsi.model.Client;


@Service("clientService")
public class ClientServiceImpl implements ClientService {

	
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@Override
	public void saveClient(Client client) {
		// TODO Auto-generated method stub
		clientRepository.save(client) ;
		
		
		
	}
	
	

}
