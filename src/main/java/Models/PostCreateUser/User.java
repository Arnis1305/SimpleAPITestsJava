package Models.PostCreateUser;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public User() {
    }

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
