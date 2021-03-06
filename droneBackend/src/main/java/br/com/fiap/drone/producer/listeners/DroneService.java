package br.com.fiap.drone.producer.listeners;

import br.com.fiap.drone.producer.dto.DroneCreateDTO;
import br.com.fiap.drone.producer.dto.DroneDTO;

import java.util.List;
import java.util.Optional;

public interface DroneService {

    DroneDTO storeDroneInfo(DroneCreateDTO dronePresenter, Long id);

    List<DroneDTO> findAllDrone();

    Optional<DroneDTO> findById(Long id);

    void deleteById(Long id);
}
