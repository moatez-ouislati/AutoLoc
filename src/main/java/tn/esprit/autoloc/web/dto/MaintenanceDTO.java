package tn.esprit.autoloc.web.dto;

import java.time.LocalDate;

public record MaintenanceDTO(
        Long idMaintenance,
        LocalDate dateDebut,
        LocalDate dateFin,
        String description,
        Long idVehicule,
        String immatriculationVehicule) {
}
