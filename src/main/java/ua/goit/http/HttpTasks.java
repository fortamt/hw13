package ua.goit.http;

import java.io.IOException;
import java.net.URI;

public class HttpTasks {
    private static final String TASK1_1_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String TASK1_2_URL = "https://jsonplaceholder.typicode.com/users/1";
    private static final String TASK2_1_URL = "https://jsonplaceholder.typicode.com/users/1/posts";
    private static final String TASK2_1_0_URL = "https://jsonplaceholder.typicode.com/posts/10/comments";
    private static final String TASK3_URL = "https://jsonplaceholder.typicode.com/users/1/todos";

    public static void main(String[] args) throws IOException, InterruptedException {

        //1-1
        User user = createDefaultUser();
        final User task1_1 = HttpUtil.createNewObject(URI.create(TASK1_1_URL), user);
        System.out.println("Task 1 " + task1_1);


        //1-2
        final User task1_2 = HttpUtil.updateObject(URI.create(TASK1_2_URL), user);
        System.out.println("Task 1 " + task1_2);


        //1-3
        System.out.println(HttpUtil.deleteObject(URI.create(TASK1_2_URL)));

        //2-1
        HttpUtil.lastPostComments(URI.create(TASK2_1_URL), URI.create(TASK2_1_0_URL));

        //3-1
        System.out.println(HttpUtil.openedTask(URI.create(TASK3_URL)));
    }


    private static User createDefaultUser(){
        Company company = new Company();
        company.setName("GoIT");
        company.setCatchPhrase("Programming learning");
        company.setBs("something");

        Geo geo = new Geo();
        geo.setLat(49.5800f);
        geo.setLng(36.1500f);

        Address address = new Address();
        address.setStreet("Pavlova");
        address.setSuite("Apt. 456");
        address.setCity("Kharkov");
        address.setZipcode("61000");
        address.setGeo(geo);

        User user = new User();
        user.setId(1);
        user.setName("Artem");
        user.setUsername("Kurowski");
        user.setMail("fortamt@gmai.com");
        user.setAddress(address);
        user.setPhone(123456789);
        user.setWebsite("mywebsite.com");
        user.setCompany(company);

        return user;
    }

}

