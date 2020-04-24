class Person {
        private String full_name;
        private String position;
        private String email;
        private String phone;
        private float the_salary;
        private int age;

        Person(String full_name, String position, String email, String phone, float the_salary, int age) {
            this.full_name = full_name;
            this.position = position;
            this.email = email;
            this.phone = phone;
            this.the_salary = the_salary;
            if(age >= 18) {
                this.age = age;
            } else {
                System.out.println("Сотрудник должен быть старше 18 лет");
            }
        }

        int Age() {
            return age;
        }

        void Show() {
            System.out.println(full_name + " " + position + " " +  email + " " + phone + " " + the_salary + " " + age);
        }
    }

    //1. Создать класс «Сотрудник» с полями: ФИО, должность, email, телефон, зарплата, возраст;
    //2. Конструктор класса должен заполнять эти поля при создании объекта;
    //3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
    //4. Создать массив из 5 сотрудников, с помощью цикла вывести информацию только о сотрудниках старше 40 лет;

    public class task1 {

        public static void main(String[] args) {

            Person[] person_array = new Person[5];
            person_array[0] = new Person("|Кузьмин Юлиан Артёмович|", "|Systems analyst|", "|hello@kuzmin.im|", "|4(5861)840-32-08|", 2600, 27);
            person_array[1] = new Person("|Алексеев Цицерон Вадимович|", "|QA engineer|", "|hello@alexeev.im|", "|445(0204)651-34-93|", 1200, 23);
            person_array[2] = new Person("|Белов Гордей Максимович|", "|Java Team Lead|", "|hello@belov.im|", "|786(05)456-66-54|", 11300, 31);
            person_array[3] = new Person("|Коваленко Эдуард Романович|", "|Senior Java developer|", "|hello@kovalenko.im|", "|9(1021)789-81-37|", 8900, 42);
            person_array[4] = new Person("|Анисимов Ефрем Данилович|", "|Middle Java developer|", "|hello@anisimov.im|", "|25(984)080-65-61|", 3400, 25);

            for(Person item : person_array) {
                if(item.Age() > 40) item.Show();
            }
        }
}
