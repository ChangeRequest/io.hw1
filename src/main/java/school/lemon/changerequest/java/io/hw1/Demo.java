package school.lemon.changerequest.java.io.hw1;

import java.io.*;


public class Demo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("src");
        sb.append(File.separator);
        sb.append("test");
        sb.append(File.separator);
        sb.append("java");
        sb.append(File.separator);
        sb.append("school");
        sb.append(File.separator);
        sb.append("lemon");
        sb.append(File.separator);
        sb.append("changerequest");
        sb.append(File.separator);
        sb.append("java");
        sb.append(File.separator);
        sb.append("io");
        sb.append(File.separator);
        sb.append("hw1");
        sb.append(File.separator);
        sb.append("testFolder");
        String str = sb.toString();
        String tmp = "";
        File folderCreate = new File(str);

        if (!folderCreate.exists()) {
            folderCreate.mkdir();
        }
        IFileManager iFileManager = new IFileManagerImp(str);


        for (char iI = 'a'; iI <= 'c'; iI++) {
            tmp = str + File.separator + iI + ".txt";
            File fileCreate = new File(tmp);
            if (!fileCreate.exists()) {
                iFileManager.createFile(tmp);

            } else System.out.println(iI + ".txt Already exists!");
        }
        for (char iI = 'c'; iI >= 'a'; iI--) {

            tmp = str + File.separator + iI + File.separator;
            File fileCreate = new File(tmp);
            if (!fileCreate.exists()) {

                iFileManager.createDirectory(tmp);

            } else System.out.println(File.separator + iI + File.separator + "Already exists!");
        }
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };
        System.out.println("________________________________________");
        System.out.println(iFileManager.ls(Order.BY_LEXICOGRAPHICAL_ASCENDING, true));
        System.out.println("________________________________________");
        System.out.println(iFileManager.ls(Order.BY_LEXICOGRAPHICAL_DESCENDING, true));
        str = sb.toString()+ File.separator+"a";
        iFileManager.changeDirectory(str);
        for (char iI = 'a'; iI <= 'c'; iI++) {
            tmp = str + File.separator + iI + ".txt";
            File fileCreate = new File(tmp);
            if (!fileCreate.exists()) {
                iFileManager.createFile(tmp);

            } else System.out.println(iI + ".txt Already exists!");
        }
        str = sb.toString()+File.separator+"b";
//Change Directory+create some more files
        for (char iI = 'a'; iI <= 'c'; iI++) {
            tmp = str + File.separator + iI + ".txt";
            File fileCreate = new File(tmp);
            if (!fileCreate.exists()) {
                iFileManager.createFile(tmp);

            } else System.out.println(iI + ".txt Already exists!");
        }
        iFileManager.changeDirectory(str);

        try {
            // try toDelete not empty folder;
            System.out.println(iFileManager.delete(str));
            System.out.println(iFileManager.delete(str, true));
        }catch (FileNotFoundException n){}

        String source=sb.toString()+File.separator+"a.txt";
        String dest=sb.toString()+File.separator+"d.txt";
        String dest2=sb.toString()+File.separator+"f.txt";
        //add some text to a.txt
        try {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(source));
bufferedWriter.write("Some Text");
 bufferedWriter.flush();
}catch (IOException io){
    System.out.println(io.getMessage());
}
try {
    //Copy 2 ways

    iFileManager.copy(source,dest);
    iFileManager.copy(source,dest2,CopyMode.BUFFERED);
}catch (FileNotFoundException fnf){}




    }


}



