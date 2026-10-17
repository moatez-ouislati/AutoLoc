package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.VehiculeRepository;

@Service
@RequiredArgsConstructor

public class VehiculeServiceImpl implements IVehiculeService {
    private final VehiculeRepository VehiculeRepository;
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
}