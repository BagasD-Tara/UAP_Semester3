package com.tiketbioskop.Model;

public class Transaction {
    private User user;
    private Film film;
    private Seat seat;
    private double totalPrice;

    public Transaction(User user, Film film, Seat seat) {
        this.user = user;
        this.film = film;
        this.seat = seat;
        this.totalPrice = film.getPrice();
    }

    public User getUser() {
        return user;
    }

    public Film getFilm() {
        return film;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}