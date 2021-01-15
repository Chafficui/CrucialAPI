package de.crucial.CrucialAPI.CScript;

import de.chaffic.MyRPG.API.*;
import de.chaffic.MyTrip.MyDrug;
import de.crucial.CrucialAPI.API.CItem;

import java.util.Arrays;

public class Interpreter {

    public static boolean compile(String code){
        String[] commands = code.split("\n");
        System.out.println(commands.length);
        System.out.println(Arrays.toString(commands));

        for (String command:commands) {
            if(!command.equals("")){
                if(command.startsWith("new")) {
                    if(!cmdNew(command))
                        return false;
                } else {
                    if(!cmdAttrib(command))
                        return false;
                }
            }
        }
        return true;
    }

    public static boolean cmdNew(String command){
        try {
            String[] words = command.split(" ");

            switch (words[1]){
                case "Class":
                    SkillAPI.addMyClass(MyClass.builder().name(words[2]).build());
                    break;
                    /*
                case "Item":
                    CItem.addCrucialItem(MyItem.builder().name(words[2]).build());
                    break;

                     */
                case "Drug":
                    CItem.addCrucialItem(MyDrug.builder().name(words[2]).build());
                default:
                    throw new IllegalArgumentException("Unknown Object Creation on \"" + command + "\"");

            }
            return true;
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cmdAttrib(String command){
        try {
            System.out.println(command.toUpperCase());
            String[] split = command.split("\\(");
            String start = split[0];
            System.out.println(start);
            split = split[1].split(", ", 2);
            String object = split[0];
            System.out.println(object);
            String value = split[1].substring(0, split[1].length() - 2);
            System.out.println(value);

            switch (start) {
                case "agility":
                    SkillAPI.getMyClassByName(object).setAgility(Double.parseDouble(value));
                    break;
                case "break":
                    SkillAPI.getMyClassByName(object).setBlockbreak(Double.parseDouble(value));
                    break;
                case "combo":
                    SkillAPI.getMyClassByName(object).setCombo(Double.parseDouble(value));
                    break;
                case "desc":
                    SkillAPI.getMyClassByName(object).setDescription(value);
                    break;
                case "health":
                    SkillAPI.getMyClassByName(object).setHealth(Double.parseDouble(value));
                    break;
                case "jump":
                    SkillAPI.getMyClassByName(object).setJump(Double.parseDouble(value));
                    break;
                case "resist":
                    SkillAPI.getMyClassByName(object).setResist(Double.parseDouble(value));
                    break;
                case "sneak":
                    SkillAPI.getMyClassByName(object).setSneak(Double.parseDouble(value));
                    break;
                case "speed":
                    SkillAPI.getMyClassByName(object).setSpeed(Double.parseDouble(value));
                    break;
                case "strength":
                    SkillAPI.getMyClassByName(object).setStrength(Double.parseDouble(value));
                    break;
                case "parent":
                    SkillAPI.getMyClassByName(object).setParent(SkillAPI.getMyClassByName(value));
                    break;
                case "materialI":
                    CItem.getCrucialItemByName(object).setMaterial(value);
                    break;
                case "addLore":
                    CItem.getCrucialItemByName(object).addLore(value);
                    break;
                    /*
                case "addCommand":
                    ((MyItem)CItem.getCrucialItemByName(object)).addCommand(value);
                    break;
                case "recipeI":
                    String[] items = value.split(", ");
                    CItem.getCrucialItemByName(object).setCrafting(items);
                    break;
                case "durability":
                    ((MyItem)CItem.getCrucialItemByName(object)).setDurability(Integer.parseInt(value));
                    break;
                case "addSkill":
                    MySkill mySkill = SkillAPI.getMySkillByName(value);
                    ((MyItem)CItem.getCrucialItemByName(object)).addSkill(mySkill);
                    break;
                     */
                case "addEffect":
                    String[] effect = value.split(", ");
                    ((MyDrug)CItem.getCrucialItemByName(object)).addEffect(new String[]{effect[0], effect[1]});
                    break;
                case "bloody":
                    ((MyDrug)CItem.getCrucialItemByName(object)).setBloody(Boolean.getBoolean(value));
                    break;
                case "particle":
                    ((MyDrug)CItem.getCrucialItemByName(object)).setParticle(value);
                    break;
                case "delay":
                    ((MyDrug)CItem.getCrucialItemByName(object)).setEffectDelay(Integer.parseInt(value));
                    break;
                case "duration":
                    ((MyDrug)CItem.getCrucialItemByName(object)).setDuration(Integer.parseInt(value));
                    break;
                case "overdose":
                    ((MyDrug)CItem.getCrucialItemByName(object)).setOverdose(Integer.parseInt(value));
                    break;
                case "addiction":
                    ((MyDrug)CItem.getCrucialItemByName(object)).setAddict(Integer.parseInt(value));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Command on \"" + command + "\"");
            }
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        } catch (IndexOutOfBoundsException ignore){

        }
        return true;
    }
}