/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingGame.fight;

import TypingGame.*;
import java.util.Scanner;
import TypingGame.fight.weapon.*;

/**
 *
 * @author Admin
 */
public class TypingSkill {

    SkillLibrary skill = new SkillLibrary();

    public String letTyping(int EnemyHP, Weapon wepon) {

        int num = 0;
        String[] Skill;// 技番号,技名,ひらがな,威力
        String[] AttackText = wepon.getAttackText();
        String SkillName;
        String SkillKana;

        String returnString = "";
        while (true) {
            System.out.println("\n        敵HP：" + EnemyHP);
            System.out.println("技の威力を選んでください  " + "保有時間：" + TypeSystem.getSecond() + " / "+ TypeSystem.getMAX_TIME() + " 秒");
            System.out.print("１:" + AttackText[0] + "(" + wepon.getEAttack() + ")　２:" + AttackText[1] + "(" + wepon.getNAttack() + ")　３:" + AttackText[2] + "(" + wepon.getHAttack() + ") ４:" + AttackText[3] + "(" + wepon.getSHAttack() + ")　５：戻る\n>> :");
            String i = TypeSystem.getType();
            if (i.equals("１")) {
                System.out.println(AttackText[0] + "を選びました");
                num = 1;
                returnString += "1";
                break;
            } else if (i.equals("２")) {
                System.out.println(AttackText[1] + "を選びました");
                num = 2;
                returnString += "2";
                break;
            } else if (i.equals("３")) {
                System.out.println(AttackText[2] + "を選びました");
                num = 3;
                returnString += "3";
                break;
            } else if (i.equals("４")) {
                System.out.println(AttackText[3] + "を選びました");
                num = 4;
                returnString += "4";
                break;
            } else if (i.equals("５")) {
                return " 55";
            } else {
                System.out.println("正しく選択肢を入力してください");
            }
        }
        if (num == 1) {
            String TypedSkill = "TypingString";
            int Combo = 0;
            System.out.print("連続タイピングを開始します。3..");
            TypeSystem.stop(1000);
            System.out.print("2..");
            TypeSystem.stop(1000);
            System.out.print("1..");
            TypeSystem.stop(1000);
            System.out.println("開始!!");
            while (true) {
                Skill = skill.getSkill(num);
                SkillName = Skill[1];
                SkillKana = Skill[2];
                showTypingWord2(SkillName, SkillKana);
                TypedSkill = TypeSystem.getTypeOnTime();

                if (TypedSkill.equals(SkillKana)) {
                    Combo++;
                    System.out.println("\n" + Combo + "コンボ！！");
                    TypeSystem.stop(800);
                } else if (TypedSkill.equals("TIME  OUT")) {
                    System.out.println("\n連撃終了");
                    break;
                } else {
                    System.out.println("\nタイピング失敗...");
                }
                TypeSystem.setSecond(TypeSystem.getSecond() - TypeSystem.getUsedTime());
            }
            if (Combo != 0) {
                System.out.println("\n" + Combo + "連撃成功！！！");
            } else {
                System.out.println("\n連撃失敗...");
            }
            TypeSystem.stop(1000);
            System.out.println("");
            TypeSystem.resetSecond();

            return returnString + Combo;

        } else {
            Skill = skill.getSkill(num);
            SkillName = Skill[1];
            SkillKana = Skill[2];

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
            showTypingWord2(SkillName, SkillKana);
            String TypedSkill = TypeSystem.getTypeOnTime();
            if (TypedSkill.equals(SkillKana)) {
                System.out.println("\nタイピング成功\t");
                   returnString += "1";
                if (num == 4) {
                    TypeSystem.resetSecond();
                }
            } else if (TypedSkill.equals("TIME  OUT")) {
                System.out.println("\n時間切れ");
                TypeSystem.stop(500);
                returnString += "0";
                TypeSystem.resetSecond();
            } else {
                System.out.println("\nタイピング失敗");
                returnString += "0";
                TypeSystem.missType2();
            }
        }
        return returnString;
    }

    public void showTypingWord(String SkillName, String SkillKana) {
        System.out.print("　");
        for (int i = 0; i < SkillKana.length(); i++) {
            System.out.print("―");
        }

        System.out.print("　\n｜" + SkillName);

        for (int i = 0; i < (SkillKana.length() - SkillName.length()); i++) {
            System.out.print("　");
        }
        System.out.println("｜");
        System.out.println("｜" + SkillKana + "｜");
        System.out.print("　");
        for (int i = 0; i < Math.max(SkillName.length(), SkillKana.length()); i++) {
            System.out.print("―");
        }
        System.out.print("\n　");
    }

    public void showTypingWord2(String SkillName, String SkillKana) {

        for (int i = 0; i < SkillKana.length(); i++) {
            System.out.print("―");
        }
        System.out.println("");
        System.out.println(SkillName);

        System.out.println(SkillKana);
        for (int i = 0; i < Math.max(SkillName.length(), SkillKana.length()); i++) {
            System.out.print("―");
        }
        System.out.println("");
    }
}
