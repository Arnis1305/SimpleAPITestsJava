package Models.GetSingleUser;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Support {
    private String url;
    private String text;

    public Support() {
    }

    public Support(String url, String text) {
        this.url = url;
        this.text = text;
    }

    @Override
    public String toString() {
        return "url: " + url + "text: " + text;
    }
}
