package TypingGame.QuestionGame;
import TypingGame.MyInfo;
import TypingGame.TypeSystem;

/**
 * @author 高野康太
 */
public class QuestionGameClass {  //謎解きを行うクラス

    QuestionLibrary ql = new QuestionLibrary();
    int storyNum =0; //階層
    String[] question; 
    
    public boolean playQuestion(int num) {//謎解きを行う関数。引数の階層のなぞ解きを行う
                                            //num=階層
        try {
            boolean pandN = false;//階層(引数)ごとの問題
            storyNum = num;
            System.out.println("===============" + storyNum + "階===============");
            System.out.println("問題を読み、適切な選択肢１～４を入力せよ。");
            showQuestion();
            while (true) {
                String answer = question[6];
                String panswer = "F";
                System.out.print("\n入力欄：");
                String in = TypeSystem.getType();
                if (in == null) {
                } else {
                    switch (in) {
                        case "１":
                            panswer = "A";
                            break;

                        case "２":
                            panswer = "B";
                            break;

                        case "３":
                            panswer = "C";
                            break;

                        case "４":
                            panswer = "D";
                            break;
                        
                        default:
                            panswer = "F";
                            break;
                    }
                }
                if (answer.equals(panswer)) {
                    System.out.println("\n正解！！");
                    System.out.println("アイテムを１つ入手しました！！");
                    Thread.sleep(1000);
                    MyInfo.getItem();
                    System.out.println("");
                    return true;
                } else if (panswer.equals("F")) {
                    System.out.println("\n正しい選択肢を入力してください\n");
                    Thread.sleep(500);
                    showQuestion();
                } else {
                    System.out.println("\n不正解…");
                    System.out.println("");
                    Thread.sleep(500);
                    return false;
                }
                
            }
        } catch (Exception e) {
            System.out.println("エラーが発生しました");
            System.out.println(e.getMessage());
            return false;
        }
    }

    //問題と選択肢を表示する関数
    public void showQuestion() {//
       question= ql.getQuestionList(storyNum);
        System.out.println("Q" + storyNum + ": " + question[1]);//階層(引数)ごとの問題
        System.out.print("選択肢：");
        char k = '１';
        for (int i = 2; i < 6; i++) {
            System.out.print(k + "：" + question[i] + "   ");
            k++;
        }
    }    
}
