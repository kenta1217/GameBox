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
public class Knife extends Weapon {

    public Knife() {
        Attack0 = 200; //小攻撃威力
        Attack1 = 500;//中攻撃威力
        Attack2 = 1000;//大攻撃威力
        Attack3 = 1500;//特大攻撃威力

        cure0 = 3000;//小攻撃威力
        cure1 = 5000;//中攻撃
        cure2 = 10000;//特大攻撃威力

        speed = 7; //デフォルトタイム
        speedLimit = 15;//タイム上限
        AttackText[0] = "連技";//連続技
        AttackText[1] = "切る";//威力中
        AttackText[2] = "切りつける";//威力大
        AttackText[3] = "急所突き";//威力特大
        WeaponName = "短剣";

        CT = 0.3;
        CTRate = 1.5;

        AttackEffectFlag = false;//攻撃後の攻撃効果の有無
    }

    public static void showSpecialEffect() {//武器説明を見る。
        System.out.println("武器説明：短剣--------------------");
        System.out.println("全体的に技の威力が低いがタイピング時間が長く、攻撃がクリティカルヒットすることがある。");
        System.out.println("敵にマヒ状態を付与することがあり、威力の高い技ほどマヒになりやすい。");
    } //武器説明を見る。

    public boolean isSpecialComand() {
        return true;
    }

    public void setSpeed() {
        TypeSystem.setDEFAULT_TIME(speed);
    }//武器によってのタイピング時間への影響

    public int AttackSave;

    public void AttackSpecialEffect(int AttackNum, Fight fight) {
        double d = Math.random();
        switch (AttackNum) {
            case 1:
                if (d < 0.10) {
                    System.out.println("敵をマヒにした");
                    fight.changeTurn(0);
                }
                break;

            case 2:
                if (d < 0.20) {
                    System.out.println("敵をマヒにした");
                    fight.changeTurn(0);
                }
                break;
            case 3:
                if (d < 0.30) {
                    System.out.println("敵をマヒにした");
                    fight.changeTurn(0);
                }
                break;
            case 4:
                if (d < 0.40) {
                    System.out.println("敵をマヒにした");
                    fight.changeTurn(0);
                }
                break;
        }

    }//攻撃時に特別な効果をもとに処理を行う。

    public void SpecialEffect(Fight fight) {

    }//特殊効果の発動（敵ターン時に）

    public boolean getAttackEffectflag() {

        return AttackEffectFlag;
    }
}
