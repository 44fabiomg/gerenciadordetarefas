package com.example.gerenciadordetarefas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private SQLiteDatabase db;
    private TarefaDBHelper dbHelper;

    public TarefaDAO(Context context) {
        dbHelper = new TarefaDBHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createTarefa(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put("descricao", tarefa.getDescricao());
        values.put("dataVencimento", tarefa.getDataVencimento());
        values.put("prioridade", tarefa.getPrioridade());
        values.put("categoria", tarefa.getCategoria());
        values.put("concluida", tarefa.isConcluida());
        return db.insert("Tarefa", null, values);
    }

    public List<Tarefa> getAllTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        Cursor cursor = db.query("Tarefa", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Tarefa tarefa = new Tarefa(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5) > 0
                );
                tarefas.add(tarefa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tarefas;
    }

    public Tarefa getTarefaById(long id) {
        Cursor cursor = db.query("Tarefa", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Tarefa tarefa = new Tarefa(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5) > 0
            );
            cursor.close();
            return tarefa;
        }
        return null;
    }

    public void updateTarefa(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put("descricao", tarefa.getDescricao());
        values.put("dataVencimento", tarefa.getDataVencimento());
        values.put("prioridade", tarefa.getPrioridade());
        values.put("categoria", tarefa.getCategoria());
        values.put("concluida", tarefa.isConcluida());
        db.update("Tarefa", values, "id = ?", new String[]{String.valueOf(tarefa.getId())});
    }

    public void deleteTarefa(long id) {
        db.delete("Tarefa", "id = ?", new String[]{String.valueOf(id)});
    }
}
