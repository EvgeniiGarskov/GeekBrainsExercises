package Java1.Lesson7;

public class Plate {

    private int food;
    public Plate(int food) {
        this.food = food;
    }
    public boolean decreaseFood(int n) {
        if (food >= n) {
            food -= n;
            return true;
        } else {
            return false;
        }
    }
    public void info() {
        System.out.println("plate: " + food);
    }

    public void addEat(int k) {
        food += k;
    }
}
