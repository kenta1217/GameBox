package TypingGame.fight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//クラス作成者　井上
//全ての敵の情報を表示するクラス
public class EnemyInfo {

    String enemyName = "null";
    int enemyHp = 1;
    int enemyAttack;
    List<String[]> enemyInfo = new ArrayList<String[]>();
    List<String[]>[] story = new List[15];//配列数は階層に等しい
    String[] artURL = {"./src/TypingGame/fight/AA/saramanda-.txt", "./src/TypingGame/fight/AA/iwanomajin.txt", "./src/TypingGame/fight/AA/sinkunog.txt", "./src/TypingGame/fight/AA/juerugon.txt", "./src/TypingGame/fight/AA/yaminosyokusyu.txt", "./src/TypingGame/fight/AA/maounokage.txt", "./src/TypingGame/fight/AA/marudhiasu.txt"};

    public void ShowAA(int num) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(artURL[((num) / 2) - 1])));
            String str = br.readLine();
            while (str != null) {
                System.out.println(str);

                str = br.readLine();
            }

        } catch (Exception e) {

        }

    }
    int i;

    public EnemyInfo() {//敵の情報を保持する関数
        try {

            for (i = 0; i < story.length; i++) {//List型配列に
                story[i] = new ArrayList<String[]>();
            }
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/fight/Enemy.csv")));

            String str = br.readLine();
            while (str.equals("") == false) {
                String[] str2 = str.split(",", 0);
                int i2 = Integer.parseInt(str2[0]);//先頭に格納されている階層数をInt型にする
                story[i2].add(str2);
                str = br.readLine();
            }
        } catch (Exception e) {
        }
    }

    public String[] getEnemyInfo(int Floor) { //敵の情報の入った配列を返す関数
        return story[Floor].get(0);
    }
}
