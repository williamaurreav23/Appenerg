package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plano  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3855881834307278660L;

	@JsonProperty("id_plano")
	Integer id_plano;

	@JsonProperty("cod_plano")
	String cod_plano;

	@JsonProperty("zona")
	String zona;

	@JsonProperty("manzana")
	String manzana;

	@JsonProperty("lote")
	String lote;

	@JsonProperty("cruza")
	String cruza;

	@JsonProperty("segmento")
	String segmento;

	@JsonProperty("altura")
	String altura;

	@JsonProperty("interior")
	String interior;

	@JsonProperty("tipoUso")
	String tipoUso;

	@JsonProperty("unid_prediales_lote")
	String unid_prediales_lote;

	@JsonProperty("maxPisosLote")
	String maxPisosLote;

	@JsonProperty("id_placa")
	String id_placa;

	@JsonProperty("id_lote")
	String id_lote;

	@JsonProperty("XMFId")
	String XMFId;
	
	@JsonProperty("created_at")
	int created_at;

	@JsonProperty("updated_at")
	int updated_at;

	@JsonProperty("is_deleted")
	int is_deleted;



	public String getMaxPisosLote() { return maxPisosLote; }

	public void setMaxPisosLote(String maxPisosLote) { this.maxPisosLote = maxPisosLote; }

	public String getZona() { return zona; }

	public void setZona(String zona){ this.zona = zona; }

	public String getManzana() { return this.manzana; }

	public void setManzana(String manzana){ this.manzana = manzana; }

	public String getlote() { return lote; }

	public void setlote(String lote) {  this.lote = lote; }


	public String getCruza() { return cruza; }

	public String setCruza(String cruza) { return cruza; }

	public String getSegmento() { return  segmento; }
	public void setSegmento(String segmento) { this.segmento = segmento; }

	public String getAltura() { return altura; }

	public void setAltura(String altura) { this.altura = altura; }

	public String getInterior() { return interior; }

	public void setInterior(String interior) { this.interior = interior;}

	public int getId_plano() { return id_plano; }

	public void setId_plano(int id_plano) { this.id_plano = id_plano; }

	public String getCod_plano() { return cod_plano; }

	public void setCod_plano(String cod_plano) { this.cod_plano = cod_plano; }

	public String getTipoUso() { return tipoUso; }

	public void setTipoUso(String tipoUso) { this.tipoUso = tipoUso; }

	public String getUnid_prediales_lote() { return unid_prediales_lote; }

	public void setUnid_prediales_lote(String unid_prediales_lote) { this.unid_prediales_lote = unid_prediales_lote; }

	public String getId_lote() { return id_lote; }

	public void setId_lote(String id_lote) { this.id_lote = id_lote; }

	public String getId_placa() { return id_placa; }

	public void setId_placa(String id_placa) { this.id_placa = id_placa; }

	public String getXMFId() { return XMFId; }

	public void setXMFId(String XMFId) { this.XMFId = XMFId; }

	
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	
	public int getCreated_at() {
		return created_at;
	}

	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}
	
	public int getUpdated_at() {
		return updated_at;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	public int getIs_deleted() {
		return is_deleted;
	}
}
