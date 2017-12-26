#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<math.h>

#define WIDTH 8
#define HEIGHT 8

#define EMPTY 0
#define BLACK 1
#define WHITE 2

#define PLAYER1 0
#define PLAYER2 1

#define N 0
#define Y 1


int player=0;
int x, y,i,j,z;
int put=0;
int randomP = 0;
int board[HEIGHT][WIDTH];
int boardPut[64];
int countB,countW;
int a, b;
int npcP;
int xp0, xp1, xp2, xp3, xp4, xp5, xp6, xp7;
int yp0, yp1, yp2, yp3, yp4, yp5, yp6, yp7;
int PR,FS;
int putP;


void field_reset() {
	int i, j;
	for (i = 0; i < HEIGHT; i++) {
		for (j = 0; j < WIDTH; j++) {
			board[i][j] = EMPTY;
		}
	}
	board[HEIGHT / 2 - 1][WIDTH / 2 - 1] = WHITE;
	board[HEIGHT / 2][WIDTH / 2 - 1] = BLACK;
	board[HEIGHT / 2 - 1][WIDTH / 2] = BLACK;
	board[HEIGHT / 2][WIDTH / 2] = WHITE;

}

void field_print() {
	int i, j;
	field_reset();
	printf("x座標とy座標に100を入力すると相手にパスします。\n");
	printf("x座標とy座標に200を入力すると終了します。\n");
	printf("　１２３４５６７８");
	printf("\n");

	for (i = 0; i < HEIGHT; i++) {
		printf("%2d", i + 1);
		for (j = 0; j < WIDTH; j++) {
			if (board[i][j] == EMPTY) {
				printf("＊");
			}
			else if (board[i][j] == BLACK) {
				printf("○");
			}
			else {
				printf("●");
			}
		}
		printf("\n");
	}
}

void field_print2() {

	int i, j;
	printf("　１２３４５６７８");
	printf("\n");

	for (i = 0; i < HEIGHT; i++) {
		printf("%2d", i + 1);
		for (j = 0; j < WIDTH; j++) {
			if (board[i][j] == EMPTY) {
				printf("＊");
			}
			else if (board[i][j] == BLACK) {
				printf("○");
			}
			else {
				printf("●");
			}
		}
		printf("\n");
	}
}

void Option() {
	printf("------------------モードを選択してください。------------------\n");
	printf("人vs人-->1\n人vsランダムロボット-->2\nランダムロボットvsランダムロボット-->3\n");
	printf("-->");
	scanf("%d", &PR);

	if (PR == 1) {
		printf("人vs人で始めます\n");
	}
	else if (PR == 2) {
		printf("人vsランダムロボットで始めます\n");
	}
	else if (PR == 3) {
		printf("ランダムロボットvsランダムロボットで始めます\n");
	}
	else {
		printf("その選択はできません\n");
		return Option();
	}

	printf("先攻後攻を決めてください。\n自分（白）が先行-->1\n相手（黒）が先行-->2\n戻る-->1,2以外\n-->");
	scanf("%d", &FS);

	if (FS == 1) {
		player = 1;
	}
	else if(FS == 2) {
		player = 0;
	}
	else {
		return Option();
	}


}

void WinLose() {
	countB = 0;
	countW = 0;
	for (i = 0; i < HEIGHT; i++) {
		for (j = 0; j < WIDTH; j++) {
			switch (board[i][j]) {
			case BLACK:
				countB++;
				break;
			case WHITE:
				countW++;
				break;
			case EMPTY:
				break;
			default:
				break;
			}
		}
	}

	if (countB < countW) {
		printf("黒の数-->%d 白の数-->%d\n", countB, countW);
		printf("白の勝ちです。\n");
	}
	if (countW < countB) {
		printf("黒の数-->%d 白の数-->%d\n", countB, countW);
		printf("黒の勝ちです。\n");
	}
	if (countB == countW) {
		printf("黒の数-->%d 白の数-->%d\n", countB, countW);
		printf("引き分けです。\n");
	}
}

