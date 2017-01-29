package school.lemon.changerequest.java.io.hw1;

import school.lemon.changerequest.java.io.hw1.exception.*;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileManager implements IFileManager {

    private File currentDirectory;

    public FileManager(String path) {
        currentDirectory = new File(path);
        if (!currentDirectory.isDirectory()) {
            throw new IllegalArgumentException("Please specify directory!");
        }
    }

    @Override
    public String ls() {
        return ls(Order.BY_LEXICOGRAPHICAL_ASCENDING);
    }

    @Override
    public String ls(Order order) {
        return ls(order, true);
    }

    @Override
    public String ls(Order order, boolean directoriesFirst) {
        File[] files = currentDirectory.listFiles();
        Arrays.sort(files, new FileComparator(order, directoriesFirst));
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            sb.append(file.getName()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<File> filter(Order order, boolean directoriesFirst, FileFilter fileFilter) {
        File[] files = currentDirectory.listFiles(fileFilter);
        Arrays.sort(files, new FileComparator(order, directoriesFirst));
        List<File> fileList = new ArrayList<>();
        for (File file : files) {
            fileList.add(file);
        }

        return fileList;
    }

    @Override
    public String fileInfo(File file) {
        final long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String lastMod = sdf.format(new Date(lastModified));
        long size;
        if (file.isDirectory()) {
            size = 0;
        } else {
            size = file.length();
        }
        StringBuilder name = new StringBuilder(file.getName());
        if (file.isDirectory()) name.append("/");
        String fileInfo = String.format("%-20s | %15d | %s", lastMod, size, name);
        return fileInfo;
    }

    @Override
    public void createFile(String name) throws FileAlreadyExistException, FileManagerException {
        try {
            File file = new File(name);
            if (file.exists()) throw new FileAlreadyExistException(file);
            file.createNewFile();
            System.out.println("File has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createDirectory(String name) throws DirectoryAlreadyExistException, FileManagerException {
        File directory = new File(name);
        if (directory.exists() && directory.isDirectory()) throw new DirectoryAlreadyExistException(directory);
        try {
            if (directory.mkdir()) System.out.println("Directory has been created. " + directory.getAbsolutePath());
        } catch (FileManagerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeDirectory(String name) throws IllegalDirectoryException {
        File newCurrentDirectory = new File(name).getAbsoluteFile();
        if (!newCurrentDirectory.exists() || !newCurrentDirectory.isDirectory())
            throw new IllegalDirectoryException(newCurrentDirectory);
        currentDirectory = newCurrentDirectory;
        System.out.println("Current directory has been changed.");
    }

    @Override
    public boolean delete(String name) throws FileNotFoundException {
        boolean delete = delete(name, true);
        return delete;
    }

    @Override
    public boolean delete(String name, boolean force) throws FileNotFoundException {
        File fileToDelete = new File(name).getAbsoluteFile();
        if (!fileToDelete.exists()) throw new FileNotFoundException(name);
        if (fileToDelete.isFile() || fileToDelete.list().length == 0) {
            fileToDelete.delete();
            return true;
        } else {
            if (force == false) return false;
            File[] files = fileToDelete.listFiles();
            for (File f : files) {
                f.delete();
            }
            if (fileToDelete.list().length == 0) {
                fileToDelete.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public void copy(String source, String dest, CopyMode copyMode)
            throws FileNotFoundException, IllegalArgumentException, FileAlreadyExistException, FileCopyException {
        File fileSource = new File(source).getAbsoluteFile();
        if (!fileSource.exists()) throw new FileNotFoundException(source);
        if (fileSource.isDirectory()) throw new IllegalArgumentException(source);
        File fileDest = new File(dest);
        if (fileDest.exists()) throw new FileAlreadyExistException(fileDest);
        try {
            if (copyMode.equals(CopyMode.SIMPLE)) {
                Files.copy(fileSource.toPath(), fileDest.toPath());
                System.out.println("Copied successfully.");
            } else {
                InputStream is = null;
                OutputStream os = null;
                is = new FileInputStream(fileSource);
                os = new FileOutputStream(fileDest);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("Copied successfully.");
            }
        } catch (FileCopyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void copy(String source, String dest)
            throws FileNotFoundException, IllegalArgumentException, FileAlreadyExistException, FileCopyException {
        copy(source, dest, CopyMode.BUFFERED);
    }

    @Override
    public File currentDirectory() {
        return currentDirectory;
    }
}
