package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.AffectationEquipementRequestDTO;
import tn.esprit.autoloc.web.dto.VehiculeEquipementsResponseDTO;

import java.util.List;

public interface IVehiculeService {
    Vehicule create(Vehicule Vehicule);
    Vehicule findById(Long id);
    List<Vehicule> findAll();
    Vehicule update(Long id, Vehicule Vehicule);
    void deleteById(Long id);
    VehiculeEquipementsResponseDTO affecterEquipement(AffectationEquipementRequestDTO request);
}
