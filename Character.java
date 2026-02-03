import java.util.*;

public class Character {

    private String name;
    private String characterType;
    private int level = 1;
    private int healthPoints;
    private int armorClass;
    private int Str;
    private int Dex;
    private int Wis;
    private int Cha;
    private int Con;
    private int Int;

    public Character(String name,String characterType){
        this.name = name;
        this.characterType = characterType;

    }

    String getAbilityModifier(int abilityScore){
        if (((abilityScore-10)/2) > 0){
            return "+" + ((abilityScore-10)/2);
        }else{
            return "" + ((abilityScore-10)/2);
        }

    }

    int getPrimaryAbilityModifier(){
        if(Objects.equals(characterType, "melee")){
            return Str;
        } else if (Objects.equals(characterType, "ranged")) {
            return Dex;
        } else {
            return Int;
        }
    }

    void calculateBaseHitPoints(){
        healthPoints = (20 + Con);
    }

    void calculateArmorClass(){
        armorClass = (10 + getPrimaryAbilityModifier());
    }

    int rollStat(){
        Dice dice = new Dice();
        dice.addDie(4,6);
        dice.rollAll();
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i <= 3; i++) {
            values.add(dice.getDieValue(i));
        }
        Collections.sort(values);

        return (dice.getAllValue() - values.getFirst());
    }

    void rollStats(){
        Str = rollStat();
        Wis = rollStat();
        Dex = rollStat();
        Int = rollStat();
        Con = rollStat();
        Cha = rollStat();
        calculateArmorClass();
        calculateBaseHitPoints();
    }

    boolean isAlive(){
        if (healthPoints == 0){
            return false;
        } else{
            return true;
        }
    }



    String chaSheet(){
        return "Character " + name + " Level " + level + " " + characterType +
                "\nHP: " + healthPoints+ " | AC: " + armorClass+
                "\nSTR: " + Str +  " (" + getAbilityModifier(Str)+ ") |" + "DEX: " + Dex +  " (" + getAbilityModifier(Dex)+ ") |" + "CON: " + Con +  " (" + getAbilityModifier(Con)+ ") |" +
                "\nINT: " + Int +  " (" + getAbilityModifier(Int)+ ") |" + "WIS: " + Wis +  " (" + getAbilityModifier(Wis)+ ") |" + "CHA: " + Cha +  " (" + getAbilityModifier(Cha)+ ") |";
    }

}
