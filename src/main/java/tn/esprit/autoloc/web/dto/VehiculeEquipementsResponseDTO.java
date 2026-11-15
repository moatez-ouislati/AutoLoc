package tn.esprit.autoloc.web.dto;

import java.util.List;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;

public record VehiculeEquipementsResponseDTO(
        Long idVehicule,
        String immatriculation,
        String marque,
        String modele,
        List<EquipementDTO> equipements) {

    public record EquipementDTO(Long idEquipement, String libelle) {
        static EquipementDTO from(Equipement e) {
            return new EquipementDTO(e.getIdEquipement(), e.getLibelle());
        }
    }

    public static VehiculeEquipementsResponseDTO from(Vehicule v) {
        return new VehiculeEquipementsResponseDTO(
                v.getIdVehicule(),
                v.getImmatriculation(),
                v.getMarque(),
                v.getModele(),
                v.getEquipements().stream().map(EquipementDTO::from).toList());
    }
}