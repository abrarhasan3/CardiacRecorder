package userDefinedClass;

public class UserInfo {
    String name;
    float height, weight;

    public UserInfo(String name, float height, float weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    /**
     *
     * @return returns the name of User
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns height of User
     */
    public float getHeight() {
        return height;
    }

    /**
     *
     * @return returns the weight of User
     */
    public float getWeight() {
        return weight;
    }
}
