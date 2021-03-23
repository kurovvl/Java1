package GB.Lesson6.Animals;

import GB.Lesson6.Animals.Animal;

public class Dog extends Animal {
    public static final int SWIM_LIMIT = 10;
    public static final int RUN_LIMIT = 500;
    private static int count = 0;


    public Dog(String name) {
        super(name, SWIM_LIMIT, RUN_LIMIT);
        this.count++;
    }

    @Override
    public String getCountOf(){
        return "Собак: " + this.count + ", " + super.getCountOf();
    }

}
