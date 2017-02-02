package school.lemon.changerequest.java.io.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Demo {
    public static void main(String[] args) {
        String str = "D:\\newFolder";
        File fileCreate = new File(str);
        fileCreate.mkdir();
        IFileManager iFileManager = new IFileManagerImp(str);
        String tmp = null;

        for (char iI = 'a'; iI <= 'c'; iI++) {
            tmp = str + "\\" + iI + ".txt";
            iFileManager.createFile(tmp);
            System.out.println("File created!");
        }

        for (char iI = 'c'; iI >= 'a'; iI--) {
            tmp = str + "\\" + iI + "\\";
            iFileManager.createDirectory(tmp);
            System.out.println("Directory  created!");
        }

        System.out.println("________________________________________");
        System.out.println(iFileManager.ls(Order.BY_LEXICOGRAPHICAL_ASCENDING));

        System.out.println("________________________________________");
        System.out.println(iFileManager.ls(Order.BY_LEXICOGRAPHICAL_DESCENDING, false));

        System.out.println("________________________________________");
        System.out.println(iFileManager.ls(Order.BY_LAST_UPDATE_ASCENDING, false));

        System.out.println("________________________________________");
        System.out.println(iFileManager.ls(Order.BY_LAST_UPDATE_DESCENDING));

        System.out.println("________________________________________");
        File testFile = new File("D:\\newFolder\\a");
        File testFile2 = new File("D:\\newFolder\\a.txt");
        System.out.println(iFileManager.fileInfo(testFile));

        System.out.println("________________________________________");
        System.out.println(iFileManager.fileInfo(testFile2));

        System.out.println("________________________________________");
        try {
            File file = new File("D:\\newFolder\\a.txt");
            FileWriter fw = new FileWriter(file);
            fw.write("Text just for Demo");
            fw.close();
            System.out.println("Changes in a.txt done");
        } catch (IOException n) {
        }
        try {
            iFileManager.copy("D:\\newFolder\\a.txt", "D:\\newFolder\\aCopy.txt");
            System.out.println("Copied");
        } catch (FileNotFoundException n) {
        }

        System.out.println(iFileManager.fileInfo(testFile2));

        System.out.println("________________________________________");
        String str2 = "D:\\newFolder\\a";
        iFileManager.changeDirectory(str2);
        for (char iI = 'a'; iI <= 'c'; iI++) {
            tmp = str2 + "\\" + iI + ".txt";
            iFileManager.createFile(tmp);
        }
        System.out.println("________________________________________");
        try {
            iFileManager.delete("D:\\newFolder\\a\\a.txt");
            iFileManager.delete("D:\\newFolder\\a\\b.txt");
            iFileManager.delete("D:\\newFolder\\a\\c.txt");
            System.out.println("Files Deleted");
        } catch (FileNotFoundException n) {
        }
        System.out.println("________________________________________");
        try {
            iFileManager.delete("D:\\newFolder\\c");
            System.out.println("Folder c Deleted");
        } catch (FileNotFoundException n) {
        }
    }


}






