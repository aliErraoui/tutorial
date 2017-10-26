package ma.emsi.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.model.Reservation;

@Repository("reservationRepository")
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
