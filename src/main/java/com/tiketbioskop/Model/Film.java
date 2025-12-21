package com.tiketbioskop.Model;

public class Film {
    private String title;
    private String genre;
    private int duration;
    private double price;

    public Film(String title, String genre, int duration, double price) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }
}
