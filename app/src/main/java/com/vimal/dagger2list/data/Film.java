package com.vimal.dagger2list.data;

import com.google.gson.annotations.SerializedName;

public class Film {
    @SerializedName("title")
    public String title;
    @SerializedName("director")
    public String director;
}
