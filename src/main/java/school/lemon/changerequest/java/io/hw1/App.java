package school.lemon.changerequest.java.io.hw1;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

/**
 * Created by lera on 22.01.17.
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        FileManager manager = new FileManager(".");

        System.out.println(manager.ls());
        System.out.println();
        System.out.println(manager.ls(Order.BY_LAST_UPDATE_DESCENDING));
        System.out.println();
        System.out.println(manager.ls(Order.BY_LEXICOGRAPHICAL_DESCENDING, false));
        System.out.println(manager.filter(Order.BY_LEXICOGRAPHICAL_DESCENDING, true, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith("gradle");
            }
        }));

        System.out.println("Try to create file, that already exist.");
        manager.createFile("LICENSE");

        System.out.println("Create new file.");
        manager.createFile("newFile");

        System.out.println("Try to create directory, that already exist.");
        manager.createDirectory("gradle");

        System.out.println("Create new directory.");
        manager.createDirectory("test");

        System.out.println("Try to invoke method changeDirectory for file.");
        manager.changeDirectory("newFile");

        System.out.println("Try to invoke method changeDirectory for non-existent directory.");
        manager.changeDirectory("nonExistentDirectory");

        System.out.println("Try to copy directory.");
        manager.copy("test","testCopy" , CopyMode.SIMPLE);

        System.out.println("Try to copy non-existent file.");
        manager.copy("nonExistentFile","fileCopy" , CopyMode.SIMPLE);

        System.out.println("Try to copy into file, that already exist.");
        manager.copy("LICENSE","newFile" , CopyMode.SIMPLE);

        manager.copy("LICENSE","fileTo" , CopyMode.SIMPLE);

        manager.copy("fileTo","fileCOPY" , CopyMode.BUFFERED);

        manager.changeDirectory("test");
        System.out.println(manager.currentDirectory());

       System.out.println(manager.delete("fileCopy", false));
       System.out.println(manager.delete("test", true));

        System.out.println("Try to delete directory, that not empty");
        System.out.println(manager.delete("build", false));

        System.out.println("Delete directory, that not empty");
        System.out.println(manager.delete("directoryWIthFiles", true));


        File file = new File("newFile").getAbsoluteFile();
        System.out.println(manager.fileInfo(file));

        File file1 = new File("README.md").getAbsoluteFile();
        System.out.println(manager.fileInfo(file1));


        manager.changeDirectory("test");
        System.out.println(manager.currentDirectory());
    }
}
