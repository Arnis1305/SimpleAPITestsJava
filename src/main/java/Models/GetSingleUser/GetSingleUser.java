package Models.GetSingleUser;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetSingleUser {
    private Data data;
    private Support support;

    public GetSingleUser() {
    }

    public GetSingleUser(Data data, Support support) {
        this.data = data;
        this.support = support;
    }

    @Override
    public String toString() {
        return "data: " + data + "support: " + support;
    }
}
