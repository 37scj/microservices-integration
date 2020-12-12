package br.com.fiap.drone.producer.dto;

import br.com.fiap.drone.producer.entity.DroneEntity;

import java.io.Serializable;

public class DroneDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Double latitude = 0d;
    private Double longitude = 0d;
    private Double temperatura = 0d;

    public DroneDTO() {
    }

    public DroneDTO(DroneEntity saveDrone) {
        if (saveDrone != null) {
            this.setId(saveDrone.getId());
            this.setNome(saveDrone.getNome());
            this.setLatitude(saveDrone.getLatitude());
            this.setLongitude(saveDrone.getLongitude());
            this.setTemperatura(saveDrone.getTemperatura());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

}
