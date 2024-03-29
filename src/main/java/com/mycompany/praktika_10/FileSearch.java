package com.mycompany.praktika_10;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileSearch {

    public static void main(String[] args) {
        String directoryPath;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter directory: ");
            directoryPath = scanner.nextLine();
            // Закрываем сканнер после использования
        }

        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Does not exist.");
            return;
        }

        List<String> fileList = new ArrayList<>();
        searchFiles(directory, "", fileList);

        Collections.sort(fileList);
        for (String fileInfo : fileList) {
            System.out.println(fileInfo);
        }
    }

    public static void searchFiles(File dir, String prefix, List<String> fileList) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileList.add(prefix + file.getName() + File.separator);
                    searchFiles(file, prefix + "\t", fileList);
                } else {
                    fileList.add(prefix + file.getName() + " - " + file.length() + " bytes");
                }
            }
        }
    }
}