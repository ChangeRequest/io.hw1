package school.lemon.changerequest.java.io.hw1;


import school.lemon.changerequest.java.io.hw1.exception.*;

import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class IFileManagerImp implements IFileManager {
    FileFilter fileFilter = null;
    String path;
    File folder;
    File[] listOfFiles;
    List<File> Files;
    boolean directoriesFirst = true;
    FileFilter filter;
    StringBuilder sb;

    public boolean isDirectoriesFirst() {
        return directoriesFirst;
    }

    public void setDirectoriesFirst(boolean directoriesFirst) {
        this.directoriesFirst = directoriesFirst;
    }

    public IFileManagerImp(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;

    }

    @Override
    public String ls() {
        sb = new StringBuilder();
        folder = new File(getPath());
        listOfFiles = folder.listFiles(fileFilter);
        directoriesFirst = true;
        if (directoriesFirst = true) {
            Arrays.sort(listOfFiles, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory())
                        return o2.isDirectory() ? o1.compareTo(o2) : -1;
                    else if (o2.isDirectory())
                        return 1;
                    return o1.compareTo(o2);
                }
            });
            for (File file : listOfFiles) {
                sb.append(file.getName());
                sb.append(" ");
            }

        }
        return sb.toString();
    }


    @Override
    public String ls(Order order) {
        sb = new StringBuilder();
        directoriesFirst = true;
        if (directoriesFirst = true) {
            switch (order) {
                case BY_LEXICOGRAPHICAL_ASCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    if (directoriesFirst = true) {
                        Arrays.sort(listOfFiles, new Comparator<File>() {
                            @Override
                            public int compare(File o1, File o2) {
                                if (o1.isDirectory())
                                    return o2.isDirectory() ? o1.compareTo(o2) : -1;
                                else if (o2.isDirectory())
                                    return 1;
                                return o1.compareTo(o2);
                            }
                        });
                        for (File file : listOfFiles) {
                            sb.append(file.getName());
                            sb.append(" ");
                        }

                    }
                    return sb.toString();

                case BY_LEXICOGRAPHICAL_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            if (o1.isDirectory())
                                return o2.isDirectory() ? String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName()) : -1;
                            else if (o2.isDirectory())
                                return 1;
                            return String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName());
                        }
                    });
                    for (File file : listOfFiles) {
                        sb.append(file.getName());
                        sb.append(" ");
                    }
                    return sb.toString();

                case BY_LAST_UPDATE_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            if (o1.isDirectory())

                                return o2.isDirectory() ? Long.valueOf(o1.lastModified()).compareTo(o2.lastModified()) : -1;
                            else if (o2.isDirectory())
                                return 1;
                            return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
                        }
                    });
                    for (File file : listOfFiles) {
                        sb.append(file.getName());
                        sb.append(" ");
                    }
                    return sb.toString();

                case BY_LAST_UPDATE_ASCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            if (o1.isDirectory())

                                return o2.isDirectory() ? Long.valueOf(o2.lastModified()).compareTo(o1.lastModified()) : -1;
                            else if (o2.isDirectory())
                                return 1;
                            return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
                        }
                    });

                    for (File file : listOfFiles) {
                        sb.append(file.getName());
                        sb.append(" ");
                    }
                    return sb.toString();
            }
        }
        return null;
    }


    @Override
    public String ls(Order order, boolean directoriesFirst) {
        sb = new StringBuilder();
        this.directoriesFirst = directoriesFirst;
        if (directoriesFirst == true) {
            ls(order);
        } else {
            switch (order) {
                case BY_LEXICOGRAPHICAL_ASCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
                        }
                    });
                    for (File file : listOfFiles) {
                        sb.append(file.getName());
                        sb.append(" ");
                    }
                    return sb.toString();
                case BY_LEXICOGRAPHICAL_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName());
                        }
                    });
                    for (File file : listOfFiles) {
                        sb.append(file.getName());
                        sb.append(" ");
                    }
                    return sb.toString();


                case BY_LAST_UPDATE_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
                        }
                    });
                    for (File file : listOfFiles) {
                        sb.append(file.getName());
                        sb.append(" ");
                    }
                    return sb.toString();
                case BY_LAST_UPDATE_ASCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
                        }
                    });
                    for (File file : listOfFiles) {
                        sb.append(file.getName());
                        sb.append(" ");
                    }
                    return sb.toString();
            }
        }
        return null;
    }


    @Override
    public List<File> filter(Order order, boolean directoriesFirst, FileFilter fileFilter) {
        List<File> result = new ArrayList<>();
        this.filter = fileFilter;
        this.directoriesFirst = directoriesFirst;

        if (directoriesFirst == true) {
            switch (order) {
                case BY_LEXICOGRAPHICAL_ASCENDING:
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            if (o1.isDirectory())
                                return o2.isDirectory() ? o1.compareTo(o2) : -1;
                            else if (o2.isDirectory())
                                return 1;
                            return o1.compareTo(o2);
                        }
                    });
                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;

                case BY_LEXICOGRAPHICAL_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            if (o1.isDirectory())
                                return o2.isDirectory() ? String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName()) : -1;
                            else if (o2.isDirectory())
                                return 1;
                            return String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName());
                        }
                    });
                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;

                case BY_LAST_UPDATE_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            if (o1.isDirectory())

                                return o2.isDirectory() ? Long.valueOf(o1.lastModified()).compareTo(o2.lastModified()) : -1;
                            else if (o2.isDirectory())
                                return 1;
                            return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
                        }
                    });
                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;

                case BY_LAST_UPDATE_ASCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            if (o1.isDirectory())

                                return o2.isDirectory() ? Long.valueOf(o2.lastModified()).compareTo(o1.lastModified()) : -1;
                            else if (o2.isDirectory())
                                return 1;
                            return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
                        }
                    });

                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;


            }
        } else {
            switch (order) {
                case BY_LEXICOGRAPHICAL_ASCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
                        }
                    });
                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;
                case BY_LEXICOGRAPHICAL_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName());
                        }
                    });
                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;


                case BY_LAST_UPDATE_DESCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
                        }
                    });
                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;
                case BY_LAST_UPDATE_ASCENDING:
                    folder = new File(getPath());
                    listOfFiles = folder.listFiles(fileFilter);
                    Arrays.sort(listOfFiles, new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
                        }
                    });
                    for (File file : listOfFiles) {
                        result.add(file);
                    }
                    return result;
            }
        }
        return null;
    }

    @Override
    public String fileInfo(File file) {
        String divider = "|";
        long size;
        String str2;

        if (file.isDirectory()) {
            size = 0;
        } else {
            size = file.length();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String str1 = sdf.format(file.lastModified());
        if (file.isDirectory()) str2 = file.getName() + "/";
        else str2 = file.getName();

        return String.format("%-20s %15s %s %n", str1 + divider, size + divider, str2);
    }

    /**
     * Create new file within current directory with given name
     *
     * @param name name of new file
     * @throws FileAlreadyExistException if file or directory with given name already exists
     * @throws FileManagerException      if any other problem happened
     */
    @Override
    public void createFile(String name) throws FileAlreadyExistException, FileManagerException {
        File file = new File(name);

        try {
            if (file.isDirectory()) {
                throw new IOException();
            }
            boolean fcnf = file.createNewFile();
            if (fcnf) {
                System.out.println("File has been created successfully");
            } else {
                throw new FileAlreadyExistException(file);
            }
        } catch (FileAlreadyExistException n) {
            System.out.println(n.getMessage());
        } catch (FileManagerException n) {
            System.out.println(n.getMessage());
        } catch (IOException e) {
            System.out.println("Not File");
        }
    }


    @Override
    public void createDirectory(String name) throws DirectoryAlreadyExistException, FileManagerException {
        File file = new File(name);
        try {
            if (file.isDirectory()) {
                throw new IOException();
            }
            boolean fcnf = file.mkdir();
            if (fcnf) {
                System.out.println("Directory has been created successfully");
            } else {
                throw new DirectoryAlreadyExistException( file);
            }
        } catch (DirectoryAlreadyExistException n) {
            System.out.println(n.getMessage());
        } catch (FileManagerException n) {
            System.out.println(n.getMessage());
        } catch (IOException e) {
            System.out.println("Not Directory");
        }

    }

    @Override
    public void changeDirectory(String name) throws IllegalDirectoryException {

        try {
            Paths.get(name);
            setPath(name);
            System.out.println("Changed Directory");
        } catch (IllegalDirectoryException ide) {
            System.out.println(ide.getMessage());
        }
    }

    @Override
    public boolean delete(String name) throws FileNotFoundException {
        try
        {
            File file = new File(name);
            if(file.exists())
            {
                file.delete();
                return true;
            }
            else
            {
                throw new FileNotFoundException();
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        return false;
    }

    /**
     * Delete file or directory with a given name.
     * If force == true delete directory even if it is not empty.
     *
     * @param name  file or directory name to delete
     * @param force should directory be removed even if it is not empty?
     * @return true if file or directory was deleted
     * @throws FileNotFoundException if file or directory could not be found
     */
    @Override
    public boolean delete(String name, boolean force) throws FileNotFoundException {
        try
        {
            File file = new File(name);
            if(file.exists())
            {
                if(file.isDirectory()&&file.length()>0&&force)
                {
                    file.delete();
                    return true;
                }
                else if(file.isDirectory()&&file.length()>0&&force) return false;
            }
            else
            {
                throw new FileNotFoundException();
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        return false;
    }




    /**
     * Copy file from source to dest using copyMode.
     *
     * @param source   source file to copy
     * @param dest     destination file to copy
     * @param copyMode copyMode
     * @throws FileNotFoundException     if source file doesn't exist
     * @throws IllegalArgumentException  if source file is directory
     * @throws FileAlreadyExistException if dest file already exist
     * @throws FileCopyException         if any exception happened during copying
     * @see CopyMode
     */
    @Override
    public void copy(String source, String dest, CopyMode copyMode) throws FileNotFoundException, IllegalArgumentException, FileAlreadyExistException, FileCopyException {
        switch (copyMode) {


            case SIMPLE:  try {
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
                System.out.println("File copied successfully! Using Simple Mode");
            } catch (FileNotFoundException | IllegalArgumentException | FileAlreadyExistException n) {
                System.out.println(n.getMessage());
            } catch (IOException n) {
            }
            break;
            case BUFFERED:

                    copy(source, dest);

                System.out.println("File copied successfully! Using Simple Buffered Mode");
break;
        }
    }

    @Override
    public void copy(String source, String dest) throws FileNotFoundException, IllegalArgumentException, FileAlreadyExistException, FileCopyException {
        try {
            File sourceFile= new File(source);
            File destFile= new File(dest);
            if(!(sourceFile.exists())) throw new FileNotFoundException();
            if(sourceFile.isDirectory())throw new IllegalArgumentException();
            if(destFile.exists()) throw new FileAlreadyExistException(destFile);
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
        }catch (FileNotFoundException|IllegalArgumentException|FileAlreadyExistException n ){
            System.out.println(n.getMessage());
        }
        catch (IOException n) {
        }
    }



    /**
     * Return current directory
     *
     * @return current directory
     */
    @Override
    public File currentDirectory() {



        return null;
    }


}



