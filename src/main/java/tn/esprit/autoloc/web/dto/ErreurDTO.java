package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Réponse d'erreur standard de l'API")
public record ErreurDTO(

        @Schema(example = "400")
        int statut,

        @Schema(example = "Données invalides")
        String message,

        @Schema(description = "Messages de validation par champ (présent uniquement pour une erreur 400 de validation)",
                example = "{\"idVehicule\": \"L'identifiant du véhicule doit être strictement positif\"}")
        Map<String, String> erreurs,

        LocalDateTime horodatage) {
}