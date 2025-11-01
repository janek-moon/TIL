#include<stdio.h>

int main(void)
{
    float grade, sum = 0.0, average;
    int count = 0;

    for (;;)
    {
        printf("학생의 성적을 입력하시오: ");
        scanf("%f", &grade);

        if(grade < 0.0)
            break;

        count++;
        sum += grade;
    }

    average = sum / count;
    printf("학생의 수는 %d명입니다.\n", count);
    printf("학생들의 성적의 평균은 %.2f입니다.\n", average);

    return 0;
}