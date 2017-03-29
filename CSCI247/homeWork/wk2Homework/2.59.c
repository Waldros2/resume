/* Write a C expressions that will yeild a word consisting of the least significant byte of x and the rem   aining bytes of y for operands x = 0x89ABCDEF and y = 0x76543210. This would give you 0x765432EF
*/

#include <stdio.h>

int main(){

int x = 0x89ABCDEF;
int y = 0x76543210;

int maskX = x & 0x000000FF;
int maskY = y & 0xFFFFFF00;
int result = maskX | maskY;
printf("Int x = %x\nInt y = %x\nY with X's least significant byte = %x\n", x, y, result);
}
