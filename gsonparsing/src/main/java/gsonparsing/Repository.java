package gsonparsing;

class Repository {
    String full_name;
    String html_url;
    int forks;
    int watchers;


    @Override
    public String toString() {
        String out = "name: " + full_name + "\n";
        out += "url: " + html_url + '\n'; 
        out += "number of watchers: " + watchers + '\n';
        out += "number of forks: " + forks;
        return out;
    }
}