package util;

import java.util.ArrayList;
import model.Poste;
import model.Predio;
import model.Photo;
import model.Plano;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Queries {
	
	private SQLiteDatabase db;
	private DbHelper dbHelper;
	
	
	public Queries(SQLiteDatabase db, DbHelper dbHelper) {
		this.db = db;
		this.dbHelper = dbHelper;
	}
	
	public void deleteTable(String tableName) {
		
		db = dbHelper.getWritableDatabase();
		try{
			db.delete(tableName, null, null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		db.close();
	}

	public void insertPredio(Predio entry) {
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("manzanaId", entry.getManzanaId());
		values.put("plano_detalle_id", entry.getPlano_detail_id());
		values.put("plano_id", entry.getPlano_id());
		values.put("cruza", entry.getCruza());
		values.put("nombrecalle", entry.getNombreCalle());
		values.put("otros_detalle", entry.getOtrosDetalle());
		values.put("CantPuertas", entry.getCantPuertas());
		values.put("tipopredioid", entry.getTipoPredioId());
		values.put("altura", entry.getAltura());
		values.put("interior", entry.getInterior());
		values.put("cantmedgas", entry.getCantMedGas());
		values.put("canmedenergia", entry.getCantMedEnergia());
		values.put("cant_hp", entry.getCant_HP());
		values.put("segmentocalle", entry.getSegmentoCalle());
		values.put("foto_panoramica", entry.getFotoPanoramicaConjunto());
		values.put("created_at", entry.getCreated_at());
		values.put("is_deleted", entry.getIs_deleted());
		values.put("predio_id", entry.getPredio_Id());
		values.put("updated_at", entry.getUpdated_at());
        values.put("comentarios", entry.getComentarios());
		db.insert("predios", null, values);
		db.close();
	}

	public void UpdatePredio(Predio entry) {

		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("manzanaId", entry.getManzanaId());
		values.put("plano_detalle_id", entry.getPlano_detail_id());
		values.put("plano_id", entry.getPlano_id());
		values.put("cruza", entry.getCruza());
		values.put("nombrecalle", entry.getNombreCalle());
		values.put("otros_detalle", entry.getOtrosDetalle());
		values.put("CantPuertas", entry.getCantPuertas());
		values.put("tipopredioid", entry.getTipoPredioId());
		values.put("altura", entry.getAltura());
		values.put("interior", entry.getInterior());
		values.put("cantmedgas", entry.getCantMedGas());
		values.put("canmedenergia", entry.getCantMedEnergia());
		values.put("cant_hp", entry.getCant_HP());
		values.put("segmentocalle", entry.getSegmentoCalle());
		values.put("foto_panoramica", entry.getFotoPanoramicaConjunto());
		values.put("created_at", entry.getCreated_at());
		values.put("is_deleted", entry.getIs_deleted());
		values.put("predio_id", entry.getPredio_Id());
		values.put("updated_at", entry.getUpdated_at());
		values.put("comentarios", entry.getComentarios());
		//db.insert("predios", null, values);
		db.update("predios", values, "predio_id = " + entry.getPredio_Id(), null);
		db.close();
	}
	
	public void insertPlano(Plano entry) {
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("cod_plano", entry.getCod_plano());
		values.put("zona", entry.getZona());
		values.put("manzana", entry.getManzana());
		values.put("lote", entry.getlote());
		values.put("cruza", entry.getCruza());
		values.put("segmento", entry.getSegmento());
		values.put("altura", entry.getAltura());
		values.put("tipouso", entry.getTipoUso());
		values.put("created_at", entry.getCreated_at());
		values.put("interior", entry.getInterior());
		values.put("unid_pred_lote", entry.getUnid_prediales_lote());
		values.put("max_pisos_lote", entry.getMaxPisosLote());
		values.put("is_deleted", entry.getIs_deleted());
		values.put("id_placa", entry.getId_placa());
		values.put("id_lote", entry.getId_lote());
		values.put("xmfId", entry.getXMFId());
		values.put("plano_id", entry.getId_plano());
		values.put("updated_at", entry.getUpdated_at());
		
		db.insert("planos", null, values);
		db.close();
	}
	
	public void updatePlano(Plano entry) {
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("cod_plano", entry.getCod_plano());
		values.put("zona", entry.getZona());
		values.put("manzana", entry.getManzana());
		values.put("lote", entry.getlote());
		values.put("cruza", entry.getCruza());
		values.put("segmento", entry.getSegmento());
		values.put("altura", entry.getAltura());
		values.put("tipouso", entry.getTipoUso());
		values.put("created_at", entry.getCreated_at());
		values.put("interior", entry.getInterior());
		values.put("unid_pred_lote", entry.getUnid_prediales_lote());
		values.put("max_pisos_lote", entry.getMaxPisosLote());
		values.put("is_deleted", entry.getIs_deleted());
		values.put("id_placa", entry.getId_placa());
		values.put("id_lote", entry.getId_lote());
		values.put("xmfId", entry.getXMFId());
		values.put("plano_id", entry.getId_plano());
		values.put("updated_at", entry.getUpdated_at());
		values.put("updated_at", entry.getUpdated_at());
		
		db.update("planos", values, "plano_id = " + entry.getId_plano(), null);
		db.close();
	}
	
	public void insertPoste(Poste entry) {
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nombre_calle", entry.getNombreCalle());
		values.put("poste_idchar", entry.getPosteIdChar());
		values.put("resistencia_posteid", entry.getResistenciaPosteId());
		values.put("manzana_id", entry.getManzana_id());
		values.put("lumina_id", entry.getLuminaId());
		values.put("plano_id", entry.getPlano_Id());
		values.put("cruza", entry.getCruza());
		values.put("segmentocallecruce", entry.getNombreCalle());
		values.put("id_padvi", entry.getIdpadvi());
		values.put("tipoposte_id", entry.getTipoposteiD());
		values.put("ubicacion_id", entry.getUbicacion_id());
		values.put("altura", entry.getAltura());
		values.put("prop_id", entry.getPropId());
		values.put("cancable_id", entry.getCantcableId());
		values.put("inclinacion_id", entry.getInclinacionId());
		values.put("bajalibre_id", entry.getBajalibreId());
		values.put("equip_elecid", entry.getEquipEleId());
		values.put("altposte_id", entry.getAltPosteId());
		values.put("comentarios", entry.getComentarios());
		values.put("fecha", entry.getFecha());
		values.put("hora", entry.getHora());
		values.put("longitud", entry.getLongitud());
		values.put("latitud", entry.getLatitud());
		values.put("foto_panoramica", entry.getFotoPanoramica());
		values.put("foto_acercamiento", entry.getFotoAcercamiento());
		values.put("foto_placa", entry.getFotoPlaca());
		values.put("created_at", entry.getCreated_at());
		values.put("is_deleted", entry.getIs_deleted());
		values.put("updated_at", entry.getUpdated_at());
		
		db.insert("postes", null, values);
		db.close();
	}


	
	public void insertPhoto(Photo entry) {
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("photo_url", entry.getPhoto_url());
		values.put("thumb_url", entry.getThumb_url());
		values.put("created_at", entry.getCreated_at());
		values.put("is_deleted", entry.getIs_deleted());
		values.put("photo_id", entry.getPhoto_id());
		values.put("poste_id", entry.getPoste_id());
		values.put("placa_id", entry.getPlaca_id());
		values.put("updated_at", entry.getUpdated_at());
		
		db.insert("photos", null, values);
		db.close();
	}

	
	public ArrayList<Predio> getPredios() {
		
		ArrayList<Predio> list = new ArrayList<Predio>();
		db = dbHelper.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM predios ORDER BY updated_at DESC", null);
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {
				
				Predio predios = formatPredios(mCursor);
				
				list.add(predios);
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}
	
	public Predio getPrediosByPredioId(int predioId) {
		
		Predio predios = null;
		db = dbHelper.getReadableDatabase();
		
		String sql = String.format("SELECT * FROM predios WHERE plano_id = %d", predioId);
		Cursor mCursor = db.rawQuery(sql, null); 
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                predios = formatPredios(mCursor);
				
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return predios;
	}
	
	
	public ArrayList<Plano> getPlanos() {
		
		ArrayList<Plano> list = new ArrayList<Plano>();
		db = dbHelper.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM planos", null);
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                Plano entry = formatPlano(mCursor);
				
				list.add(entry);
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}
	
	public ArrayList<Plano> getPlanoByManzanaId(int manzanaId) {
		
		ArrayList<Plano> list = new ArrayList<Plano>();
		db = dbHelper.getReadableDatabase();
		
		String sql = String.format("SELECT * FROM plano WHERE category_id = %d", manzanaId);
		Cursor mCursor = db.rawQuery(sql, null); 
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                Plano entry = formatPlano(mCursor);
				
				list.add(entry);
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}
	
	
	public Plano getPlanosByPlanoId(int planoId) {
		
		Plano entry = null;
		db = dbHelper.getReadableDatabase();
		
		String sql = String.format("SELECT * FROM planos WHERE plano_id = %d", planoId);
		Cursor mCursor = db.rawQuery(sql, null); 
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                entry = formatPlano(mCursor);
				
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return entry;
	}
	
	/*public ArrayList<Plano> getPlanoByUserId(String userId) {

		Plano entry = null;
		ArrayList<Plano> list = new ArrayList<Plano>();
		db = dbHelper.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM planos WHERE user_id = %d", userId);
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                entry = formatPlano(mCursor);
				
				list.add(entry);
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}*/
	
	public Photo getPhotoByPosteId(int posteId) {
		
		Photo entry = null;
		db = dbHelper.getReadableDatabase();
		
		String sql = String.format("SELECT * FROM photos WHERE poste_id = %d ORDER BY photo_id ASC", posteId);
		Cursor mCursor = db.rawQuery(sql, null); 
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                entry = formatPhoto(mCursor);
				
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return entry;
	}
	
	public ArrayList<Photo> getPhotosByPlacaId(int placaId) {
		
		ArrayList<Photo> list = new ArrayList<Photo>();
		db = dbHelper.getReadableDatabase();
		
		String sql = String.format("SELECT * FROM photos WHERE store_id = %d", placaId);
		Cursor mCursor = db.rawQuery(sql, null);  
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                Photo entry = formatPhoto(mCursor);
				
				list.add(entry);
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}
	
	
	public ArrayList<Poste> getPostes() {
		
		ArrayList<Poste> list = new ArrayList<Poste>();
		db = dbHelper.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM poste ORDER BY poste_id ASC", null);
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {
				
				Poste entry = formatPoste(mCursor);
				
				list.add(entry);
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}
	
	public ArrayList<Plano> getStoresFavorites() {
		
		ArrayList<Plano> list = new ArrayList<Plano>();
		db = dbHelper.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM planos INNER JOIN favorites ON stores.store_id = favorites.store_id ORDER BY stores.store_name", null);
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {
				
				Plano entry = formatPlano(mCursor);
				
				list.add(entry);
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}
	
	
	public ArrayList<String> getPosteNames() {
		
		ArrayList<String> list = new ArrayList<String>();
		db = dbHelper.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM postes ORDER BY poste_id ASC", null);
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {
				list.add(mCursor.getString( mCursor.getColumnIndex("poste_id")));
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return list;
	}
	
	
	public Poste getPosteByPoste(String poste_id) {
		
		Poste entry = null;
		db = dbHelper.getReadableDatabase();
		
		String sql = String.format("SELECT * FROM postes WHERE category = '%s' ORDER BY poste_id ASC", poste_id);
		Cursor mCursor = db.rawQuery(sql, null); 
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                entry = formatPoste(mCursor);
				
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return entry;
	}
	
	public Poste getPosteByPosteId(int posteId) {
		
		Poste entry = null;
		db = dbHelper.getReadableDatabase();
		
		String sql = String.format("SELECT * FROM postes WHERE plano_id = %d", posteId);
		Cursor mCursor = db.rawQuery(sql, null); 
		mCursor.moveToFirst();
		
		if (!mCursor.isAfterLast()) {
			do {

                entry = formatPoste(mCursor);
				
			} while (mCursor.moveToNext());
		}
		mCursor.close();
		dbHelper.close();

		return entry;
	}

    public Poste formatPoste(Cursor mCursor) {

        Poste entry = new Poste();

        entry.setPoste_id( mCursor.getInt( mCursor.getColumnIndex("poste_id")));
		entry.setNombreCalle( mCursor.getString( mCursor.getColumnIndex("nombre_calle")));
		entry.setPosteIdChar( mCursor.getString( mCursor.getColumnIndex("poste_idchar")));
		entry.setResistenciaPosteId( mCursor.getString( mCursor.getColumnIndex("resistencia_posteid")));
		entry.setManzana_id( mCursor.getString( mCursor.getColumnIndex("manzana_id")));
		entry.setLuminaId( mCursor.getString(mCursor.getColumnIndex("lumina_id")));
		entry.setPlano_Id(mCursor.getInt(mCursor.getColumnIndex("plano_id")));
		entry.setCruza( mCursor.getString( mCursor.getColumnIndex("cruza")));
		entry.setNombreCalle( mCursor.getString( mCursor.getColumnIndex("segmentocallecruce")));
		entry.setIdpadvi( mCursor.getString( mCursor.getColumnIndex("id_padvi")));
		entry.setTipoPosteId( mCursor.getString( mCursor.getColumnIndex("tipoposte_id")));
		entry.setUbicacionId( mCursor.getString( mCursor.getColumnIndex("ubicacion_id")));
		entry.setAltura( mCursor.getString( mCursor.getColumnIndex("altura")));
		entry.setPropId( mCursor.getString( mCursor.getColumnIndex("prop_id")));
		entry.setCantCableId( mCursor.getString( mCursor.getColumnIndex("cancable_id")));
		entry.setInclinacionId( mCursor.getString( mCursor.getColumnIndex("inclinacion_id")));
		entry.setBajalibreId( mCursor.getString( mCursor.getColumnIndex("bajalibre_id")));
		entry.setEquipEleId( mCursor.getString( mCursor.getColumnIndex("equip_elecid")));
		entry.setAltPosteId( mCursor.getString( mCursor.getColumnIndex("altposte_id" )));
		entry.setComentarios( mCursor.getString( mCursor.getColumnIndex("comentarios")));
		entry.setfecha( mCursor.getString( mCursor.getColumnIndex("fecha")));
		entry.setHora( mCursor.getString( mCursor.getColumnIndex("hora")));
		entry.setLongitud( mCursor.getString( mCursor.getColumnIndex("longitud")));
		entry.setLatitud( mCursor.getString( mCursor.getColumnIndex("latitud")));
		entry.setFotoPanoramica( mCursor.getString( mCursor.getColumnIndex("foto_panoramica")));
		entry.setFotoAcercamiento( mCursor.getString( mCursor.getColumnIndex("foto_acercamiento")));
		entry.setFotoPlaca( mCursor.getString( mCursor.getColumnIndex("foto_placa")));
        entry.setCreated_at( mCursor.getInt( mCursor.getColumnIndex("created_at")) );
        entry.setIs_deleted( mCursor.getInt( mCursor.getColumnIndex("is_deleted")) );
        entry.setUpdated_at( mCursor.getInt( mCursor.getColumnIndex("updated_at")) );

        return entry;
    }

    public Plano formatPlano(Cursor mCursor) {

        Plano entry = new Plano();
        entry.setId_plano( mCursor.getInt( mCursor.getColumnIndex("id_plano")));
        entry.setCod_plano( mCursor.getString(mCursor.getColumnIndex("cod_plano")));
        entry.setAltura( mCursor.getString( mCursor.getColumnIndex("altura")));
        entry.setCruza( mCursor.getString( mCursor.getColumnIndex("cruza")));
        entry.setId_lote( mCursor.getString( mCursor.getColumnIndex("id_lote")));
        entry.getManzana();
        entry.setMaxPisosLote( mCursor.getString( mCursor.getColumnIndex("max_pisos_lote")));
        entry.setUnid_prediales_lote( mCursor.getString( mCursor.getColumnIndex("unid_pred_lote")));
        entry.setSegmento( mCursor.getString( mCursor.getColumnIndex("segmento")));
        entry.setInterior( mCursor.getString( mCursor.getColumnIndex("interior")));
        entry.setTipoUso( mCursor.getString(mCursor.getColumnIndex("tipoUso")));
        entry.setId_placa( mCursor.getString( mCursor.getColumnIndex( "id_placa")));
        entry.setId_lote( mCursor.getString( mCursor.getColumnIndex( "id_lote")));
        entry.setXMFId( mCursor.getString( mCursor.getColumnIndex( "xmfId")));
        entry.setIs_deleted( mCursor.getInt( mCursor.getColumnIndex("is_deleted")) );
        entry.setCreated_at( mCursor.getInt( mCursor.getColumnIndex("created_at")));
        entry.setUpdated_at( mCursor.getInt( mCursor.getColumnIndex("updated_at")) );


        return entry;
    }

    public Photo formatPhoto(Cursor mCursor) {

        Photo entry = new Photo();
        entry.setCreated_at( mCursor.getInt( mCursor.getColumnIndex("created_at")) );
        entry.setIs_deleted( mCursor.getInt( mCursor.getColumnIndex("is_deleted")) );
        entry.setPhoto_id( mCursor.getInt( mCursor.getColumnIndex("photo_id")) );
        entry.setPhoto_url( mCursor.getString( mCursor.getColumnIndex("photo_url")) );
        entry.setPoste_id( mCursor.getInt( mCursor.getColumnIndex("poste_id")) );
		entry.setPlaca_id( mCursor.getInt( mCursor.getColumnIndex("placa_id")) );
        entry.setThumb_url( mCursor.getString( mCursor.getColumnIndex("thumb_url")) );
        entry.setUpdated_at( mCursor.getInt( mCursor.getColumnIndex("updated_at")) );

        return entry;
    }

    public Predio formatPredios(Cursor mCursor) {


        Predio predios = new Predio();
        predios.setCreated_at( mCursor.getInt( mCursor.getColumnIndex("created_at")) );
        predios.setIs_deleted( mCursor.getInt( mCursor.getColumnIndex("is_deleted")) );
		predios.setManzanaId( mCursor.getInt( mCursor.getColumnIndex("manzanaId")) );
		predios.setPlano_detail_id( mCursor.getInt( mCursor.getColumnIndex("plano_detalle_id" )));
		predios.setNombreCalle( mCursor.getString( mCursor.getColumnIndex("nombrecalle")));
		predios.setOtrosDetalle( mCursor.getString( mCursor.getColumnIndex("otros_detalle")));
		predios.setCantPuertas( mCursor.getString( mCursor.getColumnIndex("CantPuertas")));
		predios.setTipoPredioId(mCursor.getString( mCursor.getColumnIndex("tipopredioid")));
		predios.setAltura( mCursor.getString( mCursor.getColumnIndex("altura")));
		predios.setInterior( mCursor.getString( mCursor.getColumnIndex("interior")));
		predios.setCantMedGas( mCursor.getString( mCursor.getColumnIndex("cantmedgas")));
		predios.setCantMedEnergia( mCursor.getString( mCursor.getColumnIndex("canmedenergia")));
		predios.setCant_HP( mCursor.getString( mCursor.getColumnIndex("cant_hp")));
		predios.setSegmentoCalle( mCursor.getString( mCursor.getColumnIndex("segmentocalle")));
		predios.setFotoPanoramicaConjunto( mCursor.getString( mCursor.getColumnIndex("foto_panoramica")));
		predios.setPredio_Id( mCursor.getInt( mCursor.getColumnIndex("predio_id")));
		predios.setComentarios( mCursor.getString( mCursor.getColumnIndex("comentarios")));
        predios.setUpdated_at( mCursor.getInt( mCursor.getColumnIndex("updated_at")) );

        return predios;
    }
}
