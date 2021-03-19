package GB.Lesson7.Animals;

import GB.Lesson7.Plate;

import java.text.MessageFormat;

public class Cat extends Animal {
    public static final int SWIM_LIMIT = 0;
    public static final int RUN_LIMIT = 200;
    private static int count = 0;
    private int appetite;
    private boolean satiety = false;


    public Cat(String name, int appetite) {
        super(name, SWIM_LIMIT, RUN_LIMIT);
        this.appetite = appetite;
        this.count++;

    }

    @Override
    public String getCountOf() {
        return "Кошек: " + this.count + ", " + super.getCountOf();
    }

    public void eat(Plate plate) {
        if (this.satiety) {
            printSatiety();
            return;
        }
        if (this.satiety = plate.decreaseFood(this.appetite))
            System.out.println(MessageFormat.format(getTemplate(), "Я поел!")+"["+this.appetite+"]");
        else System.out.println(MessageFormat.format(getTemplate(), "Я не буду это есть, тут не хватает - доложи!"));
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void printSatiety() {
        if (this.satiety)
            System.out.println(MessageFormat.format(getTemplate(), "Я сыт!"));
        else
            System.out.println(MessageFormat.format(getTemplate(), "Мне бы перекусить!"));

    }

    private String getTemplate() {
        return super.getName() + ": \"{0}\"";
    }
}
