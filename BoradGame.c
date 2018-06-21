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

string Field[30] = {"  ","����ɂQ�O�_���[�W","�Q�}�X�i��","�@","�@","�����ɂP�O�_���[�W","����ɂR�O�_���[�W","�����ɂP�O�_���[�W","����ɂR�O�_���[�W","�@","�R�}�X�i��","�@","�R�}�X�߂�","����ɂR�O�_���[�W","����ɂQ�O�_���[�W","�@","�R�}�X�߂�","�Q�}�X�i��","�R�}�X�i��","�����ɂP�O�_���[�W","�@","�U�}�X�߂�"," "," ","����ɂR�O�_���[�W","�Q�}�X�i��","�X�^�[�g�ɂ��ǂ�","�@ ","�U�}�X�߂�","�S�[��"};

int dice(){
	int d;
	d = rand() % 6 + 1;
	cout << endl << "�T�C�R���̖ڂ́@" << d << "�@�ł�" << endl;
	return d;
}

void rule() {
	printf("���̂����낭�ł́A�̗͂����0�ɂȂ邩�A��ɃS�[���������������ɂȂ�܂��B\n�T�C�R�������ԂɐU��o���ڂ̕������}�X��i�݂܂��B\n�i�񂾐�̃}�X�ɏ����Ă�����ʂ��󂯂܂��B\n�����̃^�[���̍ŏ��ɃA�C�e�����g�����T�C�R����U�邩�I�Ԃ��Ƃ��o���܂��B\n�����A�C�e���������ꍇ���A�C�e���͎g���܂���B\n�����̃A�C�R���́��A����́��ŕ\������܂��B\n");
}


void item() {
	b = 0;
	printf("�A�C�e���̏������@%d\n\n", MyItem);
	printf("�T�C�R����U��-->1\n�񕜃A�C�e�����g��-->2\n-->");
	scanf("%d", &b);
	if (b == 2) {
		if (MyItem > 0) {
			printf("������HP��30��");
			MyHP += 30;
			MyItem--;
		}
		else {
			printf("�A�C�e���̏�������0�Ȃ̂Ŏg���܂���\n");
		}
	}
	else if (b == 1) {

	}
	else {
		printf("���̑I���͏o���܂���B\n");
		return item();
	}
}

void option() {
	printf("��s�����߂Ă�������\n");
	printf("������1\n���聨2\n");
	scanf("%d", &a);
	if (a == 1) {
		MT++;
	}
	else if (a == 2) {
		ET++;
	}
	else {
		printf("���̐��l�͑I���ł��܂���\n");
		return option();
	}
}

void PrintField(int n){
	int i;
	cout << endl;
	for (i = 1; i < 31; i++) {
		if (i == n) cout << "�y���z";
		else cout << "�y" << Field[i - 1] << "�z";
		if (i < 30) cout << "��";
		else cout << endl;
	}

	cout << endl;
	system("pause");
}

void PrintEnemyField(int m) {
	int j;
	cout << endl;
	for (j = 1; j < 31; j++) {
		if (j == m) cout << "�y���z";
		else cout << "�y" << Field[j - 1] << "�z";
		if (j < 30) cout << "��";
		else cout << endl;
	}
	cout << endl;
	system("pause");
}


