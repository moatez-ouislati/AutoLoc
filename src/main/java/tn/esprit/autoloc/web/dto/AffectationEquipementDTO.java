package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Affectation d'un équipement à un véhicule")
public record AffectationEquipementDTO(

        @Schema(description = "Identifiant du véhicule (obligatoire, strictement positif)",
                example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
        @NotNull(message = "L'identifiant du véhicule est obligatoire")
        @Positive(message = "L'identifiant du véhicule doit être strictement positif")
        Long idVehicule,

        @Schema(description = "Identifiant de l'équipement (obligatoire, strictement positif)",
                example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
        @NotNull(message = "L'identifiant de l'équipement est obligatoire")
        @Positive(message = "L'identifiant de l'équipement doit être strictement positif")
        Long idEquipement) {
}