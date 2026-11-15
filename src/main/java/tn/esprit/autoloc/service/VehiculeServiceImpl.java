package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.AffectationEquipementRequestDTO;
import tn.esprit.autoloc.web.dto.VehiculeEquipementsResponseDTO;

@Service
@RequiredArgsConstructor

public class VehiculeServiceImpl implements IVehiculeService {
    private final VehiculeRepository VehiculeRepository;
    private final EquipementRepository equipementRepository;
    @Override
    public Vehicule create(Vehicule Vehicule) {
        if (Vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Vehicule ne doit pas avoir d'identifiant");
        }
        return VehiculeRepository.save(Vehicule);
    }
    @Override
    public Vehicule findById(Long id) {
        return VehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }
    @Override
    public List<Vehicule> findAll() {

        return VehiculeRepository.findAll();
    }
    @Override
    public Vehicule update(Long id, Vehicule Vehicule) {
        if(Vehicule.getTarifJournalier().intValueExact() < 0)
        {
            throw new IllegalArgumentException("Tarif doit etre psotive ..");
        }
        Vehicule existant = findById(id);
        return VehiculeRepository.save(existant);
    }
    @Override
    public void deleteById(Long id) {
        if (!VehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        VehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public VehiculeEquipementsResponseDTO affecterEquipement(AffectationEquipementRequestDTO request) {
        Vehicule vehicule = findById(request.idVehicule());
        Equipement equipement = equipementRepository.findById(request.idEquipement())
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", request.idEquipement()));

        if (vehicule.getEquipements().contains(equipement)) {
            throw new IllegalArgumentException("Cet équipement est déjà affecté à ce véhicule");
        }
        vehicule.getEquipements().add(equipement);
        return VehiculeEquipementsResponseDTO.from(VehiculeRepository.save(vehicule));
    }
}