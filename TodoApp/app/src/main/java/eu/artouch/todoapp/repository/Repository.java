package eu.artouch.todoapp.repository;

import android.content.Context;

import java.util.List;

import eu.artouch.todoapp.model.Todo;

public interface Repository {
    void saveTodo(Todo todo);
    List<Todo> getAllTodo();
    void deleteTodo(Todo todo);
    void deleteAllTodo();
    void open(Context context);
    void close();
}
