package GB.Lesson7;


import GB.Lesson7.Animals.Cat;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        var catNames = new String[]{"Мурзик", "Барсик", "Кот", "ИмяРек", "Котофей"};
        Cat[] cats = new Cat[catNames.length];

        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(catNames[i], new Random().nextInt(3) * 10 + 10); //10-30
        }

        Plate plate = new Plate(60);

        var hungryCats = cats.length;

        while (hungryCats > 0) {
            for (Cat cat : cats) {
                if (!cat.isSatiety()) {
                    cat.eat(plate);
                    hungryCats -= cat.isSatiety() ? 1 : 0;
                }
            }
            if (hungryCats > 0) plate.increaseFood(50);
        }
        System.out.println("---------------\nВсе котики сыты");


        //Animal dog = new Dog("Бобик");
        //Animal dog2 = new Dog("Барбос");

//        dog.run(1500);
//        cat.swim(30);;
//        cat.run(100);
//
//
//        System.out.println(cat.getCountOf());
//        System.out.println(dog.getCountOf());

    }

}

