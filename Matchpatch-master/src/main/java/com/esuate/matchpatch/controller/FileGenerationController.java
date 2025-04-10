package com.esuate.matchpatch.controller;

import com.esuate.matchpatch.service.TxtFileGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileGenerationController {

    @Autowired
    private TxtFileGeneratorService fileGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateFile(@RequestBody Map<String, Object> request) {
        try {
            String schema = (String) request.get("schema");
            String directory = (String) request.get("directory");
            String database = (String) request.get("database");
            List<String> tables = (List<String>) request.get("tables");

            String fileName = fileGeneratorService.generateFullFile(schema, directory, database, tables);
            return ResponseEntity.ok("File generated: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
