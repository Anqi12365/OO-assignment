package com.example.moviedb;

public class Movie {
    private String title;
    private int year;
    private String genre;
    private String poster;

    // Constructor with validation
    public Movie(String title, int year, String genre, String poster) {
        setTitle(title);
        setYear(year);
        setGenre(genre);
        setPoster(poster);
    }

    // Getters
    public String getTitle() {
        return title != null ? title : "Unknown Title";
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre != null ? genre : "Unknown Genre";
    }

    public String getPoster() {
        return poster != null ? poster : "default_poster";
    }

    // Setters with validation
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title.trim();
        } else {
            this.title = "Unknown Title";
        }
    }

    public void setYear(int year) {
        // Valid year range: 1888 to current year + 5
        if (year >= 1888 && year <= 2030) {
            this.year = year;
        } else {
            this.year = 0; // Invalid year indicator
        }
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.trim().isEmpty()) {
            this.genre = genre.trim();
        } else {
            this.genre = "Unknown Genre";
        }
    }

    public void setPoster(String poster) {
        if (poster != null && !poster.trim().isEmpty()) {
            this.poster = poster.trim();
        } else {
            this.poster = "default_poster";
        }
    }

    // Check if movie data is valid
    public boolean isValid() {
        return title != null && !title.equals("Unknown Title") && year > 0;
    }
}