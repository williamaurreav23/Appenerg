package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class mst_rol {

    private static final long serialVersionUID = 3855881834307278660L;

    @JsonProperty("id")
    int id;

    @JsonProperty("nombre")
    String nombre;

    public int getId () { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre (String nombre) { this.nombre = nombre; }
}
