package TypingGame.fight;

import TypingGame.*;
import TypingGame.fight.SkillLibrary;

public class TypeMagic {
    
    SkillLibrary skill = new SkillLibrary();
    
    public String letTyping(int MyHP) {
        int num = 0;
        
        String[] Magic;
        String MagicName;
        String MagicKana;
        
        String returnString = "";
        while (true) {
            System.out.println("\n     PlayerHP：" + MyHP);
            System.out.println("回復量を選んでください    " + "保有時間：" + TypeSystem.getSecond() + "秒");
            System.out.print("１：小(3000)　２：中(5000)　３：全(全回復)　４:戻る\n>> :");
            String i = TypeSystem.getType();
            if (i.equals("１")) {
                System.out.println("小回復を選びました");
                num = 1;
                returnString += "1";
                break;
            } else if (i.equals("２")) {
                System.out.println("中回復を選びました");
                num = 2;
                returnString += "2";
                break;
            } else if (i.equals("３")) {
                System.out.println("全回復を選びました");
                num = 3;
                returnString += "3";
                break;
            } else if (i.equals("４")) {
                return "44";
            } else {
                System.out.println("正しく選択肢を入力してください");
            }
        }
        Magic = skill.getMagic(num);
        MagicName = Magic[1];
        MagicKana = Magic[2];
        
        System.out.println("");
        System.out.println("下に表示された文字を " + TypeSystem.getSecond() + "秒 以内にひらがなで打て！！");
        TypeSystem.stop(500);
        System.out.print("タイピングを開始します。3..");
        TypeSystem.stop(1000);
        System.out.print("2..");
        TypeSystem.stop(1000);
        System.out.print("1..");
        TypeSystem.stop(1000);
        System.out.println("開始!!");
        System.out.println("");
        showTypingWord2(MagicName, MagicKana);
        String skillS = TypeSystem.getTypeOnTime();
        if (skillS.equals(MagicKana)) {
            System.out.println("\nタイピング成功\n");
            returnString += "1";
            
            TypeSystem.setSecond(TypeSystem.getSecond() - TypeSystem.getUsedTime());
            
        } else if (skillS.equals("TIME  OUT")) {
            System.out.println("\n時間切れ\n");
            returnString = "TIME OUT";
            TypeSystem.resetSecond();
        } else {
            System.out.println("\nタイピング失敗");
            returnString += "0";
            TypeSystem.missType();
        }
        return returnString;
    }
    
    public void showTypingWord(String MagicName, String MagicKana) {
        System.out.print("　");
        for (int i = 0; i < MagicKana.length(); i++) {
            System.out.print("―");
        }
        
        System.out.print("　\n｜" + MagicName);
        
        for (int i = 0; i < (MagicKana.length() - MagicName.length()); i++) {
            System.out.print("　");
        }
        System.out.println("｜");
        System.out.println("｜" + MagicKana + "｜");
        System.out.print("　");
        for (int i = 0; i < Math.max(MagicName.length(), MagicKana.length()); i++) {
            System.out.print("―");
        }
        System.out.print("\n　");
    }
    
    public void showTypingWord2(String MagicName, String MagicKana) {
        
        for (int i = 0; i < MagicKana.length(); i++) {
            System.out.print("―");
        }
        System.out.println("");
        System.out.println(MagicName);
        
        System.out.println(MagicKana);
        for (int i = 0; i < Math.max(MagicName.length(), MagicKana.length()); i++) {
            System.out.print("―");
        }
        System.out.println("");
    }
}
