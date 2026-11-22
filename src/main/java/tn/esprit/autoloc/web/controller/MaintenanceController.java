package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.web.dto.AffectationMaintenanceDTO;
import tn.esprit.autoloc.web.dto.ErreurDTO;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @Operation(summary = "Lister les maintenances")
    @ApiResponse(responseCode = "200", description = "Liste des maintenances")
    @GetMapping
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @Operation(summary = "Consulter une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")})
    @GetMapping("/{id}")
    public Maintenance findById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @Operation(summary = "Créer une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @Operation(summary = "Modifier une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")})
    @PutMapping("/{id}")
    public Maintenance update(@Parameter(description = "Identifiant") @PathVariable Long id,
                              @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @Operation(summary = "Supprimer une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maintenance supprimée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        maintenanceService.deleteById(id);
    }

    //DTO
    @PutMapping("/affecter-vehicule")
    @Operation(summary = "Affecter une maintenance à un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance affectée au véhicule"),

            @ApiResponse(responseCode = "400", description = "Données invalides (échec de la validation)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErreurDTO.class),
                            examples = @ExampleObject(name = "Erreur de validation", value = """
                {
                  "statut": 400,
                  "message": "Données invalides",
                  "erreurs": {
                    "idMaintenance": "L'identifiant de la maintenance doit être strictement positif",
                    "idVehicule": "L'identifiant du véhicule est obligatoire"
                  },
                  "horodatage": "2026-11-22T10:30:00"
                }
                """))),

            @ApiResponse(responseCode = "404", description = "Maintenance ou véhicule introuvable",
                    content = @Content(schema = @Schema(implementation = ErreurDTO.class)))
    })
    public MaintenanceDTO affecterAVehicule(@Valid @RequestBody AffectationMaintenanceDTO dto) {
        return maintenanceService.affecterAVehicule(dto);
    }

}