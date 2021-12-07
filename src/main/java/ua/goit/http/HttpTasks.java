package ua.goit.http;

import java.io.IOException;
import java.net.URI;

public class HttpTasks {

    public static void main(String[] args) throws IOException, InterruptedException {

        User user = createDefaultUser();

        //1-1
        final User task1_1 = HttpUtil.createNewObject(user);
        System.out.println("Task 1 " + task1_1);

        //1-2
        final User task1_2 = HttpUtil.updateObject(3, user);
        System.out.println("Task 1 " + task1_2);

        //1-3
        System.out.println(HttpUtil.deleteObject(3));

        //1-4
        System.out.println(HttpUtil.usersInf());

        //1-5
        System.out.println(HttpUtil.userInfById(5));

        //1-6
        HttpUtil.userInfByUsername("Samantha");

        //2-1
        HttpUtil.lastPostComments(2);

        //3-1
        System.out.println(HttpUtil.openedTask(3));
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
        user.setPhone("123456789");
        user.setWebsite("mywebsite.com");
        user.setCompany(company);

        return user;
    }

}

