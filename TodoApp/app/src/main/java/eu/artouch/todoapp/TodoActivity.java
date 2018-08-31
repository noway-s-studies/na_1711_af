package eu.artouch.todoapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import eu.artouch.todoapp.adapter.TodoAdapter;
import eu.artouch.todoapp.model.Todo;
import eu.artouch.todoapp.receiver.BatteryReceiver;

public class TodoActivity extends AppCompatActivity implements TodoAdapter.OnItemLongClickListener {

    public static final int REQUEST_CODE = 111;
    private List<Todo> todos;
    private TodoAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private BatteryReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        todos = new ArrayList<>();


        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new TodoAdapter(this, todos, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(decoration);

        loadTodos();

        batteryReceiver = new BatteryReceiver();
    }

    private void loadTodos() {
        FirebaseDatabase.getInstance().getReference().child("todos")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Todo todo = dataSnapshot.getValue(Todo.class);
                        todos.add(todo);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        Todo todo = dataSnapshot.getValue(Todo.class);
                        todos.remove(todo);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void savedTodos(final Todo todo) {
        String key = FirebaseDatabase.getInstance().getReference()
                .child("todos").push().getKey();
        todo.setKey(key);
        FirebaseDatabase.getInstance().getReference()
                .child("todos")
                .child(key)
                .setValue(todo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("Firebase", "Todo Mentése sikeres");
            }
        });
    }

    private void deleteTodo(final Todo todo) {
        FirebaseDatabase.getInstance().getReference().child("todos").child(todo.getKey())
                .removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Log.d("Firebase", "Sikeres Todo törlés!");
                    }
                });
    }

    private void deleteAll() {
        FirebaseDatabase.getInstance().getReference().child("todoe").removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Log.d("Firebase", "Az összes Todo elem sikeresen törölve!");
            }
        });
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
            deleteAll();
        } else if(item.getItemId()==R.id.createTodoMenu){
            Intent intent = new Intent(this, CreateTodoActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else if(item.getItemId()==R.id.logoutMenu){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(TodoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

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
                savedTodos(todo);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemLongClick(int position) {
        Todo todo=todos.get(position);
        deleteTodo(todo);
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
