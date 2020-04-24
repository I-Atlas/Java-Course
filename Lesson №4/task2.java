abstract class Animal {
    protected int _length;
    protected double _height;

    abstract void Run(int length);
    abstract void Jump(double height);

    static void show_result(Object obj, boolean result) {
        System.out.println("Результат: " + obj.getClass().getName() + " = " + result + "\n");
    }
}

class Cat extends Animal {
    @Override
    void Run(int length) {
        boolean result = length <= 200;
        if (result) _length = length;
        show_result(this,result);
    }
    @Override
    void Jump(double height) {
        boolean result = height <= 2;
        if (result) _height = height;
        show_result(this,result);
    }

}

class Dog extends Animal {
    private int swim_length;
    private int max_length;

    Dog() {
        max_length = Math.random() > 0.5 ? 400 : 600;
    }

    void Swim(int length) {
        boolean result = length <= 10;
        if (result) swim_length = length;
        show_result(this,result);
    }
    @Override
    void Run(int length) {
        boolean result = length <= max_length;
        if (result) _length = length;
        show_result(this,result);
    }
    @Override
    void Jump(double height) {
        boolean result = height <= 0.5;
        if (result) _height = height;
        show_result(this,result);
    }
}

//5. Создать классы Собака и Кот с наследованием от класса Животное;
//6. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков);
//7. У каждого животного есть ограничения на действия (бег: кот – 200 м., собака – 500 м.; прыжок: кот – 2 м., собака – 0.5 м.; плавание: кот не умеет плавать, собака – 10 м.);
//8. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true);
//9. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой – 600 м.

public class task2 {
    public static void main(String[] args) {
        Dog Dog = new Dog();
        Animal Cat = new Cat();

        Dog.Run(150);
        Cat.Run(5000);

        Dog.Jump(10);
        Cat.Jump(11);

        Dog.Swim(3);
    }
}