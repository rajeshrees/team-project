package com.esuate.matchpatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MySQLValidationService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean tableExists(String dbName, String tableName) {
        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, dbName, tableName);
        return count != null && count > 0;
    }

    public List<Map<String, String>> getRelations(String dbName, List<String> inputTables) {
        String query = """
        SELECT TABLE_NAME AS child_table, REFERENCED_TABLE_NAME AS parent_table
        FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
        WHERE TABLE_SCHEMA = ?
          AND REFERENCED_TABLE_NAME IS NOT NULL
    """;

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query, dbName);
        List<Map<String, String>> relations = new ArrayList<>();
        int counter = 1;

        // Normalize input table names to lower for comparison
        Set<String> normalizedInputTables = inputTables.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        for (Map<String, Object> row : result) {
            String child = ((String) row.get("child_table")).toLowerCase();
            String parent = ((String) row.get("parent_table")).toLowerCase();

            if (normalizedInputTables.contains(child) && normalizedInputTables.contains(parent)) {
                Map<String, String> rel = new HashMap<>();
                String relId = "R" + parent.charAt(6) + child.charAt(6);
                rel.put("id", relId.toUpperCase());
                rel.put("parent", (String) row.get("parent_table"));
                rel.put("child", (String) row.get("child_table"));
                rel.put("status", "NEWK");
                rel.put("usage", "I");
                relations.add(rel);
            }

        }

        return relations;
    }


}
