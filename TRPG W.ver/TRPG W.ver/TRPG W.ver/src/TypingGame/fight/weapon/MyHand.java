/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingGame.fight.weapon;

import TypingGame.fight.Fight;

/**
 *
 * @author c0115138
 */
public class MyHand extends Weapon {

    int Attack0 = 40;//連撃
    int Attack1 = 500;//威力中
    int Attack2 = 800;//威力大
    int Attack3 = 1200;//威力特大

    int cure0 = 3000;//小回復の回復量
    int cure1 = 5000;//中回復の回復量
    int cure2 = 10000;//全回復の回復量

    double default_defence = 1;//デフォルトの防御力
    double defence1 = 0.7;//小回復防御力
    double defence2 = 0.5;//中回復防御力
    double defence3 = 0;//全回復防御力

    double CT = 0.6;//会心発生率
    double CTRate = 3;//ダメージ倍率

    String[] AttackText = {"でこピンラッシュ", "チョップ", "ラリアット", "正拳突き"};
    String WeaponName = "素手";

    int speed = 10;//タイム    
    int speedLimit = 20;//タイム上限
    boolean AttackEffectFlag = false;//攻撃自の効果の有無
    boolean SPCF = false;//特殊コマンドの有無

    public void getWeaponStatus() {
        System.out.println("武器の種類\t\t\t" + getWeaponName());
        System.out.println("デフォルトタイピング時間\t" + getSpeed() + "秒");
        System.out.println("タイピング時間の上限\t\t" + getSpeedLimit() + "秒");
        System.out.println("連撃 : " + getEAttack() + "\t威力中 : " + getNAttack()
                + "\t威力大 : " + getHAttack() + "\t威力特大 : " + getSHAttack());
        System.out.println("");
    }

    public static void showSpecialEffect() {
        System.out.println("武器説明：素手--------------------");
        System.out.println("技の威力が弱いが、タイピング時間が長く、会心発生率が高い");
    } //武器説明を見る。

    public void AttackSpecialEffect(int AttackNum, Fight fight) {
    }//攻撃時に特別な効果をもとに処理を行う。

    public boolean getAttackEffectflag() {
        return false;
    }

    public void SpecialEffect(Fight fight) {
    }//特殊効果の発動（敵ターン時に）

    public void SpecialAttack(Fight fight, int power) {

    }

    public String getWeaponName() {
        return WeaponName;
    }

    public String[] getAttackText() {
        return AttackText;
    }

    public int getEAttack() {
        return Attack0;
    }//連撃攻撃のもと威力

    public int getNAttack() {
        return Attack1;
    }//中攻撃

    public int getHAttack() {
        return Attack2;
    }//大攻撃

    public int getSHAttack() {
        return Attack3;
    }//特大攻撃威力

    public int getSpeed() {//武器によってのタイピング時間
        return speed;
    }

    public int getSpeedLimit() {//武器によってのタイピング時間上限
        return speedLimit;
    }

    public int getECure() {
        return cure0;
    }

    public int getNCure() {
        return cure1;
    }

    public int getHCure() {
        return cure2;
    }

    public double getDefaultDefence() {
        return default_defence;
    }

    public double getEDefence() {
        return defence1;
    }

    public double getNDefence() {
        return defence2;
    }

    public double getHDefence() {
        return defence3;
    }

    public boolean isSpecialComand() {
        return SPCF;
    }

    @Override
    public String toString() {
        return WeaponName;
    }

    public String ComandText() {
        return textComand;
    }

    public double isCritical() {
        if (CT > Math.random()) {
            System.out.println("クリティカルヒット！！");
            return CTRate;
        } else {
            return 1;
        }
    }
}
