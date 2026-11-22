package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.ContratDTO;
import tn.esprit.autoloc.web.mapper.ContratMapper;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;
    private final VehiculeRepository vehiculeRepository;
    private final ContratMapper contratMapper;

    @Override
    public Contrat create(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        verifierMontant(contrat);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Long id, Contrat contrat) {
        Contrat existant = findById(id);
        verifierMontant(contrat);
        existant.setMontantTotal(contrat.getMontantTotal());
        return contratRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> findByVehiculeId(Long idVehicule) {
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new ResourceNotFoundException("Vehicule", idVehicule);
        }
        return contratMapper.toDTOList(
                contratRepository.findByReservationVehiculeIdVehiculeOrderByDateSignatureDesc(idVehicule));
    }

    private void verifierMontant(Contrat contrat) {
        if (contrat.getMontantTotal() == null || contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException(
                    "Le montant total d'un contrat doit être positif ou nul");
        }
    }
}