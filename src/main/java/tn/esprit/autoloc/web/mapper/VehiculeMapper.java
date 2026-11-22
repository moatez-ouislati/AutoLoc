package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDTO;
import tn.esprit.autoloc.web.dto.VehiculeEquipementsDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = EquipementMapper.class)
public interface VehiculeMapper {
    VehiculeEquipementsDTO toEquipementsDTO(Vehicule vehicule);

    @Mapping(source = "agence.idAgence", target = "idAgence")
    VehiculeDTO toDTO(Vehicule vehicule);

    List<VehiculeDTO> toDTOList(List<Vehicule> vehicules);
}
