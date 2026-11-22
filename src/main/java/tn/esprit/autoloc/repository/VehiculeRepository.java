package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {

    // reservations -> contrat -> idContrat
    List<Vehicule> findDistinctByReservationsContratIdContrat(Long idContrat);

    // maintenances -> idMaintenance
    List<Vehicule> findDistinctByMaintenancesIdMaintenance(Long idMaintenance);

}
