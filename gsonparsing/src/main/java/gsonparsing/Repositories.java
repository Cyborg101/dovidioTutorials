package gsonparsing;
import java.util.List;

public class Repositories {
    List<Repository> items;

    @Override
    public String toString() {
        String out = "Number of repositories: " + items.size();
        for (Repository r : items) {
            out += "{\n" + r.toString() + "\n}\n";
        }
        return out;
    }
}