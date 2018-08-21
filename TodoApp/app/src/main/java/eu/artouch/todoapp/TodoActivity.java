package eu.artouch.todoapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import eu.artouch.todoapp.adapter.TodoAdapter;
import eu.artouch.todoapp.model.Todo;

public class TodoActivity extends AppCompatActivity implements TodoAdapter.OnItemLongClickListener {

    public static final int REQUEST_CODE = 111;
    private List<Todo> todos;
    private TodoAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        todos = new ArrayList<>();
        todos.add(new Todo("01. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("02. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("03. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("04. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("05. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("06. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("07. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("08. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("09. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("10. Karácsonyi ajándékok", "Megvenni", "Én"));

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new TodoAdapter(this, todos, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_todo,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.removeAll) {
            todos.clear();
            adapter.notifyDataSetChanged();
        } else if(item.getItemId()==R.id.createTodoMenu){
            Intent intent = new Intent(this, CreateTodoActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE) {
            if (resultCode==RESULT_OK) {
                String title = data.getStringExtra(CreateTodoActivity.KEY_TITLE);
                String description = data.getStringExtra(CreateTodoActivity.KEY_DESCRIPTION);
                String assignee = data.getStringExtra(CreateTodoActivity.KEY_ASSIGNEE);
                Todo todo = new Todo(title,description,assignee);
                todos.add(todo);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemLongClick(int position) {
        todos.remove(position);
        adapter.notifyDataSetChanged();
    }
}
