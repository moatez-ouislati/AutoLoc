package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.web.dto.AffectationMaintenanceRequestDTO;
import tn.esprit.autoloc.web.dto.MaintenanceResponseDTO;

import java.util.List;

public interface IMaintenanceService {
    Maintenance create(Maintenance Maintenance);
    Maintenance findById(Long id);
    List<Maintenance> findAll();
    Maintenance update(Long id, Maintenance Maintenance);
    void deleteById(Long id);

    MaintenanceResponseDTO affecterAVehicule(AffectationMaintenanceRequestDTO request);
}
