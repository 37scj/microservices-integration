package br.com.fiap.drone.producer.dto;

import br.com.fiap.drone.producer.entity.DroneEntity;
import com.sun.istack.NotNull;

public class DroneDTO extends DroneCreateDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    public DroneDTO() {
        super();
    }

    public DroneDTO(@NotNull DroneEntity saveDrone) {
        super(saveDrone);
        this.setId(saveDrone.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
