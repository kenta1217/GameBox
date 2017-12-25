package TypingGame;

import TypingGame.QuestionGame.*;
import TypingGame.fight.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TRPG {

    public static int Game = 0; // 0:ゲーム中、1:ゲームクリア、2:ゲームオーバー

    //ゲームループ
    //作成者　児玉光希
    //メイン関数
    public static void main(String[] args) {

        int Floor = 0;//階層               
        QuestionGameClass qy = new QuestionGameClass();
        Fight ft = new Fight();
        while (true) {
            if (Game == 2) {
                break;
            }
            if (Floor == 15) {
                Game = 1;
                break;
            } else if (Floor == 0) {
                startManu();
                MyInfo.showStoryRule();
                TypeSystem.stop(700);
            } else if (Floor % 2 == 1) {
                //謎解き
                qy.playQuestion(Floor);
            } else {
                //戦闘
                ft.battle(Floor);
            }
            System.out.println("");
            Floor++;
        }

        if (Game == 1) {
            Ending();
            System.out.println("ゲームクリア");
        } else if (Game == 2) {
            System.out.println("ゲーム―オーバー");
        }

    }

    public static void startManu() {
        System.out.println("このゲームはすべて全角入力です");
        while (true) {
            System.out.println("全角文字を入力してください");
            String s = TypeSystem.getType();
            if (method1(s)) {
                break;

            } else {
                System.out.println("全角ではありません\n");
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/title.txt")));
            String str = br.readLine();
            while (str != null) {
                System.out.println(str);

                str = br.readLine();
            }
        } catch (Exception e) {
        }
        System.out.println("\n");
        while (true) {
            System.out.print("１：ゲームスタート  ２：謎解き説明  ３：戦闘説明\n>>");
            String i = TypeSystem.getType();
            if (i.equals("１")) {
                System.out.println("");
                return;
            } else if (i.equals("２")) {
                MyInfo.showQuizRule();
            } else if (i.equals("３")) {
                MyInfo.showFightRule();
            } else {
                System.out.println("正しく選択肢を入力してください");
            }
        }
    }

    public static boolean method1(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (String.valueOf(chars[i]).getBytes().length < 2) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    
    public static void Ending(){
        System.out.println("Enterで進む");
        TypeSystem.getType();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/Ending.txt")));
            String str = br.readLine();
            String[] strlist=str.split(",");
            while (str != null) {
                if (strlist[0].equals("SECTION") == false) {
                    System.out.println(str);
                } else {
                    if(strlist.length==2){
                        Thread.sleep(Integer.parseInt(strlist[1]));
                    }else{
                    TypeSystem.getType();
                    }
                }
                str = br.readLine();
                strlist=str.split(",");
            }
            TypeSystem.getType();
            Thread.sleep(500);

        } catch (Exception e) {

        }
    }
    
}
