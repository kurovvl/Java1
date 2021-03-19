package GB.Lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void increaseFood(int foodToIncrease) {
        this.food += foodToIncrease;
        System.out.println("Миска пополнена на "+ foodToIncrease);
    }

    public boolean decreaseFood(int foodToDecrease) {
        if (this.food - foodToDecrease < 0) return false;
        this.food -= foodToDecrease;
        return true;
    }
}
