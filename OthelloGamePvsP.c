#include<stdio.h>
#include<stdlib.h>

#define WIDTH 8
#define HEIGHT 8

#define EMPTY 0
#define BLACK 1
#define WHITE 2

#define PLAYER1 0
#define PLAYER2 1

int player = 0;
int x, y, i, j, z;
int put,putP;
int randomP = 0;
int countB, countW, countE;
int board[HEIGHT][WIDTH];

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
	printf("x���W��y���W��100����͂���Ƒ���Ƀp�X���܂��B\n");
	printf("x���W��y���W��200����͂���ƏI�����܂��B\n");
	printf("�@�P�Q�R�S�T�U�V�W");
	printf("\n");

	for (i = 0; i < HEIGHT; i++) {
		printf("%2d", i + 1);
		for (j = 0; j < WIDTH; j++) {
			if (board[i][j] == EMPTY) {
				printf("��");
			}
			else if (board[i][j] == BLACK) {
				printf("��");
			}
			else {
				printf("��");
			}
		}
		printf("\n");
	}
}

void field_print2() {

	int i, j;
	printf("�@�P�Q�R�S�T�U�V�W");
	printf("\n");

	for (i = 0; i < HEIGHT; i++) {
		printf("%2d", i + 1);
		for (j = 0; j < WIDTH; j++) {
			if (board[i][j] == EMPTY) {
				printf("��");
			}
			else if (board[i][j] == BLACK) {
				printf("��");
			}
			else {
				printf("��");
			}
		}
		printf("\n");
	}
}

void WinLose() {
	countB = 0;
	countW = 0;
	countE = 0;
	for (i = 0; i < HEIGHT; i++) {
		for (j = 0; j < WIDTH; j++) {
			if (board[i][j] == BLACK) {
				countB++;
			}
			else if (board[i][j] == WHITE) {
				countW++;
			}
			else {
				countE++;
			}
		}
	}
	if (countB < countW) {
		printf("���̐�-->%d ���̐�-->%d\n", countB, countW);
		printf("���̏����ł��B\n");
	}
	else if (countW < countB) {
		printf("���̐�-->%d ���̐�-->%d\n", countB, countW);
		printf("���̏����ł��B\n");
	}
	else {
		printf("���̐�-->%d ���̐�-->%d\n", countB, countW);
		printf("���������ł��B\n");
	}
	exit(1);
}

void ArrangementBlack() {
	printf("���̔Ԃł��B\n");
	printf("x���W����͂��Ă�������-->");
	scanf("%d", &x);
	printf("y���W����͂��Ă�������-->");
	scanf("%d", &y);
	x--;
	y--;
	if (x == 99 || y == 99) {
		printf("�p�X���܂��B\n");
		field_print2();
		player++;
	}

	if (x == 199 || y == 199) {
		printf("�I�����܂�\n");
		WinLose();
	}
	if (player % 2 == PLAYER1) {
		if (board[y][x] != EMPTY) {
			printf("�����ɂ͒u���܂���B\n");
			//field_print2();
			return ArrangementBlack();
		}
	}
	board[y][x] = BLACK;
}

void ArrangementWhite() {
	printf("���̔Ԃł��B\n");
	printf("x���W����͂��Ă�������-->");
	scanf("%d", &x);
	printf("y���W����͂��Ă�������-->");
	scanf("%d", &y);
	x--;
	y--;
	if (x == 99 || y == 99) {
		printf("�p�X���܂��B\n");
		field_print2();
		player++;
		return ArrangementBlack();
	}

	if (x == 199 || y == 199) {
		printf("�I�����܂�\n");
		WinLose();
	}

	if (player % 2 == PLAYER2) {
		if (board[y][x] != EMPTY) {
			printf("�����ɂ͒u���܂���B\n");
			//field_print2();
			return ArrangementWhite();
		}
	}
	board[y][x] = WHITE;
}

