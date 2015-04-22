package ar.com.wolox.lucasdelatorre.training;

import java.util.ArrayList;

public class NewInstance {
    public String image;
    public String title;
    public String description;
    public String time;
    public boolean like;

    public NewInstance(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public NewInstance(String image, String title, String description, String time, boolean like) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.time = time;
        this.like = like;
    }

    public static ArrayList<NewInstance> getUsers() {
        ArrayList<NewInstance> news = new ArrayList<>();
        news.add(new NewInstance("Harry", "San Diego"));
        news.add(new NewInstance("Marla", "San Francisco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));
        news.add(new NewInstance("Sarah", "San Marco"));

        return news;
    }

}