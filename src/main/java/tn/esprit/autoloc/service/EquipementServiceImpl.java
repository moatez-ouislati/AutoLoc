package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.EquipementRepository;

@Service
@RequiredArgsConstructor

public class EquipementServiceImpl implements IEquipementService {
    private final EquipementRepository EquipementRepository;
    @Override
    public Equipement create(Equipement Equipement) {
        if (Equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Equipement ne doit pas avoir d'identifiant");
        }
        return EquipementRepository.save(Equipement);
    }
    @Override
    public Equipement findById(Long id) {
        return EquipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }
    @Override
    public List<Equipement> findAll() {

        return EquipementRepository.findAll();
    }
    @Override
    public Equipement update(Long id, Equipement Equipement) {
        if(Equipement.getLibelle() == null)
        {
            throw new IllegalArgumentException("Label est obligatoire ..");
        }
        Equipement existant = findById(id);
        return EquipementRepository.save(existant);
    }
    @Override
    public void deleteById(Long id) {
        if (!EquipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }
        EquipementRepository.deleteById(id);
    }
}