package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.ContratDTO;
import tn.esprit.autoloc.web.dto.ErreurDTO;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats de location")


public class ContratController {
    private final IContratService contratService;

    @Operation(summary = "Lister les contrats")
    @ApiResponse(responseCode = "200", description = "Liste des contrats")
    @GetMapping
    public List<Contrat> findAll() { return contratService.findAll(); }

    @Operation(summary = "Consulter un contrat")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")})

    @GetMapping("/{id}")
    public Contrat findById(@PathVariable Long id) { return contratService.findById(id);
    }

    @Operation(summary = "Créer un contrat (avec ses paiements)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrat créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contrat create(@RequestBody Contrat contrat) { return
            contratService.create(contrat); }

    @Operation(summary = "Modifier un contrat")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")})

    //PUT
    @PutMapping("/{id}")
    public Contrat update(@PathVariable Long id, @RequestBody Contrat contrat) {
        return contratService.update(id, contrat);
    }

    @Operation(summary = "Supprimer un contrat (et ses paiements)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrat supprimé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")})

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id); }

    @GetMapping("/by-vehicule/{idVehicule}")
    @Operation(summary = "Lister les contrats d'un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des contrats (vide si aucun)"),
            @ApiResponse(responseCode = "400", description = "Identifiant invalide",
                    content = @Content(schema = @Schema(implementation = ErreurDTO.class))),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable",
                    content = @Content(schema = @Schema(implementation = ErreurDTO.class)))})
    public List<ContratDTO> findByVehicule(
            @Parameter(description = "Identifiant du véhicule", example = "1")
            @PathVariable @Positive(message = "L'identifiant du véhicule doit être strictement positif")
            Long idVehicule) {
        return contratService.findByVehiculeId(idVehicule);
    }






}

