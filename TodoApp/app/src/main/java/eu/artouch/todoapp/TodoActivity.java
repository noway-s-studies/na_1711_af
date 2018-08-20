package eu.artouch.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eu.artouch.todoapp.model.Todo;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("Virágok", "Meglocsolni", "Kertész"));
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

    }
}
