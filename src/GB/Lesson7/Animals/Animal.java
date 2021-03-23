package GB.Lesson7.Animals;

public class Animal {
    private String name;
    private int swimLimit;
    private int runLimit;
    private static int count = 0;


    public Animal(String name, int swimLimit, int runLimit) {
        this.name = name;
        this.swimLimit = swimLimit;
        this.runLimit = runLimit;
        this.count++;
    }

    public void swim(int distance) {
        if (swimLimit > 0)
            System.out.println(
                    distance > swimLimit
                            ? name + ": \"Эй, я столько не проплыву!\""
                            : name + " проплыл " + distance + " метров и утонул..."
            );
        else System.out.println(name + ": \"Эй, я не умею плавать!\"");
    }

    public void run(int distance) {
        System.out.println(
                distance > runLimit
                        ? name + ": \"Эй, я столько не пробегу!\""
                        : name + " пробежал " + distance + " метров и остановился..."
        );
    }

    public String getCountOf() {
        return "Животных всего: " + this.count;
    }

    public String getName() {
        return name;
    }
}
