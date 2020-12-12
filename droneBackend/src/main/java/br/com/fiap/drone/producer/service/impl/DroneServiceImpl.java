package br.com.fiap.drone.producer.service.impl;

import br.com.fiap.drone.producer.dto.DroneDTO;
import br.com.fiap.drone.producer.entity.DroneEntity;
import br.com.fiap.drone.producer.repository.DroneRepository;
import br.com.fiap.drone.producer.service.DroneService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DroneServiceImpl.class);

    @Autowired
    private DroneRepository droneRepository;

    /**
     * Cadastrando um drone
     */
    @Override
    public DroneDTO storeDroneInfo(final DroneDTO droneParameter) {
        logger.info("Store Drone Info " + droneParameter.getId());
        DroneEntity newDrone = null;
        if (droneParameter.getId() != null) {
            newDrone = droneRepository.findById(droneParameter.getId())
                    .orElse(newDrone = new DroneEntity());
            //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            newDrone = new DroneEntity();
        }

        if (droneParameter.getId() != null) {
            newDrone.setId(droneParameter.getId());
        }
        if (droneParameter.getNome() != null) {
            newDrone.setNome(droneParameter.getNome());
        }
        if (droneParameter.getLatitude() != null) {
            newDrone.setLatitude(droneParameter.getLatitude());
        }
        if (droneParameter.getLongitude() != null) {
            newDrone.setLongitude(droneParameter.getLongitude());
        }
        if (droneParameter.getTemperatura() != null) {
            newDrone.setTemperatura(droneParameter.getTemperatura());
        }

        final DroneEntity savedDrone = droneRepository.save(newDrone);

        logger.info("DRONE GRAVADO COM SUCESSO");

        return savedDrone.toModel();

    }

    @Override
    public List<DroneDTO> findAllDrone() {
        return droneRepository.findAll().stream().map(DroneDTO::new).collect(Collectors.toList());
    }

    @Override
    public void deleteById(final Long id) {
        droneRepository.deleteById(id);
    }

    @Override
    public Optional<DroneDTO> findById(final Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(new DroneDTO(droneRepository.findById(id).orElse(null)));
    }

}
