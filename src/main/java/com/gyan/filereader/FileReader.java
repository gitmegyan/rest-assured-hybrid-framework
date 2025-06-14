package com.gyan.filereader;

import java.io.File;

public interface FileReader {
    <T> T readFileAsList(File fileData, Class<?> returnType);
}
