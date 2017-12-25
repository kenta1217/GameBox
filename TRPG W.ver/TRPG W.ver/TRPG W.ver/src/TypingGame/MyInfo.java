package TypingGame;

import java.io.*;

public class MyInfo {

    public static int Item;

    //児玉光希
    //ルールとストーリーが書かれているテキストファイルを読み込んで出力する関数
    public static void showRule() { //時間差でルール一行ずつ表示されていくメソッド
              
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/Rule.txt")));
            String str = br.readLine();
            while (str != null) {
                System.out.print(str);
                str = br.readLine();
                TypeSystem.getTypeOnTime(2);
            }
        } catch (Exception e) {

        }
    }

    public static void showStoryRule() { //時間差でルール一行ずつ表示されていくメソッド
          try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/storyRule.txt")));
            String str = br.readLine();
            while (str != null) {
                if (str.equals("SECTION") == false) {
                    System.out.println(str);
                } else {
                    TypeSystem.getType();
                }
                str = br.readLine();
            }
            TypeSystem.getType();
            System.out.println("---------------------------------------------");
            Thread.sleep(500);

        } catch (Exception e) {

        }
    }

    public static void showFightRule() { //時間差でルール一行ずつ表示されていくメソッド
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/fightRule.txt")));
            String str = br.readLine();
            while (str != null) {
                if (str.equals("SECTION") == false) {
                    System.out.println(str);
                } else {
                    TypeSystem.getType();
                }
                str = br.readLine();
            }
            TypeSystem.getType();
            System.out.println("---------------------------------------------");
            Thread.sleep(500);

        } catch (Exception e) {

        }
    }

    public static void showQuizRule() { //時間差でルール一行ずつ表示されていくメソッド
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/QuizRule.txt")));
            String str = br.readLine();
            while (str != null) {
                System.out.println(str);
                str = br.readLine();
            }
            TypeSystem.getType();
            System.out.println("---------------------------------------------");
            Thread.sleep(500);
        } catch (Exception e) {

        }
    }
    //笠松健太
    //HPを初期化する関数
/*
    public static void resetHP() {
        MyHP = 10000;
    }
     */
    //笠松健太
    //アイテムの所持数を初期化する関数
    public static void resetItem() {
        Item = 0;
    }

    //高野康太
    //アイテムの所持数を返す関数
    public static int getItemNum() {
        return Item;
    }

    //笠松健太
    //アイテムの所持数を増やす関数
    public static void getItem() {
        Item++;
    }
}
