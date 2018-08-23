package eu.artouch.todoapp.repository.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eu.artouch.todoapp.model.Todo;
import eu.artouch.todoapp.repository.Repository;

public class SQLiteRepository implements Repository {

    private SQLiteDatabase mydb;
    private DBHelper dbHelper;

    @Override
    public void saveTodo(Todo todo) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.TODO_MODEL.TITLE,todo.getTitle());
        values.put(DBConstants.TODO_MODEL.ASSIGNEE,todo.getAssignee());
        values.put(DBConstants.TODO_MODEL.DESCRIPTION,todo.getDescription());
        mydb.insert(DBConstants.TABLE_TODO,null,values);
    }

    @Override
    public List<Todo> getAllTodo() {

        Cursor cursor =mydb.query(DBConstants.TABLE_TODO,
                new String[]{
                        DBConstants.TODO_MODEL.ID,
                        DBConstants.TODO_MODEL.TITLE,
                        DBConstants.TODO_MODEL.DESCRIPTION,
                        DBConstants.TODO_MODEL.ASSIGNEE
                },null,null,null,null,DBConstants.TODO_MODEL.ID);
        List<Todo> todosLoaded = new ArrayList<>(cursor.getCount());
        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(DBConstants.TODO_MODEL.ID));
            String title = cursor.getString(cursor.getColumnIndex(DBConstants.TODO_MODEL.TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DBConstants.TODO_MODEL.DESCRIPTION));
            String assignee = cursor.getString(cursor.getColumnIndex(DBConstants.TODO_MODEL.ASSIGNEE));
            Todo todo = new Todo(id,title,description,assignee);
            todosLoaded.add(todo);
        }
        cursor.close();
        return todosLoaded;
    }

    @Override
    public void deleteTodo(Todo todo) {
        Long id=todo.getId();
        if (id != null) {
            mydb.delete(DBConstants.TABLE_TODO,DBConstants.TODO_MODEL.ID + "=" + todo.getId(),null);
        }
    }

    @Override
    public void deleteAllTodo() {
        mydb.execSQL(DBConstants.DROP_TABLE);
    }

    @Override
    public void open(Context context) {
        dbHelper = new DBHelper(context);
        mydb = dbHelper.getWritableDatabase();
        dbHelper.onCreate(mydb);
    }

    @Override
    public void close() {
        dbHelper.close();
    }
}
