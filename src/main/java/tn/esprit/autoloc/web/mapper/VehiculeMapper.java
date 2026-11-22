package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeEquipementsDTO;

@Mapper(componentModel = "spring", uses = EquipementMapper.class)
public interface VehiculeMapper {
    VehiculeEquipementsDTO toEquipementsDTO(Vehicule vehicule);
}
