package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.PaiementRepository;

@Service
@RequiredArgsConstructor

public class PaiementServiceImpl implements IPaiementService {
    private final PaiementRepository PaiementRepository;
    @Override
    public Paiement findById(Long id) {
        return PaiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
    }
    @Override
    public List<Paiement> findAll() {

        return PaiementRepository.findAll();
    }


}