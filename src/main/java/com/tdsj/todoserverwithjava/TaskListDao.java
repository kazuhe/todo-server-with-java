package com.tdsj.todoserverwithjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.tdsj.todoserverwithjava.HomeController.TaskItem;

@Service
public class TaskListDao {
    private final JdbcTemplate jdbcTemplate;

    /**
     * Spring Boot が application.properties の情報を元に DI してくれる
     * @param jdbcTemplate Spring JDBC に用意されている便利クラス
     */
    @Autowired
    TaskListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * タスクを登録する
     * @param taskItem タスク
     */
    public void add(TaskItem taskItem) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(taskItem);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tasklist");
        insert.execute(param);
    }

    /**
     * タスク一覧を返す
     * @return タスク一覧
     */
    public List<TaskItem> findAll() {
        String query = "SELECT * FROM tasklist";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<TaskItem> taskItems = result.stream()
                .map((Map<String, Object> row) -> new TaskItem(
                        row.get("id").toString(),
                        row.get("task").toString(),
                        row.get("deadline").toString(),
                        (Boolean)row.get("done")))
                .toList();
        return taskItems;
    }

    /**
     * タスクを削除する
     * @param id タスク ID
     * @return 更新されたレコードの行数
     */
    public int delete(String id) {
        int number = jdbcTemplate.update("DELETE FROM tasklist WHERE id = ?", id);
        return number;
    }

    /**
     * タスクを更新する
     * @param taskItem タスク
     * @return 更新されたレコードの行数
     */
    public int update(TaskItem taskItem) {
        int number = jdbcTemplate.update(
                "UPDATE tasklist SET task = ?, deadline = ?, done = ? WHERE id = ?",
                taskItem.task(),
                taskItem.deadline(),
                taskItem.done(),
                taskItem.id()
        );
        return number;
    }
}
