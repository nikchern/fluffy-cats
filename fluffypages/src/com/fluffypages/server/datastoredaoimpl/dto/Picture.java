package com.fluffypages.server.datastoredaoimpl.dto;

public class Picture {
  private final String url;
  private long rating;

  private Picture(PictureBuilder builder) {
    this.url = builder.url;
    this.rating = builder.rating;
  }

  public String getUrl() {
    return url;
  }

  public long getRating() {
    return rating;
  }

  public static class PictureBuilder {
    private final String url;
    private long rating;

    public PictureBuilder(String url) {
      this.url = url;
    }

    public PictureBuilder rating(long rating) {
      this.rating = rating;
      return this;
    }
    
    public Picture build() {
      return new Picture(this);
    }
  }
}
