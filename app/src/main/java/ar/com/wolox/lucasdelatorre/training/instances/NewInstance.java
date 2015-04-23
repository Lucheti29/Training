package ar.com.wolox.lucasdelatorre.training.instances;

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
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));
        news.add(new NewInstance("Ali Connors", "I'll be in your neighborhood doing errands ..."));

        return news;
    }

}