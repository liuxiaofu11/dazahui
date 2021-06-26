package com.tengda.dazahui.system.controller;



import java.util.*;
import java.util.stream.Collectors;

public class Test1Controller {

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        Person p1 = new Person("xiaoming",1);
        Person p2 = new Person("xiaoming",2);
        Person p3 = new Person("xiaoxin",4);
        Person p4 = new Person("xiaoxin",5);
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);


        //先分组
        Map<String, List<Person>> map = personList.stream()
                .collect(Collectors.groupingBy(Person::getName));
        //再取最大值
        List<Person> result = map.values().stream()
                .map(people -> people.stream()
                        .max(Comparator.comparing(Person::getAge)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(result);


    }

}
class    Person {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}