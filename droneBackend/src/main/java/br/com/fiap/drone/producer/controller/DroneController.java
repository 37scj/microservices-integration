package br.com.fiap.drone.producer.controller;

import br.com.fiap.drone.producer.dto.DroneDTO;
import br.com.fiap.drone.producer.service.DroneService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    /**
     * Cadastrar drone
     *
     * @param drone
     * @return
     */
    @ApiOperation(value = "Cria um novo drone")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DroneDTO addDrone(@RequestBody DroneDTO drone) {
        drone.setId(null);
        return droneService.storeDroneInfo(drone);
    }

    @ApiOperation(value = "Atualiza informações do drone")
    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DroneDTO patchDrone(@RequestBody DroneDTO drone) {
        if (drone.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Necessário ID do Drone");
        }
        return droneService.storeDroneInfo(drone);
    }

    /**
     * Localizar drone
     */
    @ApiOperation(value = "Lista todos os drone")
    @GetMapping()
    public List<DroneDTO> getAll() {
        return droneService.findAllDrone();
    }

    @ApiOperation(value = "Busca informações de um drone")
    @GetMapping("/{id}")
    public DroneDTO getById(@PathVariable Long id) {
        return droneService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Drone não encontrado (id: " + id.toString() + ")"));
    }

    @ApiOperation(value = "Exclui um drone")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteDrone(@PathVariable Long id) {
        droneService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Drone não encontrado (id: " + id.toString() + ")"));
        droneService.deleteById(id);
    }
}
