package com.shanhai.dataStructe._02DynamicArray;

import com.shanhai.pojo.Person;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/13 11:17
 */
public class DynamicArrayTest {
    public static void main(String[] args) {
        test01();
    }

    private static void test02() {
        DynamicArray<Person> list = new DynamicArray<>();

        list.add(new Person(10, "jack"));
        list.add(new Person(20, "rose"));
        list.add(null);
        list.add(null);

        System.out.println("add()添加元素: " + list);

        System.out.println("get()获取元素: " + list.get(0));

        list.set(0, new Person(99, "ghost"));
        System.out.println("set()设置元素值: " + list);

        list.remove(0);
        System.out.println("remove()删除元素: " + list);

        list.clear();
        System.out.println("clear()清空数组: " + list);
    }

    private static void test01() {
        DynamicArray<Integer> list = new DynamicArray<>();
        list.add(111);
        list.add(99);
        list.add(88);
        list.add(77);
        list.add(66);
        list.add(55);
        list.add(44);
        list.add(33);
        list.add(22);
        list.add(11);
        //System.out.println(list);
        //list.foreach(System.out::println);
        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }

        DynamicArray<Person> persons = new DynamicArray<>();
        persons.add(new Person(1, "Jack"));
        persons.add(new Person(2, "Rose"));
        persons.add(new Person(3, "Mike"));
        persons.add(new Person(4, "Jess"));
        persons.add(new Person(5, "Clock"));
        persons.add(new Person(6, "Max"));
        persons.add(new Person(7, "Min"));
        //System.out.println(persons);
        //persons.foreach(System.out::println);
        for (Person person : persons) {
            System.out.println("person = " + person);
        }

        DynamicArray<Person> personArrayList = new DynamicArray<>();
        personArrayList.add(new Person(1, "Jack"));
        personArrayList.add(null);
        personArrayList.add(new Person(2, "Rose"));
        personArrayList.add(new Person(3, "Mike"));
        //System.out.println(personArrayList);
        //personArrayList.foreach(System.out::println);
        //for (Person person : personArrayList) {
        //    System.out.println("person = " + person);
        //}

        personArrayList.stream().forEach(System.out::println);

        System.out.println(personArrayList.indexOf(null));
    }
}
