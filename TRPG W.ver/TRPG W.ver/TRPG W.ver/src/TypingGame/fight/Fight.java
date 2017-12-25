//作成者児玉
package TypingGame.fight;

import TypingGame.*;
import TypingGame.fight.weapon.*;

public class Fight {

    int MyHP = 10000;
    int Combo;
    double CausedDamage = 0;

    String EnemyName;
    int EnemyMaxHP;
    int EnemyHP;
    int EnemyPower;
    int turn;//0→主人公のターン、1→敵のターン
    double defence = 1;

    int Attack0;//小
    int Attack1; //中
    int Attack2;//大
    int Attack3;//特大

    int cure0 = 3000;//小回復の回復量
    int cure1 = 5000;//中回復の回復量
    int cure2 = 10000;//全回復の回復量

    double default_defence = 1;
    double defence0 = 0.7;//小回復防御力
    double defence1 = 0.5;//中回復防御力
    double defence2 = 0;//全回復防御力

    Weapon weapon;
    String WeaponName;
    boolean AEflag = false;//攻撃時効果フラグ
    boolean Comandflag = false;//コマンドフラグ

    public String ReturnString;
    public char ReturnChar[];
    EnemyInfo ene = new EnemyInfo();
    TypingSkill ts = new TypingSkill();
    TypeMagic tm = new TypeMagic();