int EventMyCheck(int n){
	if (n < 1) n = 1;
	if (n > 30) n = 30;
	cout << n << "�}�X�ڂ̃C�x���g : " << Field[n - 1] << endl;
	switch (n) {
	case 1://�Ȃ�
	case 4://�Ȃ�
	case 5://�Ȃ�
	case 10://�Ȃ�
	case 12://�Ȃ�
	case 16://�Ȃ�
	case 21://�Ȃ�
	case 23://�Ȃ�
	case 24://�Ȃ�
	case 28://�Ȃ�
	default://30 �S�[��
		return n;
		break;
	case 2:// ����ɂQ�O�_���[�W
	case 15:// ����ɂQ�O�_���[�W
		EnemyHP -= 20;
		return n;
		break;
	case 18://1�}�X�i��
		n += 1;
		break;
	case 3://�Q�}�X�i��
	case 26://�Q�}�X�i��
		n += 2;
		break;
	case 11://�R�}�X�i��
	case 19://�R�}�X�i��
		n += 3;
		break;
	case 6://�����ɂP�O�_���[�W 
	case 8://�����ɂP�O�_���[�W
	case 20://�����ɂP�O�_���[�W
		MyHP -= 10;
		return n;
		break;
	case 13://�R�}�X�߂�
	case 17://�R�}�X�߂�
		n -= 3;
		break;
	case 22://�U�}�X�߂�
	case 29://�U�}�X�߂�
		n -= 6;
		break;
	case 7://����ɂR�O�_���[�W
	case 9://����ɂR�O�_���[�W
	case 14://����ɂR�O�_���[�W
	case 25://����ɂR�O�_���[�W
		EnemyHP -= 30;
		return n;
		break;
	case 27:// �X�^�[�g�ɂ��ǂ�
		return 1;
		break;
	}
	return EventMyCheck(n);
}

int EventEnemyCheck(int m) {
	if (m < 1) m = 1;
	if (m > 30) m = 30;
	cout << m << "�}�X�ڂ̃C�x���g : " << Field[m - 1] << endl;
	switch (m) {
	case 1://�Ȃ�
	case 4://�Ȃ�
	case 5://�Ȃ�
	case 10://�Ȃ�
	case 12://�Ȃ�
	case 16://�Ȃ�
	case 21://�Ȃ�
	case 23://�Ȃ�
	case 24://�Ȃ�
	case 28://�Ȃ�
	default://30 �S�[��
		return m;
	case 2:// ����ɂQ�O�_���[�W
	case 15:// ����ɂQ�O�_���[�W
		MyHP -= 20;
		return m;
		break;
	case 18://1�}�X�i��
		m += 1;
		break;
	case 3://�Q�}�X�i��
	case 26://�Q�}�X�i��
		m += 2;
		break;
	case 11://�R�}�X�i��
	case 19://�R�}�X�i��
		m += 3;
		break;
	case 6://�����ɂP�O�_���[�W 
	case 8://�����ɂP�O�_���[�W
	case 20://�����ɂP�O�_���[�W
		EnemyHP -= 10;
		return m;
		break;
	case 13://�R�}�X�߂�
	case 17://�R�}�X�߂�
		m -= 3;
		break;
	case 22://�U�}�X�߂�
	case 29://�U�}�X�߂�
		m -= 6;
		break;
	case 7://����ɂR�O�_���[�W
	case 9://����ɂR�O�_���[�W
	case 14://����ɂR�O�_���[�W
	case 25://����ɂR�O�_���[�W
		MyHP -= 30;
		return m;
		break;
	case 27:// �X�^�[�g�ɂ��ǂ�
		return 1;
		break;
	}
	
	return EventEnemyCheck(m);
}

void HP(){
	printf("������HP-->%d\n", MyHP);
	printf("�����HP-->%d\n", EnemyHP);
}

void win() {
	if (MyHP <= 0) {
		printf("\n\n�s�k���Ă��܂���(�L�G�ցG`)\n\n\n");
	}
	if (EnemyHP <= 0) {
		printf("\n\n���������_(^_^)�^\n\n\n");
	}
}

int main(){
	rule();
	option();
	int n = 1;
	int m = 1;
	for (;;) {
		if (MT > 0) {
			printf("�����̃^�[���ł��B\n");
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
			printf("����̃^�[���ł��B\n");
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
			cout << "�S�[��!!" << endl;
			PrintField(30);
			break;
		}
		else if (m == 30) {
			PrintEnemyField(30);
			break;
		}
	}
}