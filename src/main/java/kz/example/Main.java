package kz.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void createBackup(String sourceDirPath) {
        File sourceDir = new File(sourceDirPath);
        File backupDir = new File("./backup");


        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.out.println("Исходная директория не существует или недоступна: " + sourceDirPath);
            return;
        }


        if (!backupDir.exists()) {
            if (backupDir.mkdir()) {
                System.out.println("Папка './backup' успешно создана.");
            } else {
                System.out.println("Не удалось создать папку './backup'.");
                return;
            }
        }


        File[] files = sourceDir.listFiles((file) -> file.isFile());
        if (files == null || files.length == 0) {
            System.out.println("В исходной директории нет файлов для резервного копирования.");
            return;
        }

        for (File file : files) {
            Path sourcePath = file.toPath();
            Path destinationPath = backupDir.toPath().resolve(file.getName());
            try {
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Скопирован файл: " + file.getName());
            } catch (IOException e) {
                System.out.println("Ошибка при копировании файла: " + file.getName());
                e.printStackTrace();
            }
        }

        System.out.println("Резервное копирование завершено.");
    }

    public static void main(String[] args) {

        String sourceDirectory = "./";
        createBackup(sourceDirectory);
    }
}
