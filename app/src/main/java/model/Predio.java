package model;

import java.io.Serializable;

public class Predio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3053478382970635522L;

	int created_at;
	Integer predio_id;
	Integer plano_detail_id;
	Integer plano_id;
	Integer manzanaId;
	String nombreCalle;
	String cruza;
	String segmentoCalleCruce;
	String altura;
	String interior;
	String pisos;
	String cant_HP;
	String CantMedEnergia;
	String cantMedGas;
	String numTimbres;
	String tipoPredioId;
	String snEnConst;
	String enConsDetalle;
	String cantLocales_HP;
	String accesoId;
	String poste_id;
	String nomConjun;
	String numTorres;
	String contacto;
	String fotoPanoramicaConjunto;
	String comentarios;
	String otrosDetalle;
	String cantPuertas;
	int updated_at;
	int is_deleted;



	public Integer getPredio_Id() {
		return predio_id;
	}

	public void setPredio_Id(Integer predio_id) {
		this.predio_id = predio_id;
	}

	public Integer getPlano_detail_id() { return plano_detail_id; }

	public void setPlano_detail_id(Integer plano_detail_id) { this.plano_detail_id = plano_detail_id; }

	public Integer getPlano_id() { return plano_id; }

	public void setPlano_id(Integer plano_id) { this.plano_id = plano_id; }

	public Integer getManzanaId() { return manzanaId; }

	public void setManzanaId(Integer manzanaId) { this.manzanaId = manzanaId; }

	public String getNombreCalle() { return nombreCalle; }

	public void setNombreCalle(String nombreCalle) { this.nombreCalle = nombreCalle; }

	public String getSegmentoCalle() { return segmentoCalleCruce; }

	public void setSegmentoCalle(String segmentocruza) { this.segmentoCalleCruce = segmentocruza; }

	public String getAltura() { return altura; }

	public void setAltura(String altura) { this.altura = altura; }

	public String getCruza() { return cruza; }

	public void setCruza(String cruza) { this.cruza = cruza; }

	public String getInterior() { return interior; }

	public void setInterior(String interior) { this.interior = interior; }

	public String getPisos() { return pisos; }

	public void setPisos(String pisos) { this.segmentoCalleCruce = pisos; }

	public String getCant_HP() { return cant_HP; }

	public void setCant_HP(String cant_HP) { this.cant_HP = cant_HP; }

	public String getCantMedEnergia() { return CantMedEnergia; }

	public void setCantMedEnergia(String cantmedenergia) { this.CantMedEnergia = CantMedEnergia; }

	public String getCantMedGas() { return cantMedGas; }

	public void setCantMedGas(String cantMedGas) { this.segmentoCalleCruce = cantMedGas; }

	public String getNumTimbres() { return numTimbres; }

	public void setNumTimbres(String numTimbres) { this.numTimbres = numTimbres; }

	public String getNumtimbres() { return numTimbres; }

	public void setTipoPredioId(String tipoPredioId) { this.tipoPredioId = tipoPredioId; }

	public String getTipoPredioId() { return tipoPredioId; }

	public void setSnEnConst(String snEnConst) { this.snEnConst = snEnConst; }

	public String getSnEnConst () { return snEnConst; }

	public void setEnConsDetalle(String enConsDetalle) { this.enConsDetalle = enConsDetalle; }

	public String getEnConsDetalle () { return enConsDetalle; }

	public void setCantlocales(String cantLocales_HP) { this.cantLocales_HP = cantLocales_HP; }

	public String getCantlocales () { return cantLocales_HP; }

	public void setAccesoId(String accesoId) { this.accesoId = accesoId; }

	public String getAccesoId () { return accesoId; }

	public void setPoste_id(String poste_id) { this.poste_id = poste_id; }

	public String getPoste_id () { return poste_id;}

	public void setNomConjun(String nomconjun) { this.nomConjun = nomConjun; }

	public String getNomConjun () { return nomConjun; }

	public void setContacto(String contacto) { this.contacto = contacto; }

	public String getContacto () { return contacto; }

	public void setFotoPanoramicaConjunto(String fotoPanoramicaConjunto) { this.fotoPanoramicaConjunto = fotoPanoramicaConjunto; }

	public String getFotoPanoramicaConjunto () { return fotoPanoramicaConjunto; }

	public void setComentarios(String comentarios) { this.comentarios = comentarios; }

	public String getComentarios () { return comentarios; }

	public void setOtrosDetalle(String otrosDetalle) { this.otrosDetalle = otrosDetalle; }

	public String getOtrosDetalle () { return otrosDetalle; }

	public void setCantPuertas(String cantPuertas) { this.cantPuertas = cantPuertas; }

	public String getCantPuertas () { return cantPuertas; }

	public String getNumTorres () { return numTorres; }

	public void setNumTorres(String numTorres) { this.numTorres = numTorres; }

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
