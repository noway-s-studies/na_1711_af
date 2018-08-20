package eu.artouch.todoapp;

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

public class TodoActivity extends AppCompatActivity {

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
        todos.add(new Todo("11. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("12. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("13. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("14. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("15. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("16. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("17. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("18. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("19. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("20. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("21. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("22. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("23. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("24. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("25. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("26. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("27. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("28. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("29. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("30. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("31. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("32. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("33. Virágok", "Meglocsolni", "Kertész"));
        todos.add(new Todo("34. Karácsonyi ajándékok", "Megvenni", "Én"));
        todos.add(new Todo("35. Előadás", "Megtartani", "Péter"));
        todos.add(new Todo("36. Virágok", "Meglocsolni", "Kertész"));

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new TodoAdapter(this, todos);

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
        }
        return super.onOptionsItemSelected(item);
    }

}
