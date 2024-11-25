package com.example.gerenciadordetarefas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioTarefaActivity extends AppCompatActivity {
    private EditText edtDescricao, edtDataVencimento, edtPrioridade, edtCategoria;
    private Switch swtConcluida;
    private Button btnSalvar, btnExcluir;

    private TarefaDAO tarefaDAO;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tarefa);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtDataVencimento = findViewById(R.id.edtDataVencimento);
        edtPrioridade = findViewById(R.id.edtPrioridade);
        edtCategoria = findViewById(R.id.edtCategoria);
        swtConcluida = findViewById(R.id.swtConcluida);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);

        tarefaDAO = new TarefaDAO(this);
        tarefaDAO.open();

        long tarefaId = getIntent().getLongExtra("tarefaId", -1);
        if (tarefaId != -1) {
            tarefa = tarefaDAO.getTarefaById(tarefaId);
            if (tarefa != null) {
                edtDescricao.setText(tarefa.getDescricao());
                edtDataVencimento.setText(tarefa.getDataVencimento());
                edtPrioridade.setText(String.valueOf(tarefa.getPrioridade()));
                edtCategoria.setText(tarefa.getCategoria());
                swtConcluida.setChecked(tarefa.isConcluida());
            }
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tarefa == null) {
                    tarefa = new Tarefa(0, "", "", 0, "", false);
                }
                tarefa.setDescricao(edtDescricao.getText().toString());
                tarefa.setDataVencimento(edtDataVencimento.getText().toString());
                tarefa.setPrioridade(Integer.parseInt(edtPrioridade.getText().toString()));
                tarefa.setCategoria(edtCategoria.getText().toString());
                tarefa.setConcluida(swtConcluida.isChecked());

                if (tarefa.getId() == 0) {
                    tarefaDAO.createTarefa(tarefa);
                } else {
                    tarefaDAO.updateTarefa(tarefa);
                }
                finish();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tarefa != null) {
                    tarefaDAO.deleteTarefa(tarefa.getId());
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        tarefaDAO.close();
        super.onDestroy();
    }
}
