package com.tanky.structure.jdk8new;

import java.util.*;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 4, 3, 2, 7, 4, 5);
        //list.forEach(System.out::println);

        //类的静态方法调用
        // List<String> convert = CollectionUtil.convert(list, integer -> Integer.toHexString(integer));
        List<String> convert = CollectionUtil.convert(list, Integer::toHexString);

        //非静态方法调用  可以与静态方法调用一致
        // List<String> convert1 = CollectionUtil.convert(convert, s -> s.toUpperCase());
        List<String> convert1 = CollectionUtil.convert(convert, String::toUpperCase);

        //指定实例的非静态方法的引用
        Integer num = 5;
        //List<Integer> convert3=CollectionUtil.convert(list,i->num.compareTo(i));
        List<Integer> convert2 = CollectionUtil.convert(list, num::compareTo);

        //构造方法的引用
        //List<Date> convert3 = CollectionUtil.convert(convert1, i -> new Date(i));
        List<Date> convert4 = CollectionUtil.convert(convert1, Date::new);


//        System.out.println(convert2);
//
//        System.out.println(convert1);
//
//        System.out.println(convert);


        //****************************************Optional**************************************************

        List<Object> aaa=new ArrayList();
        aaa.add(2);
        aaa.add(null);

        Optional<List> optionalS=Optional.ofNullable(aaa);

        System.out.println(optionalS.orElseGet(() -> new ArrayList()));

        System.out.println(optionalS.isPresent());

        //****************************************Stream流式编程***********************************************


        final List<Streams.Task> tasks=Arrays.asList(
                new Streams.Task( Streams.Status.OPEN, 5 ),
                new Streams.Task( Streams.Status.OPEN, 13 ),
                new Streams.Task( Streams.Status.CLOSED, 8 ));

        int sum = tasks.stream().filter(task -> task.getStatus() == Streams.Status.OPEN).mapToInt(Streams.Task::getPoints).sum();

        System.out.println(sum);

        //并行处理所有的task
        Integer reduce = tasks.stream().parallel().map(Streams.Task::getPoints).reduce(0, Integer::sum);


        //根据status分组
        Map<Streams.Status, List<Streams.Task>> collect = tasks.stream().collect(Collectors.groupingBy(Streams.Task::getStatus));


        List<String> collect1 = tasks.stream().mapToInt(Streams.Task::getPoints).asLongStream().mapToDouble(points -> points / reduce)
                //处理*100的操作是 对于除了以后的数据进行的
                .boxed().mapToLong(weight -> (long) (weight * 100)).mapToObj(p -> p + "%").collect(Collectors.toList());


    }

    static class Date {

        String date;

        public Date(String date) {
            date = date;
        }

    }

}

