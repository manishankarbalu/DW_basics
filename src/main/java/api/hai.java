package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class hai {
    private int id;
    private String content;
    public hai(){

    }
    public hai(int id,String content){
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
