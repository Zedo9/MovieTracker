package Model;

public class Actor {
    private String name;
    private String realName;
    private String profile;

    public Actor(String name, String realName, String profile) {
        this.name = name;
        this.realName = realName;
        this.profile = profile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
