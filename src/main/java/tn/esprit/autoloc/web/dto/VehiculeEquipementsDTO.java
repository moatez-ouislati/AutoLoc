package tn.esprit.autoloc.web.dto;

import java.util.List;

public record VehiculeEquipementsDTO(
        Long idVehicule,
        String immatriculation,
        String marque,
        String modele,
        List<EquipementDTO> equipements) {
}