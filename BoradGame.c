#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
using namespace std;

int MT = 0; 
int ET = 0;
int a, b;
int d;
int n;
int MyItem = 1;
int MyHP = 100;
int EnemyHP = 100;

string Field[30] = {"  ","相手に２０ダメージ","２マス進む","　","　","自分に１０ダメージ","相手に３０ダメージ","自分に１０ダメージ","相手に３０ダメージ","　","３マス進む","　","３マス戻る","相手に３０ダメージ","相手に２０ダメージ","　","３マス戻る","２マス進む","３マス進む","自分に１０ダメージ","　","６マス戻る"," "," ","相手に３０ダメージ","２マス進む","スタートにもどる","　 ","６マス戻る","ゴール"};

int dice(){
	int d;
	d = rand() % 6 + 1;
	cout << endl << "サイコロの目は　" << d << "　です" << endl;
	return d;
}

void rule() {
	printf("このすごろくでは、体力が先に0になるか、先にゴールした方が勝ちになります。\nサイコロを順番に振り出た目の分だけマスを進みます。\n進んだ先のマスに書いてある効果を受けます。\n自分のターンの最初にアイテムを使うかサイコロを振るか選ぶことが出来ます。\nもしアイテムが無い場合いアイテムは使えません。\n自分のアイコンは■、相手は●で表示されます。\n");
}


void item() {
	b = 0;
	printf("アイテムの所持数　%d\n\n", MyItem);
	printf("サイコロを振る-->1\n回復アイテムを使う-->2\n-->");
	scanf("%d", &b);
	if (b == 2) {
		if (MyItem > 0) {
			printf("自分のHPを30回復");
			MyHP += 30;
			MyItem--;
		}
		else {
			printf("アイテムの所持数が0なので使えません\n");
		}
	}
	else if (b == 1) {

	}
	else {
		printf("その選択は出来ません。\n");
		return item();
	}
}

void option() {
	printf("先行を決めてください\n");
	printf("自分→1\n相手→2\n");
	scanf("%d", &a);
	if (a == 1) {
		MT++;
	}
	else if (a == 2) {
		ET++;
	}
	else {
		printf("その数値は選択できません\n");
		return option();
	}
}

void PrintField(int n){
	int i;
	cout << endl;
	for (i = 1; i < 31; i++) {
		if (i == n) cout << "【■】";
		else cout << "【" << Field[i - 1] << "】";
		if (i < 30) cout << "→";
		else cout << endl;
	}

	cout << endl;
	system("pause");
}

void PrintEnemyField(int m) {
	int j;
	cout << endl;
	for (j = 1; j < 31; j++) {
		if (j == m) cout << "【●】";
		else cout << "【" << Field[j - 1] << "】";
		if (j < 30) cout << "→";
		else cout << endl;
	}
	cout << endl;
	system("pause");
}


int EventMyCheck(int n){
	if (n < 1) n = 1;
	if (n > 30) n = 30;
	cout << n << "マス目のイベント : " << Field[n - 1] << endl;
	switch (n) {
	case 1://なし
	case 4://なし
	case 5://なし
	case 10://なし
	case 12://なし
	case 16://なし
	case 21://なし
	case 23://なし
	case 24://なし
	case 28://なし
	default://30 ゴール
		return n;
		break;
	case 2:// 相手に２０ダメージ
	case 15:// 相手に２０ダメージ
		EnemyHP -= 20;
		return n;
		break;
	case 18://1マス進む
		n += 1;
		break;
	case 3://２マス進む
	case 26://２マス進む
		n += 2;
		break;
	case 11://３マス進む
	case 19://３マス進む
		n += 3;
		break;
	case 6://自分に１０ダメージ 
	case 8://自分に１０ダメージ
	case 20://自分に１０ダメージ
		MyHP -= 10;
		return n;
		break;
	case 13://３マス戻る
	case 17://３マス戻る
		n -= 3;
		break;
	case 22://６マス戻る
	case 29://６マス戻る
		n -= 6;
		break;
	case 7://相手に３０ダメージ
	case 9://相手に３０ダメージ
	case 14://相手に３０ダメージ
	case 25://相手に３０ダメージ
		EnemyHP -= 30;
		return n;
		break;
	case 27:// スタートにもどる
		return 1;
		break;
	}
	return EventMyCheck(n);
}

int EventEnemyCheck(int m) {
	if (m < 1) m = 1;
	if (m > 30) m = 30;
	cout << m << "マス目のイベント : " << Field[m - 1] << endl;
	switch (m) {
	case 1://なし
	case 4://なし
	case 5://なし
	case 10://なし
	case 12://なし
	case 16://なし
	case 21://なし
	case 23://なし
	case 24://なし
	case 28://なし
	default://30 ゴール
		return m;
	case 2:// 相手に２０ダメージ
	case 15:// 相手に２０ダメージ
		MyHP -= 20;
		return m;
		break;
	case 18://1マス進む
		m += 1;
		break;
	case 3://２マス進む
	case 26://２マス進む
		m += 2;
		break;
	case 11://３マス進む
	case 19://３マス進む
		m += 3;
		break;
	case 6://自分に１０ダメージ 
	case 8://自分に１０ダメージ
	case 20://自分に１０ダメージ
		EnemyHP -= 10;
		return m;
		break;
	case 13://３マス戻る
	case 17://３マス戻る
		m -= 3;
		break;
	case 22://６マス戻る
	case 29://６マス戻る
		m -= 6;
		break;
	case 7://相手に３０ダメージ
	case 9://相手に３０ダメージ
	case 14://相手に３０ダメージ
	case 25://相手に３０ダメージ
		MyHP -= 30;
		return m;
		break;
	case 27:// スタートにもどる
		return 1;
		break;
	}
	
	return EventEnemyCheck(m);
}

void HP(){
	printf("自分のHP-->%d\n", MyHP);
	printf("相手のHP-->%d\n", EnemyHP);
}

void win() {
	if (MyHP <= 0) {
		printf("\n\n敗北してしまった(´；ω；`)\n\n\n");
	}
	if (EnemyHP <= 0) {
		printf("\n\n勝利した＼(^_^)／\n\n\n");
	}
}

int main(){
	rule();
	option();
	int n = 1;
	int m = 1;
	for (;;) {
		if (MT > 0) {
			printf("自分のターンです。\n");
			HP();
			item();
			srand(time(0));
			PrintField(n);
			n += dice();
			n = EventMyCheck(n);
			HP();
			PrintField(n);
			MT--;
			ET++;
		}

		if (MyHP <= 0 || EnemyHP <= 0) {
			win();
			break;
		}

		if (ET > 0) {
			printf("相手のターンです。\n");
			HP();
			srand(time(0));
			PrintEnemyField(m);
			m += dice();
			m = EventEnemyCheck(m);
			HP();
			PrintEnemyField(m);
			ET--;
			MT++;
		}

		if (MyHP <= 0 || EnemyHP <= 0) {
			win();
			break;
		}

		if (n == 30) {
			cout << "ゴール!!" << endl;
			PrintField(30);
			break;
		}
		else if (m == 30) {
			PrintEnemyField(30);
			break;
		}
	}
}