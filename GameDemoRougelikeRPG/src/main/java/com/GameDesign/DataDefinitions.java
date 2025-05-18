package com.GameDesign;

import java.io.Serializable;
import java.util.*;

/**
 * Central lists of items, equipment, skills, and enemies.
 */
public class DataDefinitions {
    public enum Rarity { COMMON, UNCOMMON, RARE, EPIC, LEGENDARY }
    public enum ItemType { WEAPON, ARMOR, POTION, STONE, VALUABLE }
    public enum SkillType { PHYSICAL, MAGICAL, DEMONIC, HOLY }
    public enum EnemyType { DEMON, SOLDIER_EARTH, SOLDIER_PORTAL }

    public static class Item implements Serializable{
        public final String id, name;
        public final ItemType type;
        public final Rarity rarity;
        public final Map<String,Integer> attrs;
        public Item(String id, String name, ItemType type, Rarity rarity, Map<String,Integer> attrs) {
            this.id=id; this.name=name; this.type=type; this.rarity=rarity; this.attrs=attrs;
        }
    }

    public static class Skill implements Serializable {
        public final String id, name;
        public final SkillType type;
        public final String desc;
        public final Map<String,Integer> effect;
        public Skill(String id,String name,SkillType type,String desc,Map<String,Integer> effect){
            this.id=id; this.name=name; this.type=type; this.desc=desc; this.effect=effect;
        }
    }

    public static class EnemyDef implements Serializable {
        public final EnemyType type; public final String name;
        public final int level;
        public final Map<String,Integer> stats;
        public EnemyDef(EnemyType type,String name,int level,Map<String,Integer> stats){
            this.type=type; this.name=name; this.level=level; this.stats=stats;
        }
    }

    // Sample weapons
    public static final List<Item> WEAPONS = List.of(
            new Item("wp_001","Iron Sword",ItemType.WEAPON,Rarity.COMMON,Map.of("atk",8,"spd",5)),
            new Item("wp_002","Steel Axe",ItemType.WEAPON,Rarity.UNCOMMON,Map.of("atk",12,"spd",3)),
            new Item("wp_003","Dragon Blade",ItemType.WEAPON,Rarity.EPIC,Map.of("atk",20,"spd",4))
    );
    // Sample armor
    public static final List<Item> ARMOR = List.of(
            new Item("ar_001","Leather Armor",ItemType.ARMOR,Rarity.COMMON,Map.of("def",5)),
            new Item("ar_002","Chainmail",ItemType.ARMOR,Rarity.UNCOMMON,Map.of("def",10)),
            new Item("ar_003","Mythril Plate",ItemType.ARMOR,Rarity.LEGENDARY,Map.of("def",20))
    );
    // Potions and stones
    public static final List<Item> POTIONS = List.of(
            new Item("pt_001","Minor Potion",ItemType.POTION,Rarity.COMMON,Map.of("heal",20)),
            new Item("pt_002","Elixir",ItemType.POTION,Rarity.RARE,Map.of("heal",50))
    );
    public static final List<Item> STONES = List.of(
            new Item("st_001","Attack Stone",ItemType.STONE,Rarity.UNCOMMON,Map.of("atkBoost",5)),
            new Item("st_002","Defense Stone",ItemType.STONE,Rarity.UNCOMMON,Map.of("defBoost",5))
    );
    // Skills
    public static final List<Skill> SKILLS = List.of(
            new Skill("sk_001","Slash",SkillType.PHYSICAL,"A quick slash.",Map.of("dmg",10)),
            new Skill("sk_002","Fireball",SkillType.MAGICAL,"Burn enemy.",Map.of("dmg",15)),
            new Skill("sk_003","Dark Pact",SkillType.DEMONIC,"Sacrifice HP for power.",Map.of("dmgBoost",10,"hpCost",5)),
            new Skill("sk_004","Divine Light",SkillType.HOLY,"Smite demons.",Map.of("dmg",20))
    );
    // Enemies
    public static final List<EnemyDef> ENEMIES = List.of(
            new EnemyDef(EnemyType.DEMON,"Imp",3,Map.of("hp",40,"atk",6)),
            new EnemyDef(EnemyType.SOLDIER_EARTH,"Earth Soldier",5,Map.of("hp",60,"atk",8)),
            new EnemyDef(EnemyType.SOLDIER_PORTAL,"Portal Guard",5,Map.of("hp",65,"atk",9))
    );

    // Random fetchers
    public static Item randomWeapon() { return WEAPONS.get(RandomGenerator.nextInt(WEAPONS.size())); }
    public static Item randomArmor()  { return ARMOR.get(RandomGenerator.nextInt(ARMOR.size())); }
    public static Item randomPotion(){ return POTIONS.get(RandomGenerator.nextInt(POTIONS.size())); }
    public static Item randomStone() { return STONES.get(RandomGenerator.nextInt(STONES.size())); }
    public static Skill randomSkill() { return SKILLS.get(RandomGenerator.nextInt(SKILLS.size())); }
    public static EnemyDef randomEnemy(){ return ENEMIES.get(RandomGenerator.nextInt(ENEMIES.size())); }
}