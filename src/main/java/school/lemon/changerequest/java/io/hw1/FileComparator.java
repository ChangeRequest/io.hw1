package school.lemon.changerequest.java.io.hw1;

import school.lemon.changerequest.java.io.hw1.Order;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<File> {

    private Order order;
    private boolean directoriesFirst;

    public FileComparator(Order order, boolean directoriesFirst) {
        this.order = order;
        this.directoriesFirst = directoriesFirst;
    }

    @Override
    public int compare(File o1, File o2) {
        if (directoriesFirst && (o1.isDirectory() ^ o2.isDirectory())) {
            return Boolean.compare(o2.isDirectory(), o1.isDirectory());
        }
        switch (order) {
            case BY_LEXICOGRAPHICAL_ASCENDING:
                return o1.getName().compareTo(o2.getName());
            case BY_LEXICOGRAPHICAL_DESCENDING:
                return o2.getName().compareTo(o1.getName());
            case BY_LAST_UPDATE_ASCENDING:
                return Long.compare(o1.lastModified(), o2.lastModified());
            default:
                return Long.compare(o2.lastModified(), o1.lastModified());
        }
    }

}
