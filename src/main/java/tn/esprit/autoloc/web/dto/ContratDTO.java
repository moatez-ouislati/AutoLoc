package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratDTO(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Long idContrat,

        @NotNull(message = "La date de signature est obligatoire")
        LocalDate dateSignature,

        @NotNull(message = "Le montant total est obligatoire")
        @PositiveOrZero(message = "Le montant ne peut pas être négatif")
        @Digits(integer = 8, fraction = 2)
        BigDecimal montantTotal,

        boolean valide,

        Long idReservation,
        Long idAgence) {
}
