#include <stdio.h>

int main(void)
{
	int x;
	printf("변수 x의 크기:      %d\n", sizeof(x)); // 4

	printf("char형의 크기:      %d\n", sizeof(char)); // 1
	printf("int형의 크기:       %d\n", sizeof(int));  // 4
	printf("short형의 크기:     %d\n", sizeof(short));// 2
	printf("long형의 크기:      %d\n", sizeof(long)); // 8
	printf("long long형의 크기: %d\n", sizeof(long long)); // 8
	printf("float형의 크기:     %d\n", sizeof(float));	// 4
	printf("double형의 크기:    %d\n", sizeof(double));	// 8

	return 0;
}
