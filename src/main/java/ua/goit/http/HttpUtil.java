package ua.goit.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();


    public static User createNewObject(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public static User updateObject(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public static int deleteObject(URI uri) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .DELETE()
                .build();
        final HttpResponse<String> send = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return send.statusCode();
    }

    public static void lastPostComments(URI uriPost, URI uriComments) throws IOException, InterruptedException {
        Post post = lastPostId(uriPost);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriComments)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Comments> comments = GSON.fromJson(response.body(), new TypeToken<List<Comments>>(){}.getType());
//        List<Comments> commentsResult = comments.stream()                           ** response returned all comments id with 0
//                .filter((comments1) -> comments1.getPostID() == post.getId())       ** this part check user post id and comments id, if returning will be working correct
//                .collect(Collectors.toList());
        String filename = "user-" + post.getUserId() + "-post-" + post.getId() + "-comments.json";
        try(FileWriter writer = new FileWriter(filename)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(comments);
            writer.write(json);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static Post lastPostId(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Post> posts = GSON.fromJson(response.body(), new TypeToken<List<Post>>(){}.getType());
        return posts.get(posts.size()-1);
    }

    public static List<Todos> openedTask(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Todos> todos = GSON.fromJson(response.body(), new TypeToken<List<Todos>>(){}.getType());
        return todos.stream()
                .filter(el -> !el.isCompleted())
                .collect(Collectors.toList());
    }

}
