package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.AffectationEquipementDTO;
import tn.esprit.autoloc.web.dto.VehiculeEquipementsDTO;

import java.util.List;

public interface IVehiculeService {
    Vehicule create(Vehicule Vehicule);
    Vehicule findById(Long id);
    List<Vehicule> findAll();
    Vehicule update(Long id, Vehicule Vehicule);
    void deleteById(Long id);
    VehiculeEquipementsDTO affecterEquipement(AffectationEquipementDTO dto);
}
