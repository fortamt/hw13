package ua.goit.http;

import java.io.IOException;
import java.net.URI;

public class HttpTasks {
    private static final String TASK1_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException, InterruptedException {
        User user = createDefaultUser();
        final User task1_1 = HttpUtil.createNewObject(URI.create(TASK1_URL), user);
        System.out.println("Task 1 " + task1_1);

        final User task1_2 = HttpUtil.updateObject(URI.create(TASK1_URL), user);
        System.out.println("Task 1 " + task1_2);
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
        user.setId(2);
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

