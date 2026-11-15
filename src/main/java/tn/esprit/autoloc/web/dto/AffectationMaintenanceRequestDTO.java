package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Demande d'affectation d'une maintenance à un véhicule")
public record AffectationMaintenanceRequestDTO(

        @Schema(description = "Identifiant de la maintenance", example = "1")
        @NotNull(message = "L'identifiant de la maintenance est obligatoire")
        @Positive(message = "L'identifiant de la maintenance doit être strictement positif")
        Long idMaintenance,

        @Schema(description = "Identifiant du véhicule", example = "1")
        @NotNull(message = "L'identifiant du véhicule est obligatoire")
        @Positive(message = "L'identifiant du véhicule doit être strictement positif")
        Long idVehicule) {
}