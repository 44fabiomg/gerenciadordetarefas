package com.example.gerenciadordetarefas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TarefaDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tarefa.db";
    private static final int DATABASE_VERSION = 1;

    public TarefaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TAREFA_TABLE = "CREATE TABLE Tarefa ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "descricao TEXT,"
                + "dataVencimento TEXT,"
                + "prioridade INTEGER,"
                + "categoria TEXT,"
                + "concluida INTEGER)";
        db.execSQL(CREATE_TAREFA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Tarefa");
        onCreate(db);
    }
}
