package ru.amiralab.ecotales;

public class Card {
    private Long id;
    private String eng;
    private String rus;
    private String context;
    private String imagePath;
    private String videoPath;
    private String imageVid;

    public Card(Long id, String eng, String rus, String context, String imagePath, String videoPath, String imageVid) {
        this.id = id;
        this.eng = eng;
        this.rus = rus;
        this.context = context;
        this.imagePath = imagePath;
        this.videoPath = videoPath;
        this.imageVid = imageVid;
    }

    public String getEng() {
        return eng;
    }

    public String getRus() {
        return rus;
    }

    public Long getId() {
        return id;
    }

    public String getContext() {
        return context;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public String getImageVid() {
        return imageVid;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", eng='" + eng + '\'' +
                ", rus='" + rus + '\'' +
                ", context='" + context + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", video='" + videoPath + '\'' +
                ", videoIm='" + imageVid + '\'' +
                '}';
    }
}
