package ma.emsi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.model.Client;




@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
