package gsonparsing;
import java.io.*;
import java.net.*;
import com.google.gson.Gson;


public class GithubSearch {

    private static final String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    private static final String QUERY_CHAR = "q";
    private static final String SORT = "sort";
    private static final String BYSTARS = "stars";

    public static void main(String[] args) {
        if (args.length != 1) {
            String response = "Attenzione! \n";
            response += "Utilizzo: java GithubSearch <nomedellaRepository>";
            System.out.println(response);
            System.exit(1);
        }

        String query = args[0];
        String requestUrl = buildRequestUrlString(query);

        try {
            String result = getHTML(requestUrl);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Errore nella richiesta");
            System.out.println(e.toString());
        }


    }

    private static String buildRequestUrlString(String query) {
        return GITHUB_BASE_URL + "?q=" + query + '&' + SORT + '=' + BYSTARS;
    }

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Gson gson = new Gson();
        Repositories repositories = null;
        repositories = gson.fromJson(rd, Repositories.class);
        
        return repositories.toString();
   }
}