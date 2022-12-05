package com.learning.springboot3todoapplication.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "table_items")
public class TodoItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Boolean isComplete;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public String toString(){
        return String.format("ToDo-Item {id=%d, description='%s', isComplete='%s', createdAt='%s', updatedAt='%s'}",
                id, description, isComplete, createdAt, updatedAt);
    }
}
