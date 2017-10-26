package ma.emsi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ma.emsi.model.Voiture;

@Repository("voitureRepository")
public interface VoitureRepository extends JpaRepository<Voiture, Long> {

}
