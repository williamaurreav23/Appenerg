package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Poste implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3053478382970635522L;
	int created_at;
	int  poste_id;
	int plano_id;
	String manzanaId;
	String  posteIdChar;
	String  nombreCalle;
	String cruza;
	String segmentoCalleCruce;
	String altura;
	String id_padvi;
	String ubicacionId;
	String rolId;
	String tipoPosteId;
	String luminaId;
	String propId;
	String inclinacionId;
	String cantCableId;
	String bajalibreId;
	String equipEleId;
	String altPosteId;
	String resistenciaPosteId;
	String  comentarios;
	String  fecha;
	String  hora;
	String  longitud;
	String  latitud;
	String  fotoPanoramica;
	String  fotoAcercamiento;
	String  fotoPlaca;
	int updated_at;
	int is_deleted;

	public Integer getPoste_id() {
		return poste_id;
	}

	public void setPoste_id(Integer  Poste_id) {
		this.poste_id = poste_id;
	}

	public String getManzana_id() {
		return manzanaId;
	}

	public void setManzana_id(String manzanaId) {
		this.manzanaId = manzanaId;
	}

	public String getPosteIdChar() {
		return posteIdChar;
	}

	public void setPosteIdChar(String posteIdChar) {
		this.posteIdChar = posteIdChar;
	}

	public String getNombreCalle() {
		return nombreCalle;
	}

	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	public String getCruza() {
		return cruza;
	}

	public void setCruza(String cruza) {
		this.cruza = cruza;
	}

	public String getSegmentoCalleCruce() {
		return segmentoCalleCruce;
	}

	public void setSegmentoCalleCruce(String segmentoCalleCruce) {
		this.segmentoCalleCruce = segmentoCalleCruce;
	}

	public String getBajalibreId() { return bajalibreId; }

	public void setBajalibreId(String bajalibreId) { this.bajalibreId = bajalibreId; }

	public String getEquipEleId() { return equipEleId; }

	public void setEquipEleId(String equipEleId) { this.equipEleId = equipEleId; }

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getIdpadvi() {
		return id_padvi;
	}

	public void setIdpadvi(String id_padvi) {
		this.id_padvi = id_padvi;
	}

	public String getUbicacion_id() {
		return ubicacionId;
	}

	public void setUbicacionId(String ubicacion_id) {
		this.ubicacionId = ubicacionId;
	}

	public String getTipoposteiD() {
		return tipoPosteId;
	}

	public void setTipoPosteId(String tipoPosteId) {
		this.tipoPosteId = tipoPosteId;
	}

	public String getLuminaId() {
		return luminaId;
	}

	public void setLuminaId(String luminaId) {
		this.luminaId = luminaId;
	}

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getInclinacionId() {
		return inclinacionId;
	}

	public void setInclinacionId(String inclinacionId) {
		this.inclinacionId = inclinacionId;
	}

	public String getCantcableId() {
		return cantCableId;
	}

	public void setCantCableId(String cantCableId) {
		this.cantCableId = cantCableId;
	}


	public void setAltPosteId(String altPosteId) {
		this.altPosteId = altPosteId;
	}

	public String getAltPosteId() {
		return altPosteId;
	}

	public void setResistenciaPosteId(String resistenciaPosteId) {
		this.resistenciaPosteId = resistenciaPosteId;
	}

	public String getResistenciaPosteId() {
		return resistenciaPosteId;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setfecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getHora() {
		return hora;
	}

	public void setFotoPanoramica(String fotoPanoramica) {
		this.fotoPanoramica = fotoPanoramica;
	}

	public String getFotoPanoramica() {
		return fotoPanoramica;
	}

	public void setFotoAcercamiento(String fotoAcercamiento) {
		this.fotoAcercamiento = fotoAcercamiento;
	}

	public Integer getPlano_Id() { return plano_id; }

	public void setPlano_Id(Integer plano_id) { this.plano_id = plano_id; }

	public void setLongitud(String longitud) { this.longitud = longitud; }

	public String getLongitud() { return  longitud; }

	public void setLatitud(String latitud) { this.latitud = latitud; }

	public String getLatitud() { return  latitud; }

	public String getFotoAcercamiento() {
		return fotoAcercamiento;
	}

	public void setFotoPlaca (String fotoPlaca) { this.fotoPlaca = fotoPlaca; }

	public String getFotoPlaca() { return fotoPlaca; }

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
