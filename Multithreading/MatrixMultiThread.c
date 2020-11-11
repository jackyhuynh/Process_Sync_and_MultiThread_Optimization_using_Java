#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX 400
#define n= 400;

#define upper 100
#define lower 0

struct v
{
    size_t i;
    size_t j;
};

int A[MAX][MAX];
int B[MAX][MAX];
int C[MAX][MAX];

void matrixCreator(int matrix[][MAX])
{
    int i, j;
    for (i = 0; i < MAX; i++) {
        for (j = 0; j < MAX; j++)
        {
            int num = (rand() % (upper - lower + 1)) + lower;
            matrix[i][j] = num;
        }
    }
}

void prettyPrint(int matrix2[][MAX])
{
    int rows = MAX, cols = MAX;
    // Displaying matB
    printf("Display:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++)
            printf("%d ", matrix2[i][j]);
        printf("\n");
    }
    printf("\n");
}

//multiplication() function
static void* multiplication(void* arg) {
    struct v* data = (struct v*)arg;

    for (int l = 0; l < MAX; l++)
    {
        size_t i = (data[l]).i;
        size_t j = (data[l]).j;
        int sum = 0;

        for (int d = 0; d < MAX; d++)
        {
            sum = A[i][d] * B[d][j];
        }

        C[i][j] = sum;
        sum = 0;
    }
    return 0;
}

//Main function
int main(int argc, char* argv[])
{
    clock_t begin = clock();
    int Not_join = 0;

    srand(time(0));
    pthread_t threads[n];   //hold the thread array
    size_t i, k;            //hold the index value
    
    
                            //Create matrix with random number
    matrixCreator(A);
    matrixCreator(B);
    /*
        prettyPrint for debugging and test only
    */
    // prettyPrint(A);
    // prettyPrint(B);

    struct v** data;
    data = (struct v**)malloc(n * sizeof(struct v*));

    for (i = 0; i < n; i++)
    {
        //Allocate element data array type struct v
        data[i] = (struct v*)malloc(n * sizeof(struct v));

        for (k = 0; k < n; k++)
        {
            data[i][k].i = i;
            data[i][k].j = k;
        }
        //Create Thread that pass the multiplication() function and use the data struc pointer as argument
        pthread_create(&threads[i], NULL, multiplication, data[i]);
    }

    //Join the thread and release after done the job
    for (i = 0; i < MAX; i++)
    {
        if (pthread_join(threads[i], NULL) == 0)
        {
            printf("\nThread join line: %d\n", (int)i);
        }
        else {
            printf("\nThread Not Join:%d \n", (int)i);
        }
        free(data[i]);//Free each element 
    }

    free(data); //Free alocated data vector

    /*
        prettyPrint for debugging and test only
    */
    prettyPrint(C);

    /* time-consuming job */
    clock_t end = clock();
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("\nTotal time consumming: %ft \n", time_spent);
    return 0;
}