/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingGame.QuestionGame;

import java.util.*;
import java.io.*;

/**
 *
 * @作成者　高野康太
 */
public class QuestionLibrary { //階層ごとのクイズを保持するクラス

    List list = new ArrayList();
    List<String[]>[] story = new List[15];//配列数は階層に等しい

    public static int test = 0;
    private int[][] answer = {{2}, {0, 1, 2, 3, 4}, {0}, {1}};
    Random r = new Random();

    public QuestionLibrary() {  //csvファイル”QuestionBook.csv”を参照し、文を階層要素を見ながら、List型配列のArraylistに分け代入していくコンストラクタ。
        try {
            for (int i = 0; i < story.length; i++) {//List型配列に
                story[i] = new ArrayList<String[]>();
            }
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/QuestionGame/Question.csv")));
            String str = br.readLine();
            while (str != null) {
                String[] str2 = str.split(",", 0);
                int i2 = Integer.parseInt(str2[0]);
                story[i2].add(str2);
                str = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Question Exception");
            System.out.println(e.getMessage());
        }
    }

    public String[] getQuestionList(int num) {// 引数numの値に格納された{階層,問題,選択肢A,選択肢B,選択肢C,選択肢D,答え}の入っている配列を返す関数
        int size = story[num].size();
        return story[num].get(0);
    }

}
