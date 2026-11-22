package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {

    // vehicule -> idVehicule, tri du plus récent au plus ancien
    List<Maintenance> findByVehiculeIdVehiculeOrderByDateDebutDesc(Long idVehicule);

}