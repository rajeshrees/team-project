package com.esuate.matchpatch.service;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TxtFileGeneratorService {

    private final VelocityEngine velocityEngine;

    @Autowired
    private MySQLValidationService validationService;

    public TxtFileGeneratorService() {
        this.velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

    public String generateFullFile(String schema, String directory, String database, List<String> tables) throws Exception {
        // Normalize table names to upper case for comparison
        List<String> normalizedTables = tables.stream().map(String::toUpperCase).collect(Collectors.toList());

        // Validate tables exist
        for (String table : normalizedTables) {
            if (!validationService.tableExists(database, table)) {
                throw new Exception("Table does not exist: " + table);
            }
        }

        // Fetch relationships
        List<Map<String, String>> relationships = validationService.getRelations(database, normalizedTables);

        if (relationships.isEmpty() && normalizedTables.size() > 1) {
            throw new Exception("No valid relationships found between the given tables.");
        }

        String startTable = normalizedTables.get(0);

        VelocityContext context = new VelocityContext();
        context.put("schema", schema);
        context.put("directory", directory);
        context.put("database", database);
        context.put("startTable", startTable);
        context.put("tables", normalizedTables);
        context.put("relationships", relationships);

        Template template = velocityEngine.getTemplate("templates/template.vm");

        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Write to file
        String fileName = "generated_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".txt";
        Path filePath = Paths.get("generated", fileName);
        Files.createDirectories(filePath.getParent());
        try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
            fileWriter.write(writer.toString());
        }

        return filePath.toString();
    }
}
