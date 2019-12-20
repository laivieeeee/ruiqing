package com.ruiqing.java8;

import java.util.Optional;

/***************************************
 * @author:
 * @Date:2016/10/24
 *
 ***************************************/
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return this.car;
    }
}
