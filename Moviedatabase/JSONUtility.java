package com.example.moviedb;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONUtility {
    private static final String TAG = "JSONUtility";

    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();
        String jsonString = readJsonFile(context);

        if (jsonString == null) {
            Log.e(TAG, "Failed to read JSON file");
            return movies;
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String title = null;
                    int year = 0;
                    String genre = null;
                    String poster = null;

                    if (jsonObject.has("title") && !jsonObject.isNull("title")) {
                        title = jsonObject.getString("title");
                    }

                    if (jsonObject.has("year") && !jsonObject.isNull("year")) {
                        Object yearObj = jsonObject.get("year");
                        if (yearObj instanceof Integer) {
                            year = jsonObject.getInt("year");
                        } else if (yearObj instanceof Double) {
                            year = (int) Math.round(jsonObject.getDouble("year"));
                        } else if (yearObj instanceof String) {
                            try {
                                year = Integer.parseInt((String) yearObj);
                            } catch (NumberFormatException e) {
                                Log.w(TAG, "Invalid year format: " + yearObj);
                                year = 0;
                            }
                        }
                    }

                    if (jsonObject.has("genre") && !jsonObject.isNull("genre")) {
                        genre = jsonObject.getString("genre");
                    }

                    if (jsonObject.has("poster") && !jsonObject.isNull("poster")) {
                        poster = jsonObject.getString("poster");
                    }

                    Movie movie = new Movie(title, year, genre, poster);
                    movies.add(movie);
                        //format
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing movie at index " + i + ": " + e.getMessage());
                }
            }

        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON array: " + e.getMessage());
        }

        return movies;
    }

    private static String readJsonFile(Context context) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.movie_data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file: " + e.getMessage());
            return null;
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "JSON file not found: " + e.getMessage());
            return null;
        }

        return stringBuilder.toString();
    }
}