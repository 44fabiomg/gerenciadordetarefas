package com.example.gerenciadordetarefas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TarefaDAO tarefaDAO;
    private List<Tarefa> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        FloatingActionButton fab = findViewById(R.id.fab);

        tarefaDAO = new TarefaDAO(this);
        tarefaDAO.open();

        // Adicionar uma tarefa de teste
        addDummyData();
        refreshTarefaList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = tarefas.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioTarefaActivity.class);
                intent.putExtra("tarefaId", tarefa.getId());
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormularioTarefaActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshTarefaList();
    }

    private void refreshTarefaList() {
        tarefas = tarefaDAO.getAllTarefas();
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        listView.setAdapter(adapter);
    }

    private void addDummyData() {
        // Adicionar dados fictícios para testar
        Tarefa tarefa = new Tarefa(0, "Tarefa de Teste", "2024-12-31", 1, "Categoria de Teste", false);
        tarefaDAO.createTarefa(tarefa);
    }

    @Override
    protected void onDestroy() {
        tarefaDAO.close();
        super.onDestroy();
    }
}
