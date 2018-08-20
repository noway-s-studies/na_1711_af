package eu.artouch.todoapp.model;

public class Todo {
    private String titlr;
    private String description;
    private String assignee;

    public Todo() {
    }

    public Todo(String titlr, String description, String assignee) {
        this.titlr = titlr;
        this.description = description;
        this.assignee = assignee;
    }

    public String getTitlr() {
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
