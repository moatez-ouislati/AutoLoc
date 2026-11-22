package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat,Long> {

    // reservation -> vehicule -> idVehicule, tri du plus récent au plus ancien
    List<Contrat> findByReservationVehiculeIdVehiculeOrderByDateSignatureDesc(Long idVehicule);

}
