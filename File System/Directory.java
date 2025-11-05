import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {
    String DirectoryName;
    List<FileSystem> objectList;

    public Directory(String directoryName) {
        DirectoryName = directoryName;
        this.objectList = new ArrayList<>();
    }

    public void add(FileSystem object) {
        this.objectList.add(object);
    }

    public void ls(String indent) {
        System.out.println(indent + DirectoryName + "/");
        for (FileSystem obj : objectList) {
            if (obj instanceof Directory) {
                ((Directory) obj).ls(indent + "   ");
            } else {
                obj.ls(indent + "   ");
            }
        }
    }
}
