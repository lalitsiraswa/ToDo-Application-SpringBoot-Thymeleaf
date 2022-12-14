package com.learning.springboot3todoapplication.controllers;
import com.learning.springboot3todoapplication.models.TodoItem;
import com.learning.springboot3todoapplication.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class TodoFormController {
    @Autowired
    private TodoItemService todoItemService;
    @GetMapping("/create-todo")
    public String showCreateForm(Model model){
        TodoItem todoItem = new TodoItem();
        model.addAttribute("todoItem", todoItem);
        return "new-todo-item";
    }
    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model){
        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setDescription(todoItem.getDescription());
        newTodoItem.setIsComplete(todoItem.getIsComplete());
        todoItemService.save(newTodoItem);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model){
//        TodoItem item;
//        Optional<TodoItem> todoItem = todoItemService.getById(id);
//        if(todoItem.isPresent()){
//            item = todoItem.get();
//        }
        TodoItem item = todoItemService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("ToDo-Item id : " + id + " not found!"));
        todoItemService.delete(item);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        TodoItem item = todoItemService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("ToDo-Item id : " + id + " not found!"));
        model.addAttribute("todo", item);
        return "edit-todo-item";
    }
    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem item, BindingResult result, Model model){
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("ToDo-Item id : " + id + " not found!"));
        todoItem.setDescription(item.getDescription());
        todoItem.setIsComplete(item.getIsComplete());
        todoItemService.save(todoItem);
        return "redirect:/";
    }
}
