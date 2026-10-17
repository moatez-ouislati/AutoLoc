package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.AgenceRepository;

@Service
@RequiredArgsConstructor

public class AgenceServiceImpl implements IAgenceService {
    private final AgenceRepository AgenceRepository;
    @Override
    public Agence create(Agence Agence) {
        if (Agence.getIdAgence() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Agence ne doit pas avoir d'identifiant");
        }
        return AgenceRepository.save(Agence);
    }
    @Override
    public Agence findById(Long id) {
        return AgenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }
    @Override
    public List<Agence> findAll() {

        return AgenceRepository.findAll();
    }
    @Override
    public Agence update(Long id, Agence Agence) {
        Agence existant = findById(id);
        return AgenceRepository.save(existant);
    }
    @Override
    public void deleteById(Long id) {
        if (!AgenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }
        AgenceRepository.deleteById(id);
    }
}