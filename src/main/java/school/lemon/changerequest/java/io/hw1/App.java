package school.lemon.changerequest.java.io.hw1;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created by lera on 22.01.17.
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        FileManager manager = new FileManager(".");


//        System.out.println(manager.ls());
//        System.out.println();
//        System.out.println(manager.ls(Order.BY_LAST_UPDATE_DESCENDING));
//        System.out.println();
//        System.out.println(manager.ls(Order.BY_LEXICOGRAPHICAL_DESCENDING, false));
//        System.out.println(manager.filter(Order.BY_LEXICOGRAPHICAL_DESCENDING, true, new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.getName().endsWith("gradle");
//            }
//        }));
//        manager.createFile("LICENSE");
//          manager.createFile("newFile");
//        manager.createDirectory("NEW_DIRECTORY");
//        manager.createDirectory("gradle");
//        manager.changeDirectory("test");
//        System.out.println(manager.currentDirectory());
//        manager.changeDirectory("LICENSE");
//        System.out.println(manager.currentDirectory());
//        System.out.println( manager.delete("test", false));
//        System.out.println( manager.delete("test", true));
//        manager.copy("fileFrom","fileTo" , CopyMode.SIMPLE);
 //       manager.copy("fileFrom","fileCOPYYY" , CopyMode.BUFFERED);
  //      manager.copy("fileFrom","fileCOPIPI", CopyMode.SIMPLE);
//        manager.copy("fileFrom","fileCOP", CopyMode.BUFFERED);
//        manager.copy("fileFrom","fileCOPPP", CopyMode.SIMPLE);
 //       manager.copy("fileFrom","fileFrom", CopyMode.SIMPLE);

     File ff = new File("newFile").getAbsoluteFile();
        System.out.println(manager.fileInfo(ff));

        File f1 = new File("README.md").getAbsoluteFile();
        System.out.println(manager.fileInfo(f1));

        File f2 = new File("gradle").getAbsoluteFile();
        System.out.println(manager.fileInfo(f2));
    }
}
