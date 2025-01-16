#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

void swap(int *d1, int *d2)
{
	int tmp;
	tmp = *d1;
	*d1 = *d2;
	*d2 = tmp;
}

int main(void)
{
	int heap[100001];
	int n, cur, parent;
	int i;

	scanf("%d", &n);
	if (n == 1) {
		printf("1\n");
		return 0;
	}
	if (n == 2)
	{
		printf("1 2\n");
		return 0;
	}
	if (n > 2)
	{
		heap[1] = 2;
		heap[2] = 1;
		for (i = 3; i <= n; i++)
		{
			heap[i] = i;
			swap(&heap[i], &heap[i - 1]);

			cur = i - 1;
			parent = cur / 2;
			while (cur > 1 && heap[cur] > heap[parent])
			{
				swap(&heap[cur], &heap[parent]);
				cur = parent;
				parent /= 2;
			}
		}

	}
	for (i = 1; i <= n; i++)
		printf("%d ", heap[i]);
	printf("\n");

	return 0;
}