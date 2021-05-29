package util;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	static final String TAG = "DbHelper";
	static final String DB_NAME = "energizando_db";
	static final int DB_VERSION = 2;
	static Activity activity;
	
	public DbHelper(Activity act) {
		super(act.getApplicationContext(), DB_NAME, null, DB_VERSION);
		activity = act;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
			
	    db.execSQL("CREATE TABLE IF NOT EXISTS postes("
	    		+ "poste_id INTEGER PRIMARY KEY,"
	    		+ "created_at INTEGER, "
	    		+ "is_deleted INTEGER, "
	    		+ "updated_at INTEGER, "
				+ "manzana_id INTEGER,"
				+ "poste_idchar TEXT, "
				+ "nombre_calle TEXT, "
				+ "cruza TEXT, "
				+ "segmentocallecruce TEXT, "
				+ "altura TEXT, "
				+ "id_padvi INTEGER, "
				+ "ubicacion_id INTEGER, "
				+ "tipoposte_id INTEGER, "
				+ "lumina_id INTEGER, "
				+ "prop_id INTEGER, "
				+ "inclinacion_id INTEGER, "
				+ "cancable_id INTEGER, "
				+ "bajalibre_id INTEGER, "
				+ "equip_elecid INTEGER, "
				+ "altposte_id INTEGER, "
				+ "resistencia_posteid INTEGER, "
				+ "comentarios TEXT, "
				+ "fecha DATE, "
				+ "hora DATE, "
				+ "longitud TEXT, "
				+ "latitud TEXT, "
				+ "foto_panoramica TEXT, "
				+ "foto_acercamiento TEXT "
				+ ");");

		db.execSQL("CREATE TABLE IF NOT EXISTS predios("
				+ "predio_id INTEGER PRIMARY KEY,"
				+ "plano_detalle_id INTEGER, "
				+ "plano_id INTEGER, "
				+ "manzanaId INTEGER,"
				+ "nombrecalle TEXT,"
				+ "cruza TEXT,"
				+ "segmentocalle TEXT, "
				+ "altura TEXT, "
				+ "interior TEXT, "
				+ "pisos TEXT, "
				+ "cant_hp TEXT, "
				+ "canmedenergia TEXT, "
				+ "cantmedgas TEXT, "
				+ "numtimbres TEXT, "
				+ "tipopredioid TEXT, "
				+ "snencosnt TEXT, "
				+ "enconst_detalle TEXT, "
				+ "cantlocales TEXT, "
				+ "acceso_id INTEGER, "
				+ "poste_id INTEGER, "
				+ "nomconjunto TEXT, "
				+ "num_contacto TEXT, "
				+ "foto_panoramica TEXT, "
				+ "comentarios TEXT, "
				+ "otros_detalle TEXT, "
				+ "CantPuertas TEXT, "
				+ "created_at INTEGER, "
				+ "is_deleted INTEGER, "
				+ "updated_at INTEGER "
				+ ");");
	    
	    db.execSQL("CREATE TABLE IF NOT EXISTS planos("
	    		+ "plano_id INTEGER PRIMARY KEY,"
	    		+ "created_at INTEGER, "
				+ "cod_plano TEXT, "
				+ "zona TEXT, "
				+ "manzana TEXT, "
				+ "lote TEXT, "
				+ "cruza TEXT, "
				+ "segmento TEXT, "
				+ "altura TEXT, "
				+ "interior TEXT, "
				+ "tipouso TEXT, "
				+ "unid_pred_lote TEXT, "
				+ "max_pisos_lote TEXT, "
				+ "id_placa TEXT, "
				+ "id_lote TEXT, "
				+ "xmfId TEXT, "
	    		+ "is_deleted INTEGER, "
	    		+ "updated_at INTEGER "
	    		+ ");");
	    
	    db.execSQL("CREATE TABLE IF NOT EXISTS mst_ubicacion ("
				+ "mst_ubicacion_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

	    //db.execSQL();

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_rol ("
				+ "mst_rol_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_tipoPoste ("
				+ "mst_tipoposte_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_lumina ("
				+ "mst_lumina_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_prop ("
				+ "mst_prop_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_altPoste ("
				+ "mst_altPoste_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst__resistenciaPoste ("
				+ "mst__resistenciaPoste_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_equipEle ("
				+ "mst_equipEle_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_bajaLibre ("
				+ "mst_bajaLibre_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_bajaConst ("
				+ "mst_bajaConst_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_sino ("
				+ "mst_sino_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_tipoPredioId ("
				+ "mst_tipoPredioId_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

		db.execSQL("CREATE TABLE IF NOT EXISTS mst_accesoId ("
				+ "mst_accesoId_id INTEGER PRIMARY KEY, "
				+ "nombre TEXT"
				+");");

	    
	    db.execSQL("CREATE TABLE IF NOT EXISTS photos("
	    		+ "photo_id INTEGER PRIMARY KEY," //AUTOINCREMENT
	    		+ "created_at INTEGER,"
	    		+ "photo_url TEXT,"
	    		+ "store_id INTEGER,"
	    		+ "thumb_url TEXT,"
	    		+ "is_deleted INTEGER, "
	    		+ "updated_at INTEGER"
	    		+ ");");
	    
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
		db.execSQL("DROP TABLE IF EXISTS postes");
		db.execSQL("DROP TABLE IF EXISTS predios");
		db.execSQL("DROP TABLE IF EXISTS planos");
		db.execSQL("DROP TABLE IF EXISTS photos");
		
	}
}