package Models.GetSingleUser;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class Data {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public Data() {
    }

    public Data(Integer id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "id: " + id + "email: " + email + "first_name: " + first_name + "last_name: " + last_name + "avatar: " + avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(id, data.id) && Objects.equals(email, data.email) && Objects.equals(first_name, data.first_name) && Objects.equals(last_name, data.last_name) && Objects.equals(avatar, data.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, first_name, last_name, avatar);
    }
}
