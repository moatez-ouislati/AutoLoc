package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;

@Schema(description = "Véhicule (utilisé en entrée et en sortie)")
public record VehiculeDTO(

        @Schema(description = "Identifiant généré par le serveur", accessMode = Schema.AccessMode.READ_ONLY)
        Long idVehicule,

        @Schema(example = "123 TUN 4567")
        @NotBlank(message = "L'immatriculation est obligatoire")
        @Size(max = 20, message = "L'immatriculation ne doit pas dépasser 20 caractères")
        String immatriculation,

        @Schema(example = "Renault")
        @NotBlank(message = "La marque est obligatoire")
        @Size(max = 50, message = "La marque ne doit pas dépasser 50 caractères")
        String marque,

        @Schema(example = "Clio")
        @NotBlank(message = "Le modèle est obligatoire")
        @Size(max = 50, message = "Le modèle ne doit pas dépasser 50 caractères")
        String modele,

        @NotNull(message = "La catégorie est obligatoire")
        CategorieVehicule categorie,

        @Schema(example = "80.00")
        @NotNull(message = "Le tarif journalier est obligatoire")
        @PositiveOrZero(message = "Le tarif ne peut pas être négatif")
        @Digits(integer = 8, fraction = 2, message = "Le tarif doit avoir au plus 8 chiffres et 2 décimales")
        BigDecimal tarifJournalier,

        @NotNull(message = "Le statut est obligatoire")
        StatutVehicule statut,

        @Schema(description = "Identifiant de l'agence (optionnel)", example = "1")
        @Positive(message = "L'identifiant de l'agence doit être strictement positif")
        Long idAgence) {
}