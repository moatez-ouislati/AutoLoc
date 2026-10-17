package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.EmployeRepository;

@Service
@RequiredArgsConstructor

public class EmployeServiceImpl implements IEmployeService {
    private final EmployeRepository EmployeRepository;
    @Override
    public Employe create(Employe Employe) {
        if (Employe.getIdEmploye() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Employe ne doit pas avoir d'identifiant");
        }
        return EmployeRepository.save(Employe);
    }
    @Override
    public Employe findById(Long id) {
        return EmployeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }
    @Override
    public List<Employe> findAll() {

        return EmployeRepository.findAll();
    }
    @Override
    public Employe update(Long id, Employe Employe) {
        Employe existant = findById(id);
        return EmployeRepository.save(existant);
    }
    @Override
    public void deleteById(Long id) {
        if (!EmployeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        EmployeRepository.deleteById(id);
    }
}