public class File implements FileSystem {
    String name;
    String size;

    public File(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public void ls(String indent) {
        System.out.println(indent + "- " + name + " (" + size + ")");
    }
}
