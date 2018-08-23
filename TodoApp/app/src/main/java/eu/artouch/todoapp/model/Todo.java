package eu.artouch.todoapp.model;

public class Todo {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String titlr;
    private String description;

    public Todo(Long id, String titlr, String description, String assignee) {
        this.id = id;
        this.titlr = titlr;
        this.description = description;
        this.assignee = assignee;
    }

    private String assignee;

    public Todo() {
    }

    public Todo(String titlr, String description, String assignee) {
        this.titlr = titlr;
        this.description = description;
        this.assignee = assignee;
    }

    public String getTitle() {
        return titlr;
    }

    public void setTitlr(String titlr) {
        this.titlr = titlr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