void ArrangementBlack() {
	printf("黒の番です。\n");
	printf("x座標を入力してください-->");
	scanf("%d", &x);
	printf("y座標を入力してください-->");
	scanf("%d", &y);
	x--;
	y--;
	if (x == 99 || y == 99) {
		printf("パスします。\n");
		field_print2();
		player++;
	}

	if (x == 199 || y == 199) {
		printf("終了します\n");
		return WinLose();
		exit(1);
	}
	if (player % 2 == PLAYER1) {
		if (board[y][x] != EMPTY) {
			printf("そこには置けません。\n");
			field_print2();
			return ArrangementBlack();
		}
	}
	board[y][x] = BLACK;
}

void ArrangementWhite() {
	printf("白の番です。\n");
	printf("x座標を入力してください-->");
	scanf("%d", &x);
	printf("y座標を入力してください-->");
	scanf("%d", &y);
	x--;
	y--;
	if (x == 99 || y == 99) {
		printf("パスします。\n");
		field_print2();
		player++;
	}

	if (x == 199 || y == 199) {
		printf("終了します\n");
		return WinLose();
		exit(1);
	}

	if (player % 2 == PLAYER2) {
		if (board[y][x] != EMPTY) {
			printf("そこには置けません。\n");
			//field_print2();
			return ArrangementWhite();
		}
	}
	board[y][x] = WHITE;
}

