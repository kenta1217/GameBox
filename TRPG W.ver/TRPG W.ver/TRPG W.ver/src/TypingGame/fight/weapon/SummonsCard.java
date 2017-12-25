package TypingGame.fight.weapon;

import TypingGame.TypeSystem;
import TypingGame.fight.Fight;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author c0115138
 */
public class SummonsCard extends Weapon {

    int Monster = 5;
    String MonsterName[] = new String[Monster];
    int MonsterNum[] = new int[Monster];
    int[] MonsterPower = new int[Monster];
    char[] MonsterChar = new char[Monster];
    Map<String, Integer> map = new LinkedHashMap<>();

    Map<String, Integer> SlimeMap = new LinkedHashMap<>();
    int SummonedSlime = 0;
    int SlimeID = 0;

    static int StayTurn = 3;
    int SlimeTurn[] = new int[StayTurn];

    public SummonsCard() {
        Attack0 = 150; //小攻撃威力
        Attack1 = 600;//中攻撃威力
        Attack2 = 800;//大攻撃威力
        Attack3 = 1200;//特大攻撃威力
       int Attack4 = 2000;

        cure0 = 3000;//小回復の回復量
        cure1 = 5000;//中回復の回復量
        cure2 = 10000;//全回復の回復量

        defence1 = 0.7;//小回復防御力
        defence2 = 0.5;//中回復防御力
        defence3 = 0;//全回復防御力

        CT = 0;//会心発生率
        CTRate = 1;//ダメージ倍率

        AttackText[0] = "スライム召喚";//連続技
        AttackText[1] = "ゴブリン召喚";//威力中
        AttackText[2] = "ゴーレム召喚";//威力大
        AttackText[3] = "ドラゴン召喚";//威力特大

        MonsterName[0] = "スライム";
        MonsterName[1] = "ゴブリン";
        MonsterName[2] = "ゴーレム";
        MonsterName[3] = "ドラゴン";
        MonsterName[4] = "バーサーカー";

        MonsterChar[0] = 'A';
        MonsterChar[1] = 'A';
        MonsterChar[2] = 'A';
        MonsterChar[3] = 'A';
        MonsterChar[4] = 'A';

        MonsterPower[0] = Attack0;//スライムの攻撃力
        MonsterPower[1] = Attack1;//ゴブリンの攻撃力
        MonsterPower[2] = Attack2;//ゴーレムの攻撃力
        MonsterPower[3] = Attack3;//ドラゴンの攻撃力
        MonsterPower[4] = Attack4;//バーサーカーの攻撃力

        WeaponName = "召喚札";
    
        speed = 6;//タイム    
        speedLimit = 15;//タイム上限
        AttackEffectFlag = true;//攻撃自の効果の有無
        SPCF = false;//特殊コマンドの有無

    }

    public static void showSpecialEffect() {
        System.out.println("武器説明：召喚札------------------");
        System.out.println("タイピング時間は標準で、主人公は攻撃せずにモンスタを召喚する。");
        System.out.println("召喚したモンスターがそのターンを含めて" + StayTurn + "ターンの間追撃する。"
                + "\nまた、まれにバーサーカーを召喚する。");
    } //武器説明を見る。

    public void AttackSpecialEffect(int AttackNum, Fight fight) {
        double d = Math.random();
        if (d < 0.15) {
            map.put(MonsterName[4] + MonsterChar[4], StayTurn);
            MonsterChar[4]++;
            System.out.println("なんとバーサーカーを召喚した！！");
            TypeSystem.stop(700);
        } else {
            switch (AttackNum) {
                case 1:
                    SummonedSlime = fight.getCombo();
                    System.out.println(SummonedSlime + "匹の" + MonsterName[0] + "を召喚した！");
                    TypeSystem.stop(700);
                    SlimeMap.put(MonsterName[0] + (SlimeID), SummonedSlime);
                    SlimeTurn[SlimeID] = StayTurn;
                    SlimeID = (SlimeID + 1) % StayTurn;
                    fight.changeTurn(1);
                    break;
                case 2:
                    map.put(MonsterName[1] + MonsterChar[1], StayTurn);
                    System.out.println(MonsterName[1] + "を召喚した！");
                    TypeSystem.stop(700);
                    MonsterChar[1]++;
                    break;
                case 3:
                    map.put(MonsterName[2] + MonsterChar[2], StayTurn);
                    System.out.println(MonsterName[2] + "を召喚した！");
                    TypeSystem.stop(700);
                    MonsterChar[2]++;
                    break;
                case 4:
                    map.put(MonsterName[3] + MonsterChar[3], StayTurn);
                    System.out.println(MonsterName[3] + "を召喚した！");
                    TypeSystem.stop(700);
                    MonsterChar[3]++;
                    break;
            }
        }
        fight.changeTurn(1);
    }//攻撃時に特別な効果をもとに処理を行う。

    public boolean getAttackEffectflag() {
        return AttackEffectFlag;
    }

    public void SpecialEffect(Fight fight) {
        int EnemyHP = fight.getEnemyHp();
        String removeMonster = "";
        int removedSlime = 0;
        int SlimeSum = 0;
        if (!SlimeMap.isEmpty()) {
            for (String key : SlimeMap.keySet()) {
                SlimeSum += SlimeMap.get(key);
            }

            System.out.println("自）" + SlimeSum + "匹のスライムの追撃！!");
            EnemyHP = fight.getEnemyHp();
            System.out.print("自）敵に" + MonsterPower[0] * SlimeSum + "のダメージを与えた！！(" + EnemyHP);
            fight.NoMessageCalcDamage(MonsterPower[0] * SlimeSum);
            EnemyHP = fight.getEnemyHp();
            System.out.println("→" + EnemyHP + ")");
            TypeSystem.stop(500);
            if (EnemyHP <= 0) {
                return;
            }

            for (int i = 0; i < StayTurn; i++) {
                SlimeTurn[i]--;
                if (SlimeTurn[i] == 0) {
                    removedSlime = SlimeMap.get(MonsterName[0] + i);
                    SlimeMap.remove(MonsterName[0] + i);
                    System.out.println(removedSlime + "匹のスライムがいなくなった！");
                    TypeSystem.stop(500);
                }
            }
        }

        Damage:
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            for (int i = 0; i < MonsterNum.length; i++) {
                if (key.contains(MonsterName[i])) {
                    System.out.println("自）" + key + "の追撃！!");
                    EnemyHP = fight.getEnemyHp();
                    System.out.print("自）敵に" + MonsterPower[i] + "のダメージを与えた！！(" + EnemyHP);
                    fight.NoMessageCalcDamage(MonsterPower[i]);
                    EnemyHP = fight.getEnemyHp();
                    System.out.println("→" + EnemyHP + ")");
                    TypeSystem.stop(500);
                    if (EnemyHP <= 0) {
                        break Damage;
                    }
                    int n = map.get(key) - 1;
                    if (n < 1) {
                        removeMonster = key;
                        System.out.println(removeMonster + "がいなくなった！\n");
                        TypeSystem.stop(500);

                    } else {
                        map.put(key, n);
                    }
                }
            }
        }

        map.remove(removeMonster);
    }//特殊効果の発動（敵ターン時に）

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
