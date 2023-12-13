package Java1.Lesson7;

public class Cat {

    private String name;
    private int appetite;
    private boolean satiety;
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }
    public void eat(Plate p) {
        if (p.decreaseFood(appetite)) {
            satiety = true;
        }
    }

    public void catInfo() {
        if (satiety) {
            System.out.println(name + " поел " + appetite + " грамм корма и он сыт");
        } else {
            System.out.println(name + " не может поесть, т.к. тарелке нет столько еды и он голоден");
        }
    }
}
