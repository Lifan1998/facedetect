package com.example.face;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fan.li
 * @date 2020-03-24
 * @description
 */

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap();

        map.put(1,1);
        map.put(1,2);

        System.out.println(map.size());
        System.out.println(map.get(1));



        list.stream()
                .map(id -> {
            System.out.println(id);
            return id;
        }).collect(Collectors.toSet());

        System.out.println(new Date().getTime());
        System.out.println(new Date().getTime() + 1000 * 60 * 60 * 24 * 30);

    }
}