//黒の裏返し判定
void ReversedBlackDown() {
	for (i = 0; y+i < HEIGHT; i++) {
		if (board[y + i + 1][x] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x] != BLACK) {
			break;
		}
		if (y + i + 1 > HEIGHT) {
			break;
		}
		if (board[y + i + 1][x] == BLACK) {
			for (j = 0; y+i < HEIGHT; j++) {
				board[y + j][x] = BLACK;
				if (board[y + j + 1][x] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedBlackUp() {
	for (i = 0; y-i >= 0; i++) {
		if (board[y - i - 1][x] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x] != BLACK) {
			break;
		}
		if (y - i - 1 < 0) {
			break;
		}
		if (board[y - i - 1][x] == BLACK) {
			for (j = 0; y-i >= 0; j++) {
				board[y - j][x] = BLACK;
				if (board[y - j - 1][x] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedBlackRight() {
	for (i = 0; x+i < HEIGHT; i++) {
		if (board[y][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y][x + i + 1] != BLACK) {
			break;
		}
		if (x + i + 1 > HEIGHT) {
			break;
		}
		if (board[y][x + i + 1] == BLACK) {
			for (j = 0; x+i < HEIGHT; j++) {
				board[y][x + j] = BLACK;
				if (board[y][x + j + 1] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedBlackLeft() {
	for (i = 0; x-i >= 0; i++) {
		if (board[y][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y][x - i - 1] != BLACK) {
			break;
		}
		if (x - i - 1 < 0) {
			break;
		}
		if (board[y][x - i - 1] == BLACK) {
			for (j = 0; x-i >= 0; j++) {
				board[y][x - j] = BLACK;
				if (board[y][x - j - 1] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedBlackRD() {
	for (i = 0; y+i < HEIGHT && x+i < HEIGHT; i++) {
		if (board[y + i + 1][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x + i + 1] != BLACK) {
			break;
		}
		if (y + i + 1 > HEIGHT && x + i + 1 > HEIGHT) {
			break;
		}
		if (board[y + i + 1][x + i + 1] == BLACK) {
			for (j = 0; y+j < HEIGHT && x+j < HEIGHT; j++) {
				board[y + j][x + j] = BLACK;
				if (board[y + j + 1][x + j + 1] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedBlackRU() {
	for (i = 0; y+i < HEIGHT && x-i >= 0; i++) {
		if (board[y + i + 1][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x - i - 1] != BLACK) {
			break;
		}
		if (y + i + 1 > HEIGHT && x - i - 1 < 0) {
			break;
		}
		if (board[y + i + 1][x - i - 1] == BLACK) {
			for (j = 0; y+i < HEIGHT && x-i >= 0; j++) {
				board[y + j][x - j] = BLACK;
				if (board[y + j + 1][x - j - 1] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedBlackLD() {
	for (i = 0; y-i >= 0 && x+i < HEIGHT; i++) {
		if (board[y - i - 1][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x + i + 1] != BLACK) {
			break;
		}
		if (y - i - 1 < 0 && x + i + 1 > HEIGHT) {
			break;
		}
		if (board[y - i - 1][x + i + 1] == BLACK) {
			for (j = 0; y-i >= 0 && x+i < HEIGHT; j++) {
				board[y - j][x + j] = BLACK;
				if (board[y - j - 1][x + j + 1] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedBlackLU() {
	for (i = 0; y-i >= 0 && x-i >= 0; i++) {
		if (board[y - i - 1][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x - i - 1] != BLACK) {
			break;
		}
		if (y - i - 1 < 0 && x - i - 1 < 0) {
			break;
		}
		if (board[y - i - 1][x - i - 1] == BLACK) {
			for (j = 0; y-i >= 0 && x-i >= 0; j++) {
				board[y - j][x - j] = BLACK;
				if (board[y - j - 1][x - j - 1] == WHITE) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

//白の裏返し判定
void ReversedWhiteDown() {
	for (i = 0; y+i < HEIGHT; i++) {
		if (board[y + i + 1][x] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x] != WHITE) {
			break;
		}
		if (y + i + 1 > HEIGHT) {
			break;
		}
		if (board[y + i + 1][x] == WHITE) {
			for (j = 0; y+i < HEIGHT; j++) {
				board[y + j][x] = WHITE;
				if (board[y + j + 1][x] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedWhiteUp() {
	for (i = 0; y-i >= 0; i++) {
		if (board[y - i - 1][x] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x] != WHITE) {
			break;
		}
		if (y - i - 1 < 0) {
			break;
		}
		if (board[y - i - 1][x] == WHITE) {
			for (j = 0; y >= 0; j++) {
				board[y - j][x] = WHITE;
				if (board[y - j - 1][x] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedWhiteRight() {
	for (i = 0; x+i+1 < HEIGHT; i++) {
		if (board[y][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y][x + i + 1] != WHITE) {
			break;
		}
		if (x + i + 1 > HEIGHT) {
			break;
		}
		if (board[y][x + i + 1] == WHITE) {
			for (j = 0; x+i+1 < HEIGHT; j++) {
				board[y][x + j] = WHITE;
				if (board[y][x + j + 1] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedWhiteLeft() {
	for (i = 0; x-i >= 0; i++) {
		if (board[y][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y][x - i - 1] != WHITE) {
			break;
		}
		if (x - i - 1 < 1) {
			break;
		}
		if (board[y][x - i - 1] == WHITE) {
			for (j = 0; x >= 0; j++) {
				board[y][x - j] = WHITE;
				if (board[y][x - j - 1] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedWhiteRD() {
	for (i = 0; y + i < HEIGHT && x + i < HEIGHT; i++) {
		if (board[y + i + 1][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x + i + 1] != WHITE) {
			break;
		}
		if (y + i + 1 > HEIGHT && x + i + 1 > HEIGHT) {
			break;
		}
		if (board[y + i + 1][x + i + 1] == WHITE) {
			for (j = 0; y + j < HEIGHT && x + j < HEIGHT; j++) {
				board[y + j][x + j] = WHITE;
				if (board[y + j + 1][x + j + 1] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedWhiteRU() {
	for (i = 0; y+i < HEIGHT && x-i >= 0; i++) {
		if (board[y + i + 1][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x - i - 1] != WHITE) {
			break;
		}
		if (y + i + 1 > HEIGHT && x - i - 1 < 0) {
			break;
		}
		if (board[y + i + 1][x - i - 1] == WHITE) {
			for (j = 0; y+i < HEIGHT && x-i >= 0; j++) {
				board[y + j][x - j] = WHITE;
				if (board[y + j + 1][x - j - 1] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedWhiteLD() {
	for (i = 0; y-i >= 0 && x+i < HEIGHT; i++) {
		if (board[y - i - 1][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x + i + 1] != WHITE) {
			break;
		}
		if (y - i - 1 < 0 && x + i + 1 > HEIGHT) {
			break;
		}
		if (board[y - i - 1][x + i + 1] == WHITE) {
			for (j = 0; y >= 0 && x < HEIGHT; j++) {
				board[y - j][x + j] = WHITE;
				if (board[y - j - 1][x + j + 1] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

void ReversedWhiteLU() {
	for (i = 0; y-i >= 0 && x-i >= 0; i++) {
		if (board[y - i - 1][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x - i - 1] != WHITE) {
			break;
		}
		if (y - i - 1 < 1 && x - i - 1 < 1) {
			break;
		}
		if (board[y - i - 1][x - i - 1] == WHITE) {
			for (j = 0; y >= 0 && x >= 0; j++) {
				board[y - j][x - j] = WHITE;
				if (board[y - j - 1][x - j - 1] == BLACK) {
					put++;
					continue;
				}
				break;
				break;
			}
		}
	}
}

//ランダムロボット
//黒の裏返し判定
void ReversedBlackDown2() {
	for (i = 0; y < HEIGHT; i++) {
		if (board[y + i + 1][x] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x] != BLACK) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y + i + 1][x] == BLACK) {
			xp0 = x;
			yp0 = y;
			boardPut[z] = 0;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedBlackUp2() {
	for (i = 0; y > 0; i++) {
		if (board[y - i - 1][x] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x] != BLACK) {
			board[y][x] = EMPTY;
			break;
		}
		if (board[y - i - 1][x] == BLACK) {
			xp1 = x;
			yp1 = y;
			boardPut[z] = 1;
			z++;
			npcP = Y;
			//board[y][x] = EMPTY;
			break;
		}
	}
}

void ReversedBlackRight2() {
	for (i = 0; x < HEIGHT; i++) {
		if (board[y][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y][x + i + 1] != BLACK) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y][x + i + 1] == BLACK) {
			xp2 = x;
			yp2 = y;
			boardPut[z] = 2;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedBlackLeft2() {
	for (i = 0; x > 0; i++) {
		if (board[y][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y][x - i - 1] != BLACK) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y][x - i - 1] == BLACK) {
			xp3 = x;
			yp3 = y;
			boardPut[z] = 3;
			z++;
			npcP = Y;
			//board[y][x] = EMPTY;
			break;
		}
	}
}

void ReversedBlackRD2() {
	for (i = 0;y < HEIGHT && x< HEIGHT; i++) {
		if (board[y + i + 1][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x + i + 1] != BLACK) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y + i + 1][x + i + 1] == BLACK) {
			xp4 = x;
			yp4 = y;
			boardPut[z] = 4;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedBlackRU2() {
	for (i = 0; y < HEIGHT && x > 0; i++) {
		if (board[y + i + 1][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x - i - 1] != BLACK) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y + i + 1][x - i - 1] == BLACK) {
			xp5 = x;
			yp5 = y;
			boardPut[z] = 5;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedBlackLD2() {
	for (i = 0; y > 0 || x < HEIGHT; i++) {
		if (board[y - i - 1][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x + i + 1] != BLACK) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y - i - 1][x + i + 1] == BLACK) {
			xp6 = x;
			yp6 = y;
			boardPut[z] = 6;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedBlackLU2() {
	for (i = 0; y > 0 && x > 0; i++) {
		if (board[y - i - 1][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x - i - 1] != BLACK) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y - i - 1][x - i - 1] == BLACK) {
			xp7 = x;
			yp7 = y;
			boardPut[z] = 7;
			z++;
			npcP = Y;
			break;
		}
	}
}

void RandomBotB() {
	npcP = N;
	put = 1;
	z = 0;
	xp0 = xp1 = xp2 = xp3 = xp4 = xp5 = xp6 = xp7 = 0;
	yp0 = yp1 = yp2 = yp3 = yp4 = yp5 = yp6 = yp7 = 0;
	printf("黒の番です。\n");
	for (y = 0; y < HEIGHT; y++) {
		for (x = 0; x < WIDTH; x++) {

			if (board[y + 1][x] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackDown2();
			}
			if (board[y - 1][x] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackUp2();
			}
			if (board[y][x + 1] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackRight2();
			}
			if (board[y][x - 1] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackLeft2();
			}
			if (board[y + 1][x + 1] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackRD2();
			}
			if (board[y + 1][x - 1] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackLD2();
			}
			if (board[y - 1][x + 1] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackRU2();
			}
			if (board[y - 1][x - 1] == WHITE && board[y][x] == EMPTY) {
				ReversedBlackLU2();
			}
		}
	}

	if (npcP == Y) {
		srand(time(NULL));
		int num = rand() % z;
		
		if (boardPut[num] == 0) {
			board[yp0][xp0] = BLACK;
			y = yp0;
			x = xp0;
		}
		if (boardPut[num] == 1) {
			board[yp1][xp1] = BLACK;
			y = yp1;
			x = xp1;
		}
		if (boardPut[num] == 2) {
			board[yp2][xp2] = BLACK;
			y = yp2;
			x = xp2;
		}
		if (boardPut[num] == 3) {
			board[yp3][xp3] = BLACK;
			y = yp3;
			x = xp3;
		}
		if (boardPut[num] == 4) {
			board[yp4][xp4] = BLACK;
			y = yp4;
			x = xp4;
		}
		if (boardPut[num] == 5) {
			board[yp5][xp5] = BLACK;
			y = yp5;
			x = xp5;
		}
		if (boardPut[num] == 6) {
			board[yp6][xp6] = BLACK;
			y = yp6;
			x = xp6;
		}
		if (boardPut[num] == 7) {
			board[yp7][xp7] = BLACK;
			y = yp7;
			x = xp7;
		}
	}
}

//ランダムロボット
//白の裏返し判定
void ReversedWhiteDown2() {
	for (i = 0; y < HEIGHT; i++) {
		if (board[y + i + 1][x] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x] == EMPTY) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y + i + 1][x] == WHITE) {
			xp0 = x;
			yp0 = y;
			boardPut[z] = 0;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedWhiteUp2() {
	for (i = 0; y > 0; i++) {
		if (board[y - i - 1][x] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x] != WHITE) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y - i - 1][x] == WHITE) {
			xp1 = x;
			yp1 = y;
			boardPut[z] = 1;
			z++;
			npcP = Y;
			//board[y][x] = EMPTY;
			break;
		}
	}
}

void ReversedWhiteRight2() {
	for (i = 0; x < HEIGHT; i++) {
		if (board[y][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y][x + i + 1] != WHITE) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y][x + i + 1] == WHITE) {
			xp2 = x;
			yp2 = y;
			boardPut[z] = 2;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedWhiteLeft2() {
	for (i = 0; x > 0; i++) {
		if (board[y][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y][x - i - 1] != WHITE) {
			board[y][x] = EMPTY;
			break;
		}
		if (board[y][x - i - 1] == WHITE) {
			xp3 = x;
			yp3 = y;
			boardPut[z] = 3;
			z++;
			npcP = Y;
			//board[y][x] = EMPTY;
			break;
		}
	}
}

void ReversedWhiteRD2() {
	for (i = 0; y < HEIGHT && x < HEIGHT; i++) {
		if (board[y + i + 1][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x + i + 1] != WHITE) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y + i + 1][x + i + 1] == WHITE) {
			xp4 = x;
			yp4 = y;
			boardPut[z] = 4;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedWhiteRU2() {
	for (i = 0; y < HEIGHT && x > 0; i++) {
		if (board[y + i + 1][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x - i - 1] != WHITE) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y + i + 1][x - i - 1] == WHITE) {
			xp5 = x;
			yp5 = y;
			boardPut[z] = 5;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedWhiteLD2() {
	for (i = 0; y > 0 && x < HEIGHT; i++) {
		if (board[y - i - 1][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x + i + 1] != WHITE) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y - i - 1][x + i + 1] == WHITE) {
			xp6 = x;
			yp6 = y;
			boardPut[z] = 6;
			z++;
			npcP = Y;
			break;
		}
	}
}

void ReversedWhiteLU2() {
	for (i = 0; y >= 0 && x >= 0; i++) {
		if (board[y - i - 1][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x - i - 1] != WHITE) {
			//board[y][x] = EMPTY;
			break;
		}
		if (board[y - i - 1][x - i - 1] == WHITE) {
			xp7 = x;
			yp7 = y;
			boardPut[z] = 7;
			z++;
			npcP = Y;
			break;
		}
	}
}

void RandomBotW() {
	npcP = N;
	put = 1;
	z = 0;
	xp0 = xp1 = xp2 = xp3 = xp4 = xp5 = xp6 = xp7 = 0;
	yp0 = yp1 = yp2 = yp3 = yp4 = yp5 = yp6 = yp7 = 0;
	printf("白の番です。\n");
	for (y = 0; y < HEIGHT; y++) {
		for (x = 0; x < WIDTH; x++) {
		
			if (board[y + 1][x] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteDown2();
			}
			if (board[y - 1][x] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteUp2();
			}
			if (board[y][x + 1] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteRight2();
			}
			if (board[y][x - 1] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteLeft2();
			}
			if (board[y + 1][x + 1] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteRD2();
			}
			if (board[y + 1][x - 1] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteLD2();
			}
			if (board[y - 1][x + 1] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteRU2();
			}
			if (board[y - 1][x - 1] == BLACK && board[y][x] == EMPTY) {
				ReversedWhiteLU2();
			}
		}
	}

	if (npcP == Y) {
		srand(time(NULL));
		int num = rand() % z;

		if (boardPut[num] == 0) {
			board[yp0][xp0] = WHITE;
			y = yp0;
			x = xp0;
		}
		if (boardPut[num] == 1) {
			board[yp1][xp1] = WHITE;
			y = yp1;
			x = xp1;
		}
		if (boardPut[num] == 2) {
			board[yp2][xp2] = WHITE;
			y = yp2;
			x = xp2;
		}
		if (boardPut[num] == 3) {
			board[yp3][xp3] = WHITE;
			y = yp3;
			x = xp3;
		}
		if (boardPut[num] == 4) {
			board[yp4][xp4] = WHITE;
			y = yp4;
			x = xp4;
		}
		if (boardPut[num] == 5) {
			board[yp5][xp5] = WHITE;
			y = yp5;
			x = xp5;
		}
		if (boardPut[num] == 6) {
			board[yp6][xp6] = WHITE;
			y = yp6;
			x = xp6;
		}
		if (boardPut[num] == 7) {
			board[yp7][xp7] = WHITE;
			y = yp7;
			x = xp7;
		}
	}
}

int main(void) {
	Option();
	field_print();
	for (;;) {
		put = 0;
		a = 0;
		putP = 0;

		if (PR == 1) {
			if (player % 2 == PLAYER1) {
				ArrangementBlack();
				ReversedBlackDown();
				ReversedBlackUp();
				ReversedBlackRight();
				ReversedBlackLeft();
				ReversedBlackRD();
				ReversedBlackRU();
				ReversedBlackLD();
				ReversedBlackLU();
			}

			if (player % 2 == PLAYER2) {
				ArrangementWhite();
				ReversedWhiteDown();
				ReversedWhiteUp();
				ReversedWhiteRight();
				ReversedWhiteLeft();
				ReversedWhiteRD();
				ReversedWhiteRU();
				ReversedWhiteLD();
				ReversedWhiteLU();
			}
		}

		if (PR == 2) {
			if (player % 2 == PLAYER1) {
				RandomBotB();
				ReversedBlackDown();
				ReversedBlackUp();
				ReversedBlackRight();
				ReversedBlackLeft();
				ReversedBlackRD();
				ReversedBlackRU();
				ReversedBlackLD();
				ReversedBlackLU();
			}

			if (player % 2 == PLAYER2) {
				ArrangementWhite();
				ReversedWhiteDown();
				ReversedWhiteUp();
				ReversedWhiteRight();
				ReversedWhiteLeft();
				ReversedWhiteRD();
				ReversedWhiteRU();
				ReversedWhiteLD();
				ReversedWhiteLU();
			}
		}

		if (PR == 3) {
			if (player % 2 == PLAYER1) {
				RandomBotB();
				ReversedBlackDown();
				ReversedBlackUp();
				ReversedBlackRight();
				ReversedBlackLeft();
				ReversedBlackRD();
				ReversedBlackRU();
				ReversedBlackLD();
				ReversedBlackLU();
			}

			if (player % 2 == PLAYER2) {
				RandomBotW();
				ReversedWhiteDown();
				ReversedWhiteUp();
				ReversedWhiteRight();
				ReversedWhiteLeft();
				ReversedWhiteRD();
				ReversedWhiteRU();
				ReversedWhiteLD();
				ReversedWhiteLU();
			}
		}

		if (x == 99 && y == 99) {
			continue; 
		}

		if (put == 0) {
			board[y][x] = EMPTY;
			printf("そこには置けません。\n");
			continue;
		}

		if (x != 99 && y != 99) {
			printf("x座標-->%d、y座標-->%dに置きました。\n", x + 1, y + 1);
			field_print2();
			player++;
		}

		for (i = 0; i < HEIGHT; i++) {
			for (j = 0; j < WIDTH; j++) {
				if (board[i][j] == EMPTY) {
					putP++;
				}
			}
		}
		if (putP == 0) {
			WinLose();
			break;
		}
	}
	return 0;
}