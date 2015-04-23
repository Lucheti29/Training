package ar.com.wolox.lucasdelatorre.training.instances;

import java.util.ArrayList;
import java.util.Date;

public class NewsInstance {

    private String mTitle;
    private String mDescription;
    private Date mTime;
    private boolean mLike;

    public NewsInstance(String title, String description, boolean like, Date time) {
        setTitle(title);
        setDescription(description);
        setLike(like);
        setTime(time);
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date mTime) {
        this.mTime = mTime;
    }

    public boolean isLike() {
        return mLike;
    }

    public void setLike(boolean mLike) {
        this.mLike = mLike;
    }

    public static ArrayList<NewsInstance> getUsers() {
        ArrayList<NewsInstance> news = new ArrayList<>();

        Date date = new Date();

        //TODO: Test - Delete it
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", true, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", true, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", true, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));
        news.add(new NewsInstance("Ali Connors",
                "I'll be in your neighborhood doing errands ...", false, date));

        return news;
    }

}