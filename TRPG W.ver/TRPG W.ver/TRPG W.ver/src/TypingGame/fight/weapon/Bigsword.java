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
public class Bigsword extends Weapon {

    public Bigsword() {
        Attack0 = 100; //小攻撃威力
        Attack1 = 500;//中攻撃威力
        Attack2 = 3000;//大攻撃威力
        Attack3 = 6000;//特大攻撃威力
        WeaponName = "大剣";

        cure0 = 3000;//小攻撃威力
        cure1 = 5000;//中攻撃
        cure2 = 10000;//特大攻撃威力
        
        default_defence = 0.8;

        speed = 3; //デフォルトタイム
        speedLimit = 20;//タイム上限
        AttackText[0] = "連続パンチ";//連続技
        AttackText[1] = "たたきつける";//威力中
        AttackText[2] = "薙ぎ払う";//威力大
        AttackText[3] = "たたき切る";//威力特大

        AttackEffectFlag = false;//攻撃後の攻撃効果の有無
        textComand="";
    }

    public static void showSpecialEffect() {//武器説明を見る。
        System.out.println("武器説明：大剣--------------------");
        System.out.println("特大や大技の威力がとても高く、強攻撃を得意とする反面、小および中技の威力は低く弱攻撃は不得意である。");
        System.out.println("攻撃をヒットさせると威力に応じて、次の攻撃に時間ボーナスを得る。");
    } 

    public void setSpeed() {
        TypeSystem.setDEFAULT_TIME(speed);
    }//武器によってのタイピング時間への影響

    public void AttackSpecialEffect(int AttackNum, Fight fight) {
        TypeSystem.setSecond(TypeSystem.getDEFAULT_TIME());
        switch (AttackNum) {
            case 1:
                TypeSystem.getTimeBonus(1);
                System.out.println("次のタイピング時間が1秒増えた");
                break;
            case 2:
                TypeSystem.getTimeBonus(3);
                System.out.println("次のタイピング時間が3秒増えた");
                break;
            case 3:
                TypeSystem.getTimeBonus(5);
                System.out.println("次のタイピング時間が5秒増えた");

                break;
            case 4:
                TypeSystem.getTimeBonus(5);
                System.out.println("次のタイピング時間が5秒増えた");
                break;
        }
  //      fight.changeTurn(1);
    }//攻撃時に特別な効果をもとに処理を行う。

    public void SpecialEffect(Fight fight) {
    }//特殊効果の発動（敵ターン時に）

    public boolean getAttackEffectflag() {
        return AttackEffectFlag;
    }
}
