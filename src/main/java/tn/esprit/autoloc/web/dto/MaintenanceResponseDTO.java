package tn.esprit.autoloc.web.dto;

import java.time.LocalDate;
import tn.esprit.autoloc.domain.Maintenance;

public record MaintenanceResponseDTO(
        Long idMaintenance,
        LocalDate dateDebut,
        LocalDate dateFin,
        String description,
        Long idVehicule,
        String immatriculationVehicule) {

    public static MaintenanceResponseDTO from(Maintenance m) {
        return new MaintenanceResponseDTO(
                m.getIdMaintenance(),
                m.getDateDebut(),
                m.getDateFin(),
                m.getDescription(),
                m.getVehicule() != null ? m.getVehicule().getIdVehicule() : null,
                m.getVehicule() != null ? m.getVehicule().getImmatriculation() : null);
    }
}