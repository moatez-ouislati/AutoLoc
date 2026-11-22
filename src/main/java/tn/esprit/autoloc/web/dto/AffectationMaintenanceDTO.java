package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Affectation d'une maintenance à un véhicule")
public record AffectationMaintenanceDTO(

        @Schema(description = "Identifiant de la maintenance (obligatoire, strictement positif)",
                example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
        @NotNull(message = "L'identifiant de la maintenance est obligatoire")
        @Positive(message = "L'identifiant de la maintenance doit être strictement positif")
        Long idMaintenance,

        @Schema(description = "Identifiant du véhicule (obligatoire, strictement positif)",
                example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
        @NotNull(message = "L'identifiant du véhicule est obligatoire")
        @Positive(message = "L'identifiant du véhicule doit être strictement positif")
        Long idVehicule) {
}