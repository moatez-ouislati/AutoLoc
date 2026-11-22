package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    @Mapping(source = "vehicule.immatriculation", target = "immatriculationVehicule")
    MaintenanceDTO toDTO(Maintenance maintenance);
    List<MaintenanceDTO> toDTOList(List<Maintenance> maintenances);
}
