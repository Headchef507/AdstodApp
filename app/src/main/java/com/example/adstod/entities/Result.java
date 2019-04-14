package com.example.adstod.entities;

import java.net.URL;

public class Result{
    // Bunch of local variables
    private long id;
    private String title;
    private URL link;
    private String description;
    private int[] phoneNumbers;

    // Constructor for result
    public Result() {}

    // Id for a result
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    // Title for a result
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    // Link for a result
    public URL getLink() { return link; }

    public void setLink(URL link) { this.link = link; }

    // Description for a result
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    // Phone numbers for a result
    public int[] getPhoneNumbers() { return phoneNumbers; }

    public void setPhoneNumbers(int[] phoneNumbers) { this.phoneNumbers = phoneNumbers; }
}
