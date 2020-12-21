package br.com.fiap.drone.producer.controller;

import br.com.fiap.drone.producer.dto.DroneCreateDTO;
import br.com.fiap.drone.producer.dto.DroneDTO;
import br.com.fiap.drone.producer.listeners.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Drone")
@RequestMapping("drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    /**
     * Cadastrar drone
     *
     * @param drone Dados do drone
     * @return drone com resultado que foi gravado
     */
    @ApiOperation(value = "Cria um novo drone")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DroneDTO addDrone(@RequestBody DroneCreateDTO drone) {
        return droneService.storeDroneInfo(drone, null);
    }

    @ApiOperation(value = "Atualiza informações do drone")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DroneDTO patchDrone(@PathVariable Long id, @RequestBody DroneCreateDTO drone) {
        Optional.ofNullable(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Necessário ID para Drone"));
        Optional.ofNullable(drone).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Necessário ID para Drone"));

        return droneService.storeDroneInfo(drone, id);
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