    //高野康太
    //敵の情報を取得して表示する関数
    public void battle(int Floor) {
        String[] str = ene.getEnemyInfo(Floor);
        EnemyName = str[1];
        EnemyHP = Integer.parseInt(str[2]);
        EnemyMaxHP = EnemyHP;
        EnemyPower = Integer.parseInt(str[3]);

        System.out.println("");
        turn = 0;

        System.out.println("===============" + str[0] + "階===============");
        TypeSystem.stop(1000);
        ene.ShowAA(Floor);
        selectWepon();

        showFightInfo();
        if (EnemyName.equals("深紅のG")) {
            Gfight:
            while (true) {
                System.out.println("\n行動を選択してください 保有時間：9999秒");
                System.out.print("１:ゴキジェット \n");
                System.out.print(">>");
                String s = TypeSystem.getType();
                if (s.equals("１")) {
                    System.out.println("");
                    System.out.println("下に表示された文字を 9999 秒 以内にひらがなで打て！！");
                    TypeSystem.stop(500);
                    System.out.print("タイピングを開始します。3..");
                    TypeSystem.stop(1000);
                    System.out.print("2..");
                    TypeSystem.stop(1000);
                    System.out.print("1..");
                    TypeSystem.stop(1000);
                    System.out.println("開始!!");
                    System.out.println("");
                    System.out.println("――――――\nゴキジェット\nごきじぇっと\n――――――");
                    String TypedSkill = TypeSystem.getTypeOnTime(9999);
                    if (TypedSkill.equals("ごきじぇっと")) {
                        System.out.println("\nタイピング成功！");
                        System.out.println("\n自）くらえ！ゴキジェット！！");
                        TypeSystem.stop(1300);
                        System.out.println("自）深紅のGに5000兆のダメージを与えた！！");
                        TypeSystem.stop(1300);
                        System.out.println("深紅のG「ぐえ～～～～！」");
                        TypeSystem.stop(1300);
                        System.out.println("敵を撃破した！！");
                        TypeSystem.stop(1000);
                        break Gfight;
                    } else {
                        System.out.println("\nタイピング失敗...\n");
                        TypeSystem.stop(1000);
                        System.out.println("深紅のGはカサカサ動いている！");
                        TypeSystem.stop(1000);
                        System.out.println("カイは精神的ダメージを受けた！");
                        TypeSystem.stop(1000);
                        System.out.print("カイのＨＰが１になった！(" + MyHP);
                        MyHP = 1;
                        System.out.println("→" + MyHP + ")");
                        TypeSystem.stop(1000);
                    }
                } else {
                    System.out.println("\n選択肢を正しく入力してください\n");
                }
            }
            MyHP = 10000;
        } else {

            fight:
            while (true) {
                while (turn == 0) {
                    System.out.println("\n行動を選択してください 保有時間：" + TypeSystem.getSecond() + " / " + TypeSystem.getMAX_TIME() + " 秒");
                    System.out.print("１:攻撃　２:回復　３:アイテム  ４：ためる  \n");
                    System.out.print(">>");
                    String s = TypeSystem.getType();
                    if (s.equals("１")) {
                        ReturnString = ts.letTyping(EnemyHP, weapon);
                        ReturnChar = ReturnString.toCharArray();
                        if (ReturnChar[0] == '1') {
                            
                            Combo = Integer.parseInt(ReturnString.substring(1));
                            if (!WeaponName.equals("召喚札")) {
                                calcDamage(1);
                            }
                            weapon.AttackSpecialEffect(Character.getNumericValue(ReturnChar[0]), this);

                        } else if (ReturnChar[1] == '1') {

                            calcDamage(Character.getNumericValue(ReturnChar[0]));
                            if (isGameSet()) {
                                break fight;
                            }

                            if (!WeaponName.equals("大剣") && ReturnChar[0] != '4') {
                                TypeSystem.getTimeBonus();
                            }

                            weapon.AttackSpecialEffect(Character.getNumericValue(ReturnChar[0]), this);
                            if (isGameSet()) {
                                break fight;
                            }

                            System.out.println("");
                        } else if (ReturnChar[1] == '0') {
                            turn = 1;
                        }

                    } else if (s.equals("２")) {
                        String returnString = tm.letTyping(MyHP);
                        if (!returnString.equals("TIME OUT")) {
                            ReturnChar = returnString.toCharArray();
                            if (ReturnChar[1] == '1') {
                                cure(Character.getNumericValue(ReturnChar[0]));
                                System.out.println("");
                            }
                        }else{
                            turn = 1;
                        }
                    } else if (s.equals("３")) {
                        if (MyHP == 10000) {
                            System.out.println("\nＨＰはすでに満タンです\n");
                        } else if (MyInfo.getItemNum() != 0) {
                            MyInfo.Item--;
                            int tmp = MyHP;
                            if ((tmp += 7000) >= 10000) {
                                MyHP = 10000;
                            } else {
                                MyHP += 7000;
                            }

                            System.out.println("\nアイテムを１つ使用してＨＰを7000回復しました\n");
                            showFightInfo();
                            System.out.println("");
                        } else {
                            System.out.println("\nアイテムがありません！！\n");
                        }
                    } else if (s.equals("４")) {
                        if (!TypeSystem.isMaxTime()) {
                            System.out.println("自）カイは力を溜め、次の機会を待った。\n");
                            defence *= 1 - (0.02 * TypeSystem.getSecond());
                            TypeSystem.getTimeBonus(TypeSystem.getDEFAULT_TIME());
                            TypeSystem.stop(1000);
                            turn = 1;
                        } else {
                            System.out.println("タイピング時間はすでに上限に達しています！");
                        }
                    } else {
                        System.out.println("\n選択肢を正しく入力してください\n");
                    }
                }
                weapon.SpecialEffect(this);
                if (isGameSet()) {
                    break fight;
                }

                if (turn == 1) {
                    calcDamage();
                }
                if (isGameSet()) {
                    break fight;
                }
                Combo = 0;
                turn = 0;
                System.out.println("---------------------------------");
                showFightInfo();
                System.out.println("");
            }
        }

    }

    //0 : プレイヤーの攻撃
    //1 : 敵の攻撃
    //ダメージ計算をする関数
    //笠松健太
    String MyHPS;
    String EneHPS;
    String AlertS;

    public void showFightInfo() {
        try {
            MyHPS = String.valueOf(MyHP) + "/10000";
            EneHPS = String.valueOf(EnemyHP) + "/" + String.valueOf(EnemyMaxHP);
            System.out.println("-------Player---------　　　　--------Enemy---------");
            System.out.println("|Name:" + String.format("%" + (15 + "カイ".length() - "カイ".getBytes("Shift_JIS").length) + "s", "カイ") + "|　　　　|"
                    + "Name: " + String.format("%" + (15 + EnemyName.length() - EnemyName.getBytes("Shift_JIS").length) + "s", EnemyName) + "|"
                    + "\n|HP:" + String.format("%" + (17 + MyHPS.length() - MyHPS.getBytes("Shift_JIS").length) + "s", MyHPS) + "|　　　　|"
                    + "HP:" + String.format("%17s", EneHPS) + "|"
                    + "\n|Item:" + String.format("%15s", MyInfo.getItemNum()) + "|　　　　|"
                    + "Power:" + String.format("%14s", String.valueOf(EnemyPower)) + "|");
            System.out.println("----------------------　　　　----------------------");
            if (MyHP < EnemyPower) {
                System.out.println("!!!!HPが危険です!!!!");
            }

        } catch (Exception e) {
        }
    }

