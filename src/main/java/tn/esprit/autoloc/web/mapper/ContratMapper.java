package tn.esprit.autoloc.web.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.web.dto.ContratDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    @Mapping(source = "reservation.idReservation", target = "idReservation")
    @Mapping(source = "agence.idAgence", target = "idAgence")
    ContratDTO toDTO(Contrat contrat);

    List<ContratDTO> toDTOList(List<Contrat> contrats);
}
