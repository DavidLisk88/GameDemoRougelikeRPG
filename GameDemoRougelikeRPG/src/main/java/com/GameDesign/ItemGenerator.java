package com.GameDesign;

import java.util.*;

public class ItemGenerator {

    public static class Item implements List<Item> {
        public String name;
        public String type; // weapon, armor, skill, valuable
        public int level;
        public int effectiveness;

        public Item(String name, String type, int level, int effectiveness) {
            this.name = name;
            this.type = type;
            this.level = level;
            this.effectiveness = effectiveness;
        }

        @Override
        public String toString() {
            return name + " (Type: " + type + ", Level: " + level + ", Effectiveness: " + effectiveness + ")";
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Item> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Item item) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Item> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Item> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Item get(int index) {
            return null;
        }

        @Override
        public Item set(int index, Item element) {
            return null;
        }

        @Override
        public void add(int index, Item element) {

        }

        @Override
        public Item remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Item> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Item> listIterator(int index) {
            return null;
        }

        @Override
        public List<Item> subList(int fromIndex, int toIndex) {
            return List.of();
        }
    }

    private static final Random random = new Random();

    private static final List<Item> goodWeapons = Arrays.asList(
            new Item("Radiant Sword", "weapon", 1, 15),
            new Item("Holy Lance", "weapon", 2, 20),
            new Item("Blessed Bow", "weapon", 3, 25)
    );

    private static final List<Item> evilWeapons = Arrays.asList(
            new Item("Dagger of the Damned", "weapon", 1, 18),
            new Item("Blood Cleaver", "weapon", 2, 23),
            new Item("Hellfire Staff", "weapon", 3, 28)
    );

    private static final List<Item> goodSkills = Arrays.asList(
            new Item("Light Heal", "skill", 1, 10),
            new Item("Divine Shield", "skill", 2, 15),
            new Item("Holy Strike", "skill", 3, 20)
    );

    private static final List<Item> evilSkills = Arrays.asList(
            new Item("Life Leech", "skill", 1, 12),
            new Item("Dark Curse", "skill", 2, 17),
            new Item("Soul Drain", "skill", 3, 22)
    );

    private static final List<Item> goodArmor = Arrays.asList(
            new Item("Knight's Plate", "armor", 1, 12),
            new Item("Guardian Mail", "armor", 2, 18),
            new Item("Angelic Robe", "armor", 3, 22)
    );

    private static final List<Item> evilArmor = Arrays.asList(
            new Item("Bone Carapace", "armor", 1, 14),
            new Item("Demonic Hide", "armor", 2, 19),
            new Item("Wraithmail", "armor", 3, 24)
    );

    private static final List<Item> goodValuables = Arrays.asList(
            new Item("Sun Pendant", "valuable", 1, 5),
            new Item("Crystal Chalice", "valuable", 2, 8),
            new Item("Holy Gem", "valuable", 3, 10)
    );

    private static final List<Item> evilValuables = Arrays.asList(
            new Item("Blood Ruby", "valuable", 1, 7),
            new Item("Demon Fang", "valuable", 2, 10),
            new Item("Cursed Idol", "valuable", 3, 12)
    );

    public static Item generateItem(String type, String morality) {
        type = type.toLowerCase();
        morality = morality.toLowerCase();

        List<Item> sourceList;

        if (type.equals("random")) {
            type = getRandomType();
        }

        switch (type) {
            case "weapon":
                sourceList = morality.equals("good") ? goodWeapons : evilWeapons;
                break;
            case "skill":
                sourceList = morality.equals("good") ? goodSkills : evilSkills;
                break;
            case "armor":
                sourceList = morality.equals("good") ? goodArmor : evilArmor;
                break;
            case "valuable":
                sourceList = morality.equals("good") ? goodValuables : evilValuables;
                break;
            default:
                return new Item("Unknown Item", "unknown", 0, 0);
        }

        return sourceList.get(random.nextInt(sourceList.size()));
    }

    private static String getRandomType() {
        String[] types = {"weapon", "skill", "armor", "valuable"};
        return types[random.nextInt(types.length)];
    }
}
