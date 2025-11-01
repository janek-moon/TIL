#include<stdio.h>

int main(void)
{
    int n, i;

    printf("출력하고 싶은 단: ");
    scanf("%d", &n);

    i = 1;
    while (i <= 9)
    {
        printf("%d * %d = %d\n", n, i, n * i);
        i++;
    }

    return 0;
}