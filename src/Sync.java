import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Sync {

    private ArrayList<File> fileArrayList;
    private ArrayList<String> op;
    private ArrayList<String> path;

    public Sync() {
       op=new ArrayList<>();
        path=new ArrayList<>();
       fileArrayList=new ArrayList<>();
    }

    public ArrayList<File> getFileArrayList() {
        return fileArrayList;
    }

    public ArrayList<String> getOp() {
        return op;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setFileArrayList(ArrayList<File> fileArrayList) {
        this.fileArrayList = fileArrayList;
    }

    public void setOp(ArrayList<String> op) {
        this.op = op;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }
}
