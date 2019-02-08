package ua.ita.smartcarservice.dto.sensors;

public enum Tires {
    FRONT_LEFT("front left"),
    FRONT_RIGHT("front right"),
    BACK_LEFT("back left"),
    BACK_RIGHT("back right");

    private String tire;

    private Tires(String tire){
        this.tire = tire;
    }

    public String toString() {
        return tire;
    }
}
