package br.com.tw.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter { 
	
			public static final String TABELA_ITENS = "itens";
			 
			public static final String COLUNA_ID_ITENS = "_id";
			public static final String COLUNA_DESCRICAO_ITENS= "descricaoItem";
			 
			public static final String TABELA_SUBITENS = "SUBITENS";
			 
			private static final String CREATE_TABLE_ITENS = "CREATE TABLE "
				+ TABELA_ITENS + "  (" + COLUNA_ID_ITENS
				+ " INTEGER, "
				+ COLUNA_DESCRICAO_ITENS + " text not null);";	
			 
			private static final String TAG = "DbAdapter";
			private DatabaseHelper mDbHelper;
			private SQLiteDatabase mDb;
			private Context mCtx = null;
			
			
			public static final String DB_NAME = "PCM_DATABASE";
			public final static int DATABASE_VERSION = 1;
			
			private static class DatabaseHelper extends SQLiteOpenHelper{
				
			
				
				@Override
				  public void onOpen(SQLiteDatabase db)
				  {
				    super.onOpen(db);
				    if (!db.isReadOnly())
				    {
				      db.execSQL("PRAGMA foreign_keys=ON;");
				    }
				  }
				
				public DatabaseHelper(Context context) {
					super(context, DB_NAME, null, DATABASE_VERSION);
					
				}
				@Override
				public void onCreate(SQLiteDatabase db) {
					db.execSQL(CREATE_TABLE_ITENS);
	
					Log.w("DbAdapter","DB criado com sucesso!");
				}
				@Override
				public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
						Log.w(TAG, "Atualizando o banco de dados da versão " + oldVersion
								+ " para " + newVersion
								+ ", todos os dados serão perdidos!");
						db.execSQL("DROP TABLE IF EXISTS " + TABELA_ITENS);
						onCreate(db);
				}
			}
			public DbAdapter(Context ctx) {
				this.mCtx = ctx;
			}
		 
			public DbAdapter open() throws SQLException {
				mDbHelper = new DatabaseHelper(mCtx);
				mDb = mDbHelper.getWritableDatabase();
				return this;
			}
		 
			public void close() {
				mDbHelper.close();
		                mDb.close();
			}
			
			public long criarItem(int id, String descricaoItem) {
				ContentValues values = new ContentValues();
				values.put(COLUNA_DESCRICAO_ITENS, descricaoItem);
				values.put(COLUNA_ID_ITENS, id);			
				return mDb.insert(TABELA_ITENS, null, values);
				
				
			}
			
			public boolean removerItem(long idItem) {
				 
				return mDb.delete(TABELA_ITENS, COLUNA_ID_ITENS+ "=?",
					new String[] { String.valueOf(idItem) }) > 0;
			}
				
			public Cursor consultarTodosItens() {
				 
				return mDb.query(TABELA_ITENS, new String[] { COLUNA_ID_ITENS,
						COLUNA_DESCRICAO_ITENS }, null, null, null, null, null);
			}
			
			public Cursor consultarItem(String descricaoItem) throws SQLException {
				 
				Cursor mCursor =
			 
				mDb.query(true, TABELA_ITENS, new String[] { COLUNA_ID_ITENS,
					COLUNA_DESCRICAO_ITENS }, COLUNA_DESCRICAO_ITENS + "=?",
					new String[] { descricaoItem }, null, null, null,
					null);
				if (mCursor != null) {
					mCursor.moveToFirst();
				}
				return mCursor;
			 
			}
			public Cursor consultarItemId(long idItem) throws SQLException {
				 
				Cursor mCursor =
			 
				mDb.query(true, TABELA_ITENS, new String[] { COLUNA_ID_ITENS,
					COLUNA_DESCRICAO_ITENS }, COLUNA_ID_ITENS + "=?",
					new String[] { String.valueOf(idItem) }, null, null, null,
					null);
				if (mCursor != null) {
					mCursor.moveToFirst();
				}
				return mCursor;
			 
			}
		
	}


