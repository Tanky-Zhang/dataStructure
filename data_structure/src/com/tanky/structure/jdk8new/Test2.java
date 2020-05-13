package com.tanky.structure.jdk8new;


public class Test2<T> {

    Class<?> aClass;
    Class<T> tClass;

    /**
     * <T> T 代表了返回的数据类型为T类型的  如果不带泛型就是Object类型的如果带了就是T类型
     * @param clazz
     * @param <>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public  static <T> T createInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

//        A a=createInstance(A.class);
//        B b=createInstance(B.class);

    }

    class A {}

    class B {}

}
