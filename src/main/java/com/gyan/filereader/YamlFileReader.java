package com.gyan.filereader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

@Slf4j
public class YamlFileReader implements FileReader {

    private ObjectMapper objectMapper;

    public YamlFileReader() {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.findAndRegisterModules();
    }

    public <T> T readFileAsList(File fileData, Class<?> responseClass) {
        var typeFactory = objectMapper.getTypeFactory();
        var collectionType = typeFactory.constructCollectionType(List.class, responseClass);
        T result = null;
        try {
            result = objectMapper.readValue(fileData, collectionType);
        } catch (Exception e) {
            log.error("Error reading file -> " + e.getMessage());
        }
        return result;
    }
}
