package school.lemon.changerequest.java.io.hw1;


import school.lemon.changerequest.java.io.hw1.exception.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class IFileManagerImp implements IFileManager {


    File currentDirectory;

    boolean directoriesFirst = true;


    public IFileManagerImp(String path) {

        this.currentDirectory = new File(path);
    }

    public IFileManagerImp() {
        this.currentDirectory = new File(System.getProperty("user.dir"));
    }

    @Override
    public String ls() {
        return FilesInfo(filter(Order.BY_LEXICOGRAPHICAL_ASCENDING, true, fileFilterForAll()));
    }

    @Override
    public String ls(Order order) {
        return FilesInfo(filter(order, true, fileFilterForAll()));
    }

    @Override
    public String ls(Order order, boolean directoriesFirst) {

        return FilesInfo(filter(order, directoriesFirst, fileFilterForAll()));
    }

    @Override
    public List<File> filter(Order order, boolean directoriesFirst, FileFilter fileFilter) {
        File[] paths;
        paths = currentDirectory().listFiles(fileFilter);
        List<File> result = new ArrayList<>();
        if (directoriesFirst == true) {
            switch (order) {
                case BY_LEXICOGRAPHICAL_ASCENDING:
                    Arrays.sort(paths, comparatorForLexAscending);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
                case BY_LEXICOGRAPHICAL_DESCENDING:
                    Arrays.sort(paths, comparatorForLexDescending);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
                case BY_LAST_UPDATE_DESCENDING:
                    Arrays.sort(paths, comparatorForLastUpdateDescending);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
                case BY_LAST_UPDATE_ASCENDING:
                    Arrays.sort(paths, comparatorForLastUpdateAscending);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
            }
        } else {
            switch (order) {
                case BY_LEXICOGRAPHICAL_ASCENDING:
                    Arrays.sort(paths, comparatorForLexAscendingNoOrder);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
                case BY_LEXICOGRAPHICAL_DESCENDING:
                    Arrays.sort(paths, comparatorForLexDescendingNoOrder);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
                case BY_LAST_UPDATE_DESCENDING:
                    Arrays.sort(paths, comparatorForLastUpdateDescendingNoOrder);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
                case BY_LAST_UPDATE_ASCENDING:
                    Arrays.sort(paths, comparatorForLastUpdateAscendingNoOrder);
                    for (File file : paths) {
                        result.add(file);
                    }
                    break;
            }
        }
        return result;
    }

    @Override
    public String fileInfo(File file) {
        if (!file.exists()) {
            return null;
        }
        long size;
        String fileName;
        if (file.isDirectory()) {
            size = 0;
        } else {
            size = file.length();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String lastModifiedDate = sdf.format(file.lastModified());
        if (file.isDirectory()) fileName = file.getName() + File.separator;
        else fileName = file.getName();
        return String.format("%-20s|%15s|%s %n", lastModifiedDate, size, fileName);
    }

    @Override
    public void createFile(String name) throws FileAlreadyExistException, FileManagerException {
        File file = new File(name);
        if (file.exists()) throw new FileAlreadyExistException(file);
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        if (!file.exists()) throw new FileManagerException();
    }


    @Override
    public void createDirectory(String name) throws DirectoryAlreadyExistException, FileManagerException {
        File file = new File(name);
        if (file.exists()) throw new DirectoryAlreadyExistException(file);
        file.mkdir();
        if (!file.exists()) throw new FileManagerException();
    }

    @Override
    public void changeDirectory(String name) throws IllegalDirectoryException {
        File file = new File(name);
        if (!file.isDirectory()) {
            throw new IllegalDirectoryException(file);
        }
        setCurrentDirectory(name);
    }

    @Override
    public boolean delete(String name) throws FileNotFoundException {
        File file = new File(name);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        if (file.isDirectory()) {
            if (file.list().length > 0) return false;
        }
        file.delete();
        return true;

    }


    @Override
    public boolean delete(String name, boolean force) throws FileNotFoundException {
        File[] files = currentDirectory.listFiles();
        File file = new File(name);
        if (!force) {
            delete(name);
        } else {
            for (File f : files) {
                f.delete();
            }
        }
        file.delete();
        return true;
    }

    @Override
    public void copy(String source, String dest, CopyMode copyMode) throws
            FileNotFoundException, IllegalArgumentException, FileAlreadyExistException, FileCopyException {
        switch (copyMode) {
            case SIMPLE:
                try {
                    File sourceFile = new File(source);
                    File destFile = new File(dest);
                    if (!(sourceFile.exists())) throw new FileNotFoundException();
                    if (sourceFile.isDirectory()) throw new IllegalArgumentException();
                    if (destFile.exists()) throw new FileAlreadyExistException(destFile);
                    else destFile.createNewFile();
                    FileInputStream fin = new FileInputStream(source);
                    FileOutputStream fout = new FileOutputStream(dest);
                    int i;
                    do {
                        i = fin.read();
                        if (i != -1)
                            fout.write(i);
                    }
                    while (i != -1);
                    fin.close();
                    fout.close();
                } catch (IOException n) {
                }
                break;
            case BUFFERED:
                copy(source, dest);
                break;
        }
    }

    @Override
    public void copy(String source, String dest) throws
            FileNotFoundException, IllegalArgumentException, FileAlreadyExistException, FileCopyException {
        try {
            File sourceFile = new File(source);
            File destFile = new File(dest);
            if (!(sourceFile.exists())) throw new FileNotFoundException();
            if (sourceFile.isDirectory()) throw new IllegalArgumentException();
            if (destFile.exists()) throw new FileAlreadyExistException(destFile);
            else destFile.createNewFile();
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(dest));
            int i;
            do {
                i = fin.read();
                if (i != -1)
                    fout.write(i);
            }
            while (i != -1);
            fin.close();
            fout.close();
            System.out.println("File copied successfully!!");
        } catch (IOException n) {
        }
    }


    @Override
    public File currentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String newPath) {
        this.currentDirectory = new File(newPath);

    }

    private String FilesInfo(List<File> files) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            sb.append(fileInfo(files.get(i)));
        }
        return sb.toString();

    }

    private FileFilter fileFilterForAll() {
        FileFilter fileFilterForAll = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        return fileFilterForAll;
    }


    Comparator comparatorForLexAscending = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            if (o1.isDirectory())
                return o2.isDirectory() ? o1.compareTo(o2) : -1;
            else if (o2.isDirectory())
                return 1;
            return o1.compareTo(o2);
        }

    };
    Comparator comparatorForLexAscendingNoOrder = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
        }
    };
    Comparator comparatorForLexDescending = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            if (o1.isDirectory())
                return o2.isDirectory() ? o2.compareTo(o1) : -1;
            else if (o2.isDirectory())
                return 1;
            return o2.compareTo(o1);
        }
    };
    Comparator comparatorForLexDescendingNoOrder = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName());
        }
    };
    Comparator comparatorForLastUpdateDescending = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            if (o1.isDirectory())
                return o2.isDirectory() ? Long.valueOf(o1.lastModified()).compareTo(o2.lastModified()) : -1;
            else if (o2.isDirectory())
                return 1;
            return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
        }
    };
    Comparator comparatorForLastUpdateDescendingNoOrder = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
        }
    };
    Comparator comparatorForLastUpdateAscending = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            if (o1.isDirectory())
                return o2.isDirectory() ? Long.valueOf(o2.lastModified()).compareTo(o1.lastModified()) : -1;
            else if (o2.isDirectory())
                return 1;
            return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
        }
    };
    Comparator comparatorForLastUpdateAscendingNoOrder = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
        }
    };

}