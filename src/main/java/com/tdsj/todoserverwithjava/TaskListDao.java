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
     * DB にタスクを登録する
     * @param taskItem タスク
     */
    public void add(TaskItem taskItem) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(taskItem);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tasklist");
        insert.execute(param);
    }

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
}