    public int getEnemyHp() {
        return EnemyHP;
    }

    public int getMyHp() {
        return MyHP;
    }

    public int getCombo() {
        return Combo;
    }

    public void calcDamage() {
        System.out.println("敵）敵の攻撃！！");
        if (defence == 0) {
            System.out.println("敵）カイは攻撃を完全に防いだ！！");
        } else {
            System.out.print("敵）" + (int) (EnemyPower * defence) + "のダメージを受けた！！(" + MyHP + "→");
            MyHP -= EnemyPower * defence;
            System.out.println(MyHP + ")");
        }
        
        TypeSystem.stop(1000);
        defence = default_defence;
        this.turn = 0;
    }

    public void calcDamage(int Power) {

        int Damage;
        if (Power != 1 && Combo != 0) {
            System.out.println("自）カイの攻撃！！");
        }
        if (weapon.getAttackEffectflag() == false) {
            switch (Power) {
                case 1: 
                    if (Combo != 0) {
                        int FirstEnemyHP  = EnemyHP;
                        System.out.println("自）カイの攻撃！！");
                        int TotalDamage = 0;
                        for (int i = 0; i < Combo; i++) {
                            Damage = (int) (weapon.isCritical() * Attack0 * (1 + (i * 0.3)));
                            System.out.print("自）" + (i + 1) + "回" + Damage + "ダメージを与えた(" + EnemyHP);
                            EnemyHP -= Damage;
                            TotalDamage += Damage;
                            TypeSystem.stop(300);
                            System.out.println("→" + EnemyHP + ")");
                        }
                        TypeSystem.stop(500);
                        System.out.print("\n自）" + Combo + "連撃！！敵に合計 " + TotalDamage + " のダメージを与えた");
                        System.out.println("(" + FirstEnemyHP + "→" + EnemyHP + ")");
                        CausedDamage = TotalDamage;
                        TypeSystem.stop(700);
                        turn = 1;
                    }
                    break;
                case 2: //威力中
                    Damage = (int) (Attack1 * weapon.isCritical());
                    System.out.print("自）敵に" + Damage + "のダメージを与えた！！(" + EnemyHP);
                    EnemyHP -= Damage;
                    System.out.println("→" + EnemyHP + ")");
                    TypeSystem.stop(700);
                    CausedDamage = Damage;
                    turn = 1;
                    break;

                case 3: //威力大
                    Damage = (int) (Attack2 * weapon.isCritical());
                    System.out.print("自）敵に" + Damage + "のダメージを与えた！！(" + EnemyHP);
                    EnemyHP -= Damage;
                    System.out.println("→" + EnemyHP + ")");
                    TypeSystem.stop(700);
                    CausedDamage = Damage;
                    turn = 1;
                    break;

                case 4: //威力特大
                    Damage = (int) (Attack3 * weapon.isCritical());
                    System.out.print("自）敵に" + Damage + "のダメージを与えた！！(" + EnemyHP);
                    EnemyHP -= Damage;
                    System.out.println("→" + EnemyHP + ")");
                    TypeSystem.stop(700);
                    CausedDamage = Damage;
                    turn = 1;
                    break;
            }
        } else {
            weapon.SpecialAttack(this, Power);
        }
        System.out.println("");

    }

    public void NoMessageCalcDamage(int Power) {
        EnemyHP -= Power;
    }

    //0 :小回復
    //1 :中回復
    //2 :全回復
    //HP回復をする関数
    //作成者　井上宗哉
    public void cure(int curePower) {
        switch (curePower) {
            case 1:
                if ((MyHP + cure0) >= 10000) {
                    System.out.print("自）ＨＰが全回復した");
                    MyHP = 10000;
                } else {
                    System.out.print("自）ＨＰが" + cure0 + "回復した");
                    System.out.print("(" + MyHP + "→");
                    MyHP += cure0;
                    System.out.println(MyHP + ")");
                }

                defence *= defence0;
                TypeSystem.stop(1000);

                break;

            case 2:
                if ((MyHP + cure1) >= 10000) {
                    System.out.print("自）ＨＰが全回復した");
                    MyHP = 10000;
                } else {
                    System.out.print("自）ＨＰが" + cure1 + "回復した");
                    System.out.print("(" + MyHP + "→");
                    MyHP += cure1;
                    System.out.print(MyHP + ")");
                }

                defence *= defence1;
                TypeSystem.stop(1000);
                break;

            case 3:
                System.out.print("自）ＨＰが全回復した");
                MyHP = cure2;
                defence *= defence2;
                TypeSystem.stop(1000);
                break;
        }

    }

