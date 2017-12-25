package TypingGame.fight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class SkillLibrary {

    Random r = new Random();
    List<String[]> Superlist = new ArrayList<String[]>();
    List<String[]> Hardlist = new ArrayList<String[]>();
    List<String[]> Normallist = new ArrayList<String[]>();
    List<String[]> Easylist = new ArrayList<String[]>();

    List<String[]> SuperMagic = new ArrayList<String[]>();
    List<String[]> NormalMagic = new ArrayList<String[]>();
    List<String[]> EasyMagic = new ArrayList<String[]>();

    public SkillLibrary() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/TypingGame/skillBook.csv")));
            String str = br.readLine();
            while (str != null) {
                String[] str2 = str.split(",", 0);

                if (str2[3].equals("小")) {
                    Easylist.add(str2);
                } else if (str2[3].equals("中")) {
                    Normallist.add(str2);
                } else if (str2[3].equals("大")) {
                    Hardlist.add(str2);
                } else if (str2[3].equals("特大")) {
                    Superlist.add(str2);
                }

                str = br.readLine();

            }

            br = new BufferedReader(new FileReader(new File("./src/TypingGame/MagicBook.csv")));
            str = br.readLine();
            while (str != null) { //読み取りファイル：csv　構文：タイピング本文、ひらがな変換文,威力

                String[] str2 = str.split(",", 0);

                if (str2[3].equals("小")) {
                    EasyMagic.add(str2);
                } else if (str2[3].equals("中")) {
                    NormalMagic.add(str2);
                } else if (str2[3].equals("全")) {
                    SuperMagic.add(str2);
                }

                str = br.readLine();

            }
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
    }

    public String[] getSkill(int num) {
        int n;
        String[] s = {"NULL"};
        switch (num) {
            case 1:
                n = r.nextInt(Easylist.size());
                s = Easylist.get(n);
                break;
            case 2:
                n = r.nextInt(Normallist.size());
                s = Normallist.get(n);
                break;
            case 3:
                n = r.nextInt(Hardlist.size());
                s = Hardlist.get(n);
                break;
            case 4:
                n = r.nextInt(Superlist.size());
                s = Superlist.get(n);
                break;
        }
        return s;
    }

    public String[] getMagic(int num) {
        int n;
        String[] s = {"NULL"};
        switch (num) {
            case 1:
                n = r.nextInt(EasyMagic.size());
                s = EasyMagic.get(n);
                break;
            case 2:
                n = r.nextInt(NormalMagic.size());
                s = NormalMagic.get(n);
                break;
            case 3:
                n = r.nextInt(SuperMagic.size());
                s = SuperMagic.get(n);
                break;
        }
        return s;
    }

}
