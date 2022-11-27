package com.tdsj.todoserverwithjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    private final TaskListDao dao;

    @Autowired
    HomeController(TaskListDao dao) {
        this.dao = dao;
    }

    /**
     * タスク
     * @param id ID
     * @param task タスク内容
     * @param deadline 期限
     * @param done 完了状態
     */
    record TaskItem(String id, String task, String deadline, boolean done) {}
//    private List<TaskItem> taskItems = new ArrayList<>();

    @GetMapping("/add")
    String addItem(@RequestParam("task") String task,
                   @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        dao.add(item);
        return "redirect:/list";
    }

    /**
     * タスク一覧を返す
     * @return html
     */
    @GetMapping("/list")
    String listItems(Model model) {
        List<TaskItem> taskItems = dao.findAll();
        model.addAttribute("taskList", taskItems);
        return "home";
    }

    /**
     * タスクを削除する
     * @param id タスク ID
     * @return html
     */
    @GetMapping("/delete")
    String deleteItem(@RequestParam("id") String id) {
        dao.delete(id);
        return "redirect:/list";
    }

    /**
     * タスクを更新する
     * @param id タスク ID
     * @param task タスク内容
     * @param deadline 期限
     * @param done 完了状態
     * @return html
     */
    @GetMapping("/update")
    String updateItem(@RequestParam("id") String id,
                      @RequestParam("task") String task,
                      @RequestParam("deadline") String deadline,
                      @RequestParam("done") boolean done) {
        TaskItem taskItem = new TaskItem(id, task, deadline, done);
        dao.update(taskItem);
        return "redirect:/list";
    }
}
