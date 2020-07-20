package com.line.ticket.log.ju;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[] locations = {1, 2, 3, 4, 5, 6, 2, 2, 5, 4, 3};

        Item item1 = new Item("id1", 1, 1);
        Item item2 = new Item("id2", 1, 2);
        Item item3 = new Item("id3", 2, 1);
        Item item4 = new Item("id4", 2, 2);

        Comparator<Item> comparator = Item.ITEM_COMPARATOR;

        Set<Item> set = new TreeSet<>(Item.ITEM_COMPARATOR);
        set.add(item1);
        set.add(item3);
        set.add(item2);
        set.add(item4);
        Item item5 = new Item("id5", 2, 1);
        set.add(item5);
        System.out.println(set.size());
        System.out.println(Arrays.toString(set.toArray()));
    }
}
