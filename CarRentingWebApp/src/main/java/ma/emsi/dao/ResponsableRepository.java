package ma.emsi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.model.Responsable;


@Repository("responsableRepository")
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
	Responsable findByEmail(String email);

	
}
