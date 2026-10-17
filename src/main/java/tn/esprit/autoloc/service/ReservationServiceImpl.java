package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ReservationRepository;

@Service
@RequiredArgsConstructor

public class ReservationServiceImpl implements IReservationService {
    private final ReservationRepository ReservationRepository;
    @Override
    public Reservation create(Reservation Reservation) {
        if (Reservation.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Reservation ne doit pas avoir d'identifiant");
        }
        return ReservationRepository.save(Reservation);
    }
    @Override
    public Reservation findById(Long id) {
        return ReservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
    }
    @Override
    public List<Reservation> findAll() {

        return ReservationRepository.findAll();
    }
    @Override
    public Reservation update(Long id, Reservation Reservation) {
        Reservation existant = findById(id);
        if(Reservation.getDateDebut().isAfter(Reservation.getDateFin())){
            throw new IllegalArgumentException("date fin doit etre apres date debut");
        }
        return ReservationRepository.save(existant);
    }
    @Override
    public void deleteById(Long id) {
        if (!ReservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }
        ReservationRepository.deleteById(id);
    }
}