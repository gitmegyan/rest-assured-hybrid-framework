package com.gyan.dataprovider;

import com.gyan.filereader.FileReader;
import com.gyan.filereader.YamlFileReader;
import com.gyan.model.TestDataModel;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Slf4j
public class TestCaseProvider {

    private final String TEST_CASE_PATH = "src/test/resources/testcases/";
    private FileReader fileReader = new YamlFileReader();

    @DataProvider(parallel = true)
    public Object[][] testcaseProvider() {
        final List<File> list = readAllTestcaseFileInADirectory();
        final Map<String, TestDataModel> testcaseMap = new HashMap<>();
        for(int i =0; i<list.size(); i++) {
            List<TestDataModel> testcases = fileReader.readFileAsList(list.get(i), TestDataModel.class);
            testcases.forEach(test -> testcaseMap.put(test.getTestcaseId(), test));
        }
        final Object[][] tests = new Object[testcaseMap.size()][1];
        final AtomicInteger i = new AtomicInteger();
        testcaseMap.forEach((key, testdata) -> {
            final int index = i.getAndIncrement();
            tests[index][0] = testdata;
        });
        return tests;
    }



    private List<File> readAllTestcaseFileInADirectory() {
        var dirPath = Paths.get(TEST_CASE_PATH);
        List<File> listOfFiles = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(dirPath, 1)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".yml") || path.toString().endsWith(".yaml"))
                    .forEach(path -> {
                        listOfFiles.add(path.toFile());
                    });
            return  listOfFiles;
        } catch (IOException e) {
            log.error("Error accessing directory: " + dirPath);
            e.printStackTrace();
            return null;
        }
    }
}
