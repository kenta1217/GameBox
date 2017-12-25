/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author 高野康太
 */
public class TypeSystem {

    private static double DEFAULT_TIME = 5;  //デフォルトのタイピング時間
    private static double MAX_TIME = 10;     //タイピング時間の上限
    private static double sec = DEFAULT_TIME;//タイピング時間
    private static double UsedTime = 0.0;   //所要時間
    private static double ExtraTime = 0.0; //余った時間

    public static String getTypeOnTime() {//変数secの宣言時間を設け、タイピングを行う関数
        String str = "TIME  OUT";
        Calendar end = Calendar.getInstance();
        double StartTime = end.getTimeInMillis();
        end.add(Calendar.SECOND, (int) sec);
        end.add(Calendar.MILLISECOND, (int) (sec * 1000 - (int) sec * 1000));

        try {
            InputStreamReader is = new InputStreamReader(System.in, "Shift_JIS");
            BufferedReader br = new BufferedReader(is);

            while (Calendar.getInstance().before(end)) {
                if (br.ready()) {
                    str = br.readLine();
                    long StopTime = Calendar.getInstance().getTimeInMillis();
                    UsedTime = (StopTime - StartTime) / 1000;
                    ExtraTime = sec - UsedTime;
                    System.out.println("\n所要時間 : " + String.format("%.1f", UsedTime) + "秒");
                    System.out.println("余り時間 : " + String.format("%.1f", ExtraTime) + "秒");
                    return str;
                }
            }
        } catch (Exception e) {
        }
        return str;
    }

    public static String getTypeOnTime(int time) { //引数timeの制限時間を設け、タイピングを行う関数
        String str = "TIME  OUT";
        Calendar end = Calendar.getInstance();
        end.add(Calendar.SECOND, time);

        try {
            InputStreamReader is = new InputStreamReader(System.in, "Shift_JIS");
            BufferedReader br = new BufferedReader(is);
            while (Calendar.getInstance().before(end)) {
                if (br.ready()) {
                    str = br.readLine();
                    long Calnum = Calendar.getInstance().getTimeInMillis();
                    return str;
                }
            }
        } catch (Exception e) {
        }
        System.out.println("");
        return str;
    }

    public static String getType() {//制限時間なしでタイピングを行う関数
        while (true) {
            try {
                InputStreamReader is = new InputStreamReader(System.in, "Shift_JIS");
                BufferedReader br = new BufferedReader(is);
                String str = br.readLine();
                return str;
            } catch (Exception e) {
            }
        }
    }

    public static void setSecond(double second) {//制限時間を設定する関数
        sec = second;
    }

    public static void setDEFAULT_TIME(int second) {//制限時間を設定する関数
        DEFAULT_TIME = second;
        sec = second;
    }

    public static void setMAX_SECOND(int maxSecond) {
        MAX_TIME = maxSecond;
    }

    public static void resetSecond() {//制限時間をデフォルトの状態に戻す関数
        if (sec != DEFAULT_TIME) {
            sec = DEFAULT_TIME;
            System.out.println("タイピング時間が元に戻りました\n");
        }
    }

    public static void getTimeBonus() {//タイピング時間に余った時間を足す関数
        if ((DEFAULT_TIME + ExtraTime) >= MAX_TIME) {
            System.out.println(String.format("%.1f", ExtraTime) + "秒 のボーナスを獲得しました");
            sec = MAX_TIME;
            System.out.println("タイピング時間が上限に達しました\n");
        } else {
            System.out.println(String.format("%.1f", ExtraTime) + "秒 のボーナスを獲得しました");
            sec = DEFAULT_TIME + ExtraTime;
        }
        TypeSystem.stop(500);
    }

    public static void getTimeBonus(double time) {
        if ((sec + time) < MAX_TIME) {
            sec += time;
            System.out.println(String.format("%.1f", time) + "秒 のボーナスを獲得しました");
        } else {
            sec = MAX_TIME;
            System.out.println("タイピング時間が上限に達しました\n");
        }
    }

    public static boolean isMaxTime() {
        if (sec == MAX_TIME) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isShortTime() {
        if (sec <= DEFAULT_TIME) {
            return true;
        } else {
            return false;
        }
    }

    public static void missType() {//タイピングミスをした際の制限時間の設定をする関数
        if ((sec - UsedTime) >= DEFAULT_TIME) {
            sec -= UsedTime;
            System.out.println("タイピング時間が " + String.format("%.1f", UsedTime) + "秒 減少しました\n");
            stop(500);
        }
    }

    public static void missType2() {//タイピングミスをした際の制限時間の設定をする関数
        if ((sec - UsedTime) >= DEFAULT_TIME) {
            sec -= UsedTime;
            System.out.println("タイピング時間が " + String.format("%.1f", UsedTime) + "秒 減少しました\n");
            stop(500);
        } else {
            resetSecond();
        }
    }

    public static void getTimePenalty(double Penalty) {
        sec -= Penalty;
    }

    public static double getSecond() {//設定された制限時間を返す関数
        return Double.parseDouble(String.format("%.1f", sec));
    }

    public static double getMAX_TIME() {
        return Double.parseDouble(String.format("%.1f", MAX_TIME));
    }

    public static double getDEFAULT_TIME() {
        return Double.parseDouble(String.format("%.1f", DEFAULT_TIME));
    }

    public static double getUsedTime() {
        return Double.parseDouble(String.format("%.1f", UsedTime));
    }

    public static void stop(int MilliSecond) {
        try {
            Thread.sleep(MilliSecond);
        } catch (Exception e) {

        }
    }
}