//���̗��Ԃ�����
void ReversedBlackDown() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y + i + 1][x] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x] == EMPTY) {
			break;
		}
		if (board[y + i + 1][x] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y + j][x] = BLACK;
				if (board[y + j + 1][x] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedBlackUp() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y - i - 1][x] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x] == EMPTY) {
			break;
		}
		if (board[y - i - 1][x] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y - j][x] = BLACK;
				if (board[y - j - 1][x] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedBlackRight() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y][x + i + 1] == EMPTY) {
			break;
		}
		if (board[y][x + i + 1] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y][x + j] = BLACK;
				if (board[y][x + j + 1] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedBlackLeft() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y][x - i - 1] == EMPTY) {
			break;
		}
		if (board[y][x - i - 1] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y][x - j] = BLACK;
				if (board[y][x - j - 1] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedBlackRD() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y + i + 1][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x + i + 1] == EMPTY) {
			break;
		}
		if (board[y + i + 1][x + i + 1] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y + j][x + j] = BLACK;
				if (board[y + j + 1][x + j + 1] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedBlackRU() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y + i + 1][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y + i + 1][x - i - 1] == EMPTY) {
			break;
		}
		if (board[y + i + 1][x - i - 1] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y + j][x - j] = BLACK;
				if (board[y + j + 1][x - j - 1] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedBlackLD() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y - i - 1][x + i + 1] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x + i + 1] == EMPTY) {
			break;
		}
		if (board[y - i - 1][x + i + 1] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y - j][x + j] = BLACK;
				if (board[y - j - 1][x + j + 1] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedBlackLU() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y - i - 1][x - i - 1] == WHITE) {
			continue;
		}
		if (board[y - i - 1][x - i - 1] == EMPTY) {
			break;
		}
		if (board[y - i - 1][x - i - 1] == BLACK) {
			for (j = 0; j < HEIGHT; j++) {
				board[y - j][x - j] = BLACK;
				if (board[y - j - 1][x - j - 1] == WHITE) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

//���̗��Ԃ�����
void ReversedWhiteDown() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y + i + 1][x] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x] == EMPTY) {
			break;
		}
		if (board[y + i + 1][x] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y + j][x] = WHITE;
				if (board[y + j + 1][x] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedWhiteUp() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y - i - 1][x] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x] == EMPTY) {
			break;
		}
		if (board[y - i - 1][x] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y - j][x] = WHITE;
				if (board[y - j - 1][x] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedWhiteRight() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y][x + i + 1] == EMPTY) {
			break;
		}
		if (board[y][x + i + 1] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y][x + j] = WHITE;
				if (board[y][x + j + 1] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedWhiteLeft() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y][x - i - 1] == EMPTY) {
			break;
		}
		if (board[y][x - i - 1] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y][x - j] = WHITE;
				if (board[y][x - j - 1] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedWhiteRD() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y + i + 1][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x + i + 1] == EMPTY) {
			break;
		}
		if (board[y + i + 1][x + i + 1] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y + j][x + j] = WHITE;
				if (board[y + j + 1][x + j + 1] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedWhiteRU() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y + i + 1][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y + i + 1][x - i - 1] == EMPTY) {
			break;
		}
		if (board[y + i + 1][x - i - 1] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y + j][x - j] = WHITE;
				if (board[y + j + 1][x - j - 1] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedWhiteLD() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y - i - 1][x + i + 1] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x + i + 1] == EMPTY) {
			break;
		}
		if (board[y - i - 1][x + i + 1] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y - j][x + j] = WHITE;
				if (board[y - j - 1][x + j + 1] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

void ReversedWhiteLU() {
	for (i = 0; i < HEIGHT; i++) {
		if (board[y - i - 1][x - i - 1] == BLACK) {
			continue;
		}
		if (board[y - i - 1][x - i - 1] == EMPTY) {
			break;
		}
		if (board[y - i - 1][x - i - 1] == WHITE) {
			for (j = 0; j < HEIGHT; j++) {
				board[y - j][x - j] = WHITE;
				if (board[y - j - 1][x - j - 1] == BLACK) {
					put++;
					continue;
				}
				break;
			}
		}
	}
}

int main(void) {
	field_print();
	for (;;) {
		put = 0;
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

		if (y == 99 && x == 99) {
			continue;
		}

		if (put == 0) {
			board[y][x] = EMPTY;
			printf("�����ɂ͒u���܂���B\n");
			continue;
		}


		printf("x���W-->%d�Ay���W-->%d�ɒu���܂����B\n", x + 1, y + 1);
		field_print2();
		player++;

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