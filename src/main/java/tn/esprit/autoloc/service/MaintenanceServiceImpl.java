package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.MaintenanceRepository;

@Service
@RequiredArgsConstructor

public class MaintenanceServiceImpl implements IMaintenanceService {
    private final MaintenanceRepository MaintenanceRepository;
    @Override
    public Maintenance create(Maintenance Maintenance) {
        if (Maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Maintenance ne doit pas avoir d'identifiant");
        }
        return MaintenanceRepository.save(Maintenance);
    }
    @Override
    public Maintenance findById(Long id) {
        return MaintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }
    @Override
    public List<Maintenance> findAll() {

        return MaintenanceRepository.findAll();
    }
    @Override
    public Maintenance update(Long id, Maintenance Maintenance) {
        Maintenance existant = findById(id);
        if(Maintenance.getDateDebut().isAfter(Maintenance.getDateFin())){
            throw new IllegalArgumentException("date fin doit etre apres date debut");
        }
        return MaintenanceRepository.save(existant);
    }
    @Override
    public void deleteById(Long id) {
        if (!MaintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        MaintenanceRepository.deleteById(id);
    }
}