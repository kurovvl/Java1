package GB.Lesson6;


import GB.Lesson6.Animals.Animal;
import GB.Lesson6.Animals.Cat;
import GB.Lesson6.Animals.Dog;

public class Main {

    public static void main(String[] args) {
        Animal cat = new Cat("Мурзик");
        Animal cat2 = new Cat("Барсик");
        Animal dog = new Dog("Бобик");
        //Animal dog2 = new Dog("Барбос");

        dog.run(1500);
        cat.swim(30);;
        cat.run(100);


        System.out.println(cat.getCountOf());
        System.out.println(dog.getCountOf());

    }

}
