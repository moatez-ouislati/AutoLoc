package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.AffectationEquipementDTO;
import tn.esprit.autoloc.web.dto.VehiculeDTO;
import tn.esprit.autoloc.web.dto.VehiculeEquipementsDTO;
import tn.esprit.autoloc.web.mapper.VehiculeMapper;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;
    private final ContratRepository contratRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Vehicule ne doit pas avoir d'identifiant");
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        if (vehicule.getTarifJournalier().intValueExact() < 0) {
            throw new IllegalArgumentException("Tarif doit etre psotive ..");
        }
        Vehicule existant = findById(id);
        return vehiculeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public VehiculeEquipementsDTO affecterEquipement(AffectationEquipementDTO dto) {
        Vehicule vehicule = findById(dto.idVehicule());
        Equipement equipement = equipementRepository.findById(dto.idEquipement())
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", dto.idEquipement()));

        // Set.add() renvoie false si l'équipement est déjà présent
        if (!vehicule.getEquipements().add(equipement)) {
            throw new IllegalStateException("Cet équipement est déjà affecté à ce véhicule");
        }
        // Pas de save() : l'entité est managée, Hibernate enregistre la modification au commit
        return vehiculeMapper.toEquipementsDTO(vehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeDTO> findByContratId(Long idContrat) {
        if (!contratRepository.existsById(idContrat)) {
            throw new ResourceNotFoundException("Contrat", idContrat);
        }
        return vehiculeMapper.toDTOList(
                vehiculeRepository.findDistinctByReservationsContratIdContrat(idContrat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeDTO> findByMaintenanceId(Long idMaintenance) {
        if (!maintenanceRepository.existsById(idMaintenance)) {
            throw new ResourceNotFoundException("Maintenance", idMaintenance);
        }
        return vehiculeMapper.toDTOList(
                vehiculeRepository.findDistinctByMaintenancesIdMaintenance(idMaintenance));
    }
}