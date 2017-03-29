#include <stdio.h>

int main(){

int intSize = sizeof(int);
int charSize = sizeof(char);
int floatSize = sizeof(float);
int shortSize = sizeof(float);
int longSize = sizeof(long);
int doubleSize = sizeof(double);
int processor = sizeof(int) << 3;

printf("Size of int = %d\nSize of char = %d\nSize of float = %d\nSize of short = %d\nSize of long = %d\nSize of double = %d\n\nProcessor is a %d-bit machine\n", intSize, charSize, floatSize, shortSize, longSize, doubleSize, processor); 
}