    public void SpecialCure(double cure) {
        if (MyHP == 10000) {

        } else if ((MyHP + cure) >= 10000) {
            System.out.print("自）ＨＰが全回復した");
            MyHP = 10000;
        } else {
            System.out.print("自）ＨＰが" + (int) cure + "回復した");
            System.out.print("(" + MyHP + "→");
            MyHP += (int) cure;
            System.out.println(MyHP + ")");
        }
        TypeSystem.stop(500);
    }

    public void changeTurn(int turn) {
        this.turn = turn;
    }

    public void selectWepon() {
        while (true) {
            System.out.println("\n武器を選択してください。");
            System.out.print("    １:大剣");
            System.out.print("    ２:短剣");
            System.out.print("    ３:双剣");
            System.out.print("    ４:召喚札");
            System.out.print("    ５:素手");

            System.out.println("");
            System.out.print(">>");
            String s = TypeSystem.getType();

            if (s.equals("１")) {
                System.out.println("");
                weapon = new Bigsword();
                weapon.getWeaponStatus();
                Bigsword.showSpecialEffect();
                if (this.selectTrueorFalse()) {
                    break;
                }
            } else if (s.equals("２")) {
                System.out.println("");
                weapon = new Knife();
                weapon.getWeaponStatus();
                Knife.showSpecialEffect();
                if (this.selectTrueorFalse()) {
                    break;
                }
            } else if (s.equals("３")) {
                System.out.println("");
                weapon = new TwinSword();
                weapon.getWeaponStatus();
                TwinSword.showSpecialEffect();
                if (this.selectTrueorFalse()) {
                    break;
                }
            } else if (s.equals("４")) {
                System.out.println("");
                weapon = new SummonsCard();
                weapon.getWeaponStatus();
                SummonsCard.showSpecialEffect();
                if (this.selectTrueorFalse()) {
                    break;
                }
            } else if (s.equals("５")) {
                System.out.println("");
                weapon = new MyHand();
                weapon.getWeaponStatus();
                MyHand.showSpecialEffect();
                if (this.selectTrueorFalse()) {
                    break;
                }
            }
        }
        this.setWepon();
        System.out.println(WeaponName + "を選択しました\n");
        return;

    }

    public boolean selectTrueorFalse() {
        while (true) {
            System.out.println("\n本当によろしいですか？");
            System.out.println("１:はい    ２：いいえ");
            System.out.print(">>");
            String s = TypeSystem.getType();
            if (s.equals("１")) {
                return true;
            } else if (s.equals("２")) {
                return false;
            } else {
                System.out.print("\n正しく選択肢を入力してください\n");
            }
        }
    }

    public void setWepon() {
        Attack0 = weapon.getEAttack();
        Attack1 = weapon.getNAttack();
        Attack2 = weapon.getHAttack();
        Attack3 = weapon.getSHAttack();
        WeaponName = weapon.getWeaponName();

        cure0 = weapon.getECure();
        cure1 = weapon.getNCure();
        cure2 = weapon.getHCure();

        default_defence = weapon.getDefaultDefence();
        defence = default_defence;
        defence0 = weapon.getEDefence();
        defence1 = weapon.getNDefence();
        defence2 = weapon.getHDefence();

        TypeSystem.setDEFAULT_TIME(weapon.getSpeed());
        TypeSystem.setMAX_SECOND(weapon.getSpeedLimit());
        AEflag = weapon.getAttackEffectflag();
        Comandflag = weapon.isSpecialComand();
    }

    public boolean isGameSet() {
        if (MyHP <= 0) {
            TRPG.Game = 2;
            return true;
        } else if (EnemyHP <= 0) {
            System.out.println("敵を撃破した！");
            TypeSystem.resetSecond();
            MyHP = 10000;
            return true;
        } else {
            return false;
        }
    }

    public double getCausedDamage() {
        return CausedDamage;
    }

}
