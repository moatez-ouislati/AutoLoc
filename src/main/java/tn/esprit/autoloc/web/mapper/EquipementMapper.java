package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.web.dto.EquipementDTO;

@Mapper(componentModel = "spring")
public interface EquipementMapper {
    EquipementDTO toDTO(Equipement equipement);
}
