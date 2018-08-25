package eu.artouch.todoapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import eu.artouch.todoapp.adapter.TodoAdapter;
import eu.artouch.todoapp.model.Todo;
import eu.artouch.todoapp.receiver.BatteryReceiver;
import eu.artouch.todoapp.repository.Repository;
import eu.artouch.todoapp.repository.sqlite.SQLiteRepository;

public class TodoActivity extends AppCompatActivity implements TodoAdapter.OnItemLongClickListener {

    public static final int REQUEST_CODE = 111;
    private List<Todo> todos;
    private TodoAdapter adapter;
    private Repository repository;
    private LinearLayoutManager linearLayoutManager;
    private BatteryReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        repository = new SQLiteRepository();
        todos = new ArrayList<>();


        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new TodoAdapter(this, todos, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(decoration);

        loadTodosAsync();

        batteryReceiver = new BatteryReceiver();
    }

    private void loadTodosAsync() {
        Thread loadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                repository.open(TodoActivity.this);
                final List<Todo> todosLoaded = repository.getAllTodo();
                repository.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        todos.clear();
                        todos.addAll(todosLoaded);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        loadThread.start();
    }

    private void savedTodosAsync(final Todo todo) {
        Thread saveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                repository.open(TodoActivity.this);
                repository.saveTodo(todo);
                repository.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadTodosAsync();
                    }
                });
            }
        });
        saveThread.start();
    }

    private void deleteTodoAsync(final Todo todo) {
        Thread saveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                repository.open(TodoActivity.this);
                repository.deleteTodo(todo);
                repository.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadTodosAsync();
                    }
                });
            }
        });
        saveThread.start();
    }

    private void deleteAllAsync() {
        Thread saveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                repository.open(TodoActivity.this);
                repository.deleteAllTodo();
                repository.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadTodosAsync();
                    }
                });
            }
        });
        saveThread.start();
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
            deleteAllAsync();
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
                savedTodosAsync(todo);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemLongClick(int position) {
        Todo todo=todos.get(position);
        deleteTodoAsync(todo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        intentFilter.addAction("android.intent.action.BATTERY_OKAY");
        registerReceiver(batteryReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(batteryReceiver);
        super.onPause();
    }
}
