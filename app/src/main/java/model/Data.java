package model;

import java.util.ArrayList;

public class Data {
	
	private ArrayList<Plano> planos;
	private ArrayList<Poste> postes;
	private ArrayList<Predio> predios;
	private ArrayList<Photo> photos;

	public void setPlanos(ArrayList<Plano> s) {
	    planos = s;
	}
	
	public ArrayList<Plano> getPlanos() {
	    return planos;
	}
	
	public void setPostes(ArrayList<Poste> s) {
		postes = s;
	}
	
	public ArrayList<Poste> getPostes() {
	    return postes;
	}

	public void SetPredios(ArrayList<Predio> s) { predios = s; }

	public ArrayList<Predio> getPredios() { return predios; }
	
	public void setPhotos(ArrayList<Photo> s) {
	    photos = s;
	}
	
	public ArrayList<Photo> getPhotos() {
	    return photos;
	}
}
