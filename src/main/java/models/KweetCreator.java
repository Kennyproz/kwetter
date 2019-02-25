package models;

import java.util.Date;
import java.util.List;

public class KweetCreator {
    private String content;
    private String dateTime;
    private String creator;
    private List<String> mentions;

    public KweetCreator(String content, String dateTime, String creator, List<String> mentions) {
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
        this.mentions = mentions;
    }


}
