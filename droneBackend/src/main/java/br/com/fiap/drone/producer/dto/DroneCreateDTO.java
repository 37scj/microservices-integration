package br.com.fiap.drone.producer.dto;

import br.com.fiap.drone.producer.entity.DroneEntity;
import com.sun.istack.NotNull;

import java.io.Serializable;

public class DroneCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private Double latitude = 0d;
    private Double longitude = 0d;
    private Double temperatura = 0d;
    private Double umidade = 0d;

    public DroneCreateDTO(){
        //for serializer
    }

    public DroneCreateDTO(@NotNull DroneEntity drone) {
        this.setNome(drone.getNome());
        this.setLatitude(drone.getLatitude());
        this.setLongitude(drone.getLongitude());
        this.setTemperatura(drone.getTemperatura());
        this.setUmidade(drone.getUmidade());
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

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }
}
