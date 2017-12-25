/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingGame.fight.weapon;

import TypingGame.TypeSystem;
import TypingGame.fight.Fight;

/**
 *
 * @author Admin
 */
public class TwinSword extends Weapon {

    int[] ANum = new int[3];
    int AttackNum = 0;
    int Combo = 0;
    double CTdef = 0.5;

    public TwinSword() {
        Attack0 = 90; //小攻撃威力
        Attack1 = 5;//中攻撃威力 300 　最大　450
        Attack2 = 15;//大攻撃威力 1500　最大 2250
        Attack3 = 25;//特大攻撃威力 2500 最大 3750

        ANum[0] = Attack1;
        ANum[1] = Attack2;
        ANum[2] = Attack3;

        WeaponName = "双剣";

        cure0 = 3000;//小攻撃威力
        cure1 = 5000;//中攻撃
        cure2 = 10000;//特大攻撃威力

        speed = 7; //デフォルトタイム
        speedLimit = 20;//タイム上限
        AttackText[0] = "連続切り";//連続技
        AttackText[1] = "五連切り";//威力中
        AttackText[2] = "十五連切り";//威力大
        AttackText[3] = "二十五連切り";//威力特大

        AttackEffectFlag = true;//攻撃後の攻撃効果の有無
        textComand = "";

        CTRate = 1.8;
    }

    public static void showSpecialEffect() {//武器説明を見る。
        System.out.println("武器説明：双剣--------------------");
        System.out.println("連続攻撃を得意とする武器。\n通常攻撃が一定数の連撃となっており、一ターン中の総合連撃回数に応じて、クリティカル率が高まる。");
        System.out.println("また、HPが少なくなると攻撃力が上がる");
    } //武器説明を見る。

    public void setSpeed() {
        TypeSystem.setDEFAULT_TIME(speed);
    }//武器によってのタイピング時間への影響

    public void SpecialAttack(Fight fight, int num) {
        try {
            int multD = 0;
            if (num != 1) {
                for (int i = 0; i < ANum[num - 2]; i++) {
                    AttackNum++;
                    double damege = this.isCritical() * Attack0 * damegeHp(fight);
                    System.out.print("自）" + AttackNum + "連撃目 " + (int) damege + "ダメージを与えた(" + fight.getEnemyHp());
                    fight.NoMessageCalcDamage((int) damege);
                    multD += damege;
                    Thread.sleep(200);
                    System.out.println("→" + fight.getEnemyHp() + ")");
                    CT = AttackNum * 0.03 + CTdef;

                }
                System.out.println("\n自）" + AttackNum + "連撃！！敵に合計" + multD + "のダメージを与えた");
                Thread.sleep(1000);
            } else {
                CT = 0.3;
                Combo = fight.getCombo();
                if (Combo != 0) {
                    System.out.println("自）カイの攻撃！！");
                    for (int i = 0; i < (int) (Combo); i++) {
                        double Damage = this.isCritical() * Attack0 * (1 + (i * 0.40));
                        System.out.print("自）" + (i + 1) + "回" + (int) Damage + "ダメージを与えた(" + fight.getEnemyHp());
                        fight.NoMessageCalcDamage((int) Damage);
                        multD += Damage;
                        TypeSystem.stop(200);
                        System.out.println("→" + fight.getEnemyHp() + ")");
                    }
                    TypeSystem.stop(500);
                    System.out.println("\n自）" + (int) (Combo) + "連撃！！敵に合計 " + multD + " のダメージを与えた");
                    TypeSystem.stop(700);

                }
            }
            fight.changeTurn(1);
        } catch (Exception e) {

        }
    }

    public void SpecialEffect(Fight fight) {
        if (AttackNum != 0 || CT != 0) {
            if (AttackNum != 0) {
                AttackNum = 0;
                System.out.println("攻撃回数がリセットされた");
            }
            if (CT != 0) {
                System.out.println("クリティカル率がリセットされた");
                CT = 0;
            }
            System.out.println("");
        }

    }//特殊効果の発動（敵ターン時に）

    public boolean getAttackEffectflag() {
        return AttackEffectFlag;
    }

    public double damegeHp(Fight fight) {
        if (((double) fight.getMyHp() / 10000) < 0.3) {
//            System.out.println(fight.getMyHp() / 10000);
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public double isCritical() {
        if (CT > Math.random()) {
            System.out.println("クリティカルヒット！！");
            return CTRate;
        } else {
            return 1;
        }
    }
}
