package GB.Lesson6.Animals;

public class Cat extends Animal {
    public static final int SWIM_LIMIT = 0;
    public static final int RUN_LIMIT = 200;
    private static int count = 0;



    public Cat(String name) {
        super(name, SWIM_LIMIT, RUN_LIMIT);
        this.count++;

    }
    @Override
    public String getCountOf(){
        return "Кошек: " + this.count + ", " + super.getCountOf();
    }
}
