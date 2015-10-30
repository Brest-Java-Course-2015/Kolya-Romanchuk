
package com.epam.brest.course2015.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

/**
 * Created by kolya on 26.10.15.
 */

@Component
public class Main {

    @Value("${user.protocol}://${user.host}:${user.port}/${user.prefix}/")
    private String mainUrl;

    @Value("${qry.users}")
    private String qryUsers;

    @Value("${qry.user}")
    private String qryUser;

    Scanner sc = new Scanner(System.in);
    ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    {
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Main main = context.getBean(Main.class);
        main.menu();
    }

    private void menu() {

        int swValue = 0;

        System.out.println("=================================");
        System.out.println("|   MENU SELECTION DEMO         |");
        System.out.println("=================================");
        System.out.println("| Options:                      |");
        System.out.println("|        1. Get all users       |");
        System.out.println("|        2. Get user by login   |");
        System.out.println("|        3. Exit                |");
        System.out.println("=================================");
        while (swValue != 3) {
            System.out.print("Select option: ");
            if (sc.hasNextInt()) {
                swValue = sc.nextInt();
                checkValue(swValue);
            } else {
                System.out.println("Bad value." + sc.nextInt());
            }
        }
    }

    private void checkValue(int menuItem) {
        switch (menuItem) {
            case 1:
                getAllUsers();
                break;
            case 2:
                getUserByLogin();
                break;
            case 3:
                System.out.println("Exit.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void getAllUsers() {
        ResponseEntity responseEntity = restTemplate.getForEntity(mainUrl + qryUsers, Object.class);
        System.out.println("user: " + responseEntity.getBody());
    }

    private void getUserByLogin() {
        String userLogin = "";
        System.out.print("    Enter user login: ");
        if(sc.hasNextLine()) {
            userLogin = sc.next();
        }

        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(mainUrl + qryUser + "/" + userLogin, Object.class);
            System.out.println("    User: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }
}