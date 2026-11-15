package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Demande d'affectation d'un équipement à un véhicule")
public record AffectationEquipementRequestDTO(

        @Schema(description = "Identifiant du véhicule", example = "1")
        @NotNull(message = "L'identifiant du véhicule est obligatoire")
        @Positive(message = "L'identifiant du véhicule doit être strictement positif")
        Long idVehicule,

        @Schema(description = "Identifiant de l'équipement", example = "1")
        @NotNull(message = "L'identifiant de l'équipement est obligatoire")
        @Positive(message = "L'identifiant de l'équipement doit être strictement positif")
        Long idEquipement) {
}