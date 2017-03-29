/* 
 * CS:APP Data Lab 
 * 
 * <Please put your name and userid here>
 * Scott Waldron W00970369
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting an integer by more
     than the word size.

EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implent floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
     that you are allowed to use for your implementation of the function. 
     The max operator count is checked by dlc. Note that '=' is not 
     counted; you may use as many of these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* Copyright (C) 1991-2016 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses Unicode 8.0.0.  Version 8.0 of the Unicode Standard is
   synchronized with ISO/IEC 10646:2014, plus Amendment 1 (published
   2015-05-15).  */
/* We do not support C11 <threads.h>.  */
/* 
 *
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 * int test_dl10(int parameter1, int parameter2)
 * {
 *   int result = 0;
 *   int i;
 *   for (i = parameter2; i <= parameter1; i++)
 *   result |= 1 << i;
 *   return result;
 *  } 
 *
 *   Assume 0 <= parameter2 <= 31, and 0 <= parameter1 <= 31
 *   If parameter2 > parameter1, then return 0
 *   Legal ops: ~ & + << 
 *   Max ops: 12
 *   Rating: 3
 */
int dl10(int highbit, int lowbit) {
  // Creating a mask that starts at the low bit and ends at the highbit
  // If lowbit is higher than highbit, return 0

  int highMask;
  int lowMask; 
  int counterMask;
  highMask = ~0 << highbit;
  lowMask = ~(~0 << lowbit);
  counterMask = ~(1 << highbit);
  highMask = highMask & counterMask;
  return (~highMask & ~lowMask);
}
/* 
 *
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl11(int x, int y)
 *   {
 *       return ~(x|y);
 *   }
 *
 *
 *   Legal ops: ~ &
 *   Max ops: 5
 *   Rating: 1
 */
int dl11(int x, int y) {
  // Using deMorgan's Law's to make an equivelent expression

  return ~x & ~y;
}
/* 
 * int test_dl12(int x, int y)
 * {
 *    return x|y;
 * }
 *
 *
 *   Legal ops: ~ &
 *   Max ops: 8
 *   Rating: 1
 */
int dl12(int x, int y) {
  // Using deMorgan's Law's to make an equivelent expression

  return ~(~x & ~y);
}
/*
 *
 *  int test_dl13(int x) {
 *  int result = 0;
 *  int i;
 *  for (i = 0; i < 32; i++)
 *  result ^=  (x >> i) & 0x1;
 *  return result; }
 *
 *   Legal ops: ! & ^  >>
 *   Max ops: 20
 *   Rating: 4
 */
int dl13(int x) {
  // Returns 1 or 0 depending on the parity of x

  x = (x >> 16) ^ x;
  x = (x >> 8) ^ x;
  x = (x >> 4) ^ x;
  x = (x >> 2) ^ x;
  x = (x >> 1) ^ x;
  return x & 0x1;
}
/* 
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 * int test_dl14(int x, int y)
 * {
 *   return x^y; }
 *
 *
 *
 *   Legal ops: ~ &
 *   Max ops: 14
 *   Rating: 1
 */
int dl14(int x, int y) {
  // Using deMorgan's Laws to construct an exclusive-or using NAND gates

  return ~(~(x & ~y) & ~(y & ~x));
}
/* 
 *
 *
 *
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 * 
 * asumming a little endiamachine 
 * least significant byte stored first 
 *
 * int test_dl15(int x, int n, int m)
 * {
 *
 *   unsigned int nmask, mmask;
 *
 *   switch(n) {
 *   case 0:
 *     nmask = x & 0xFF;
 *     x &= 0xFFFFFF00;
 *     break;
 *   case 1:
 *     nmask = (x & 0xFF00) >> 8;
 *     x &= 0xFFFF00FF;
 *     break;
 *   case 2:
 *     nmask = (x & 0xFF0000) >> 16;
 *     x &= 0xFF00FFFF;      
 *     break;
 *   default:
 *     nmask = ((unsigned int)(x & 0xFF000000)) >> 24;
 *     x &= 0x00FFFFFF;
 *     break;
 *    }
 *
 *   switch(m) {
 *   case 0:
 *     mmask = x & 0xFF;
 *     x &= 0xFFFFFF00;
 *     break;
 *   case 1:
 *     mmask = (x & 0xFF00) >> 8;
 *     x &= 0xFFFF00FF;
 *     break;
 *   case 2:
 *     mmask = (x & 0xFF0000) >> 16;
 *     x &= 0xFF00FFFF;      
 *     break;
 *   default:
 *     mmask = ((unsigned int)(x & 0xFF000000)) >> 24;
 *     x &= 0x00FFFFFF;
 *     break;
 *   }
 *
 *   nmask <<= 8*m;
 *   mmask <<= 8*n;
 *
 *   return x | nmask | mmask;
 * }
 *
 *
 *  You may assume that 0 <= n <= 3, 0 <= m <= 3
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 2
 */
int dl15(int x, int n, int m) { 
  // Switching a byte of information from where m and n are located in x

  int nByte = 0xFF & (x >> (n << 3));
  int mByte = 0xFF & (x >> (m << 3));
  int mask = 0x0;
  int nMask = 0xFF << (n << 3);
  int mMask = 0xFF << (m << 3);

  mask = (nMask | mMask) ^ ~0x0;
  x = (x & mask) | (nByte << (m << 3)) | (mByte << (n << 3));
  return x;
}
/* 
 *
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 *
 * int test_dl16(int x, int y, int z)
 * {
 *   return x?y:z;
 *  }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 16
 *   Rating: 3
 */
int dl16(int x, int y, int z) {
  // If x is anything other than zero we return y, else return z

  int ones;
  int mask;
  mask = 0xFF;
  mask = mask | (mask << 8);
  mask = mask | (mask << 16);
  ones = !x + mask;
  return (ones & y) | (~ones & z);
}
/* 
 *
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 *  int test_dl17(int x)
 *  {
 *   return (x & 0x1) ? -1 : 0;
 *  }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 5
 *   Rating: 2
 */
int dl17(int x) {
  // If x has a 1 in the least significant bit, return -1, else 0

  return ~(x & 0x1) + 1;
}
/* 
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 * int test_dl18(int x, int n)
 * {
 *    int p2n = 1<<n;
 *    return x/p2n;
 *		
 * }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int dl18(int x, int n) {
  // We divide by a power of two, and need to add the bias if it's negative because it will round towards negative infinity. Pg. 106

  int isNegative;
  int negativeOne;
  int bias;
  
  isNegative = x >> 31;
  negativeOne = ~0x0;
  bias = ((1 << n) + negativeOne) & isNegative;
  x += bias;
  x = x >> n;
 
  return x;
}
/* 
 *
 *
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 *
 *
 *  int test_dl19(void) {
 *  int result = 0;
 *  int i;
 *  for (i = 0; i < 32; i+=2)
 *    result |= 1<<i;
 *  return result; }
 *
 *   Legal ops: |  << 
 *   Max ops: 6
 *   Rating: 1
 */
int dl19(void) {
  // Check to see if there is a pattern of the following...10101010...
  
  int mask = 0x55;
  mask |= mask << 8;
  mask |= mask << 16;
  return mask;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int dl1(int x) {return (x < 0) ? -x : x; }
 *
 *   Example: dl1(-1) = 1.
 *   You may assume -TMax <= x <= TMax
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 10
 *   Rating: 4
 */
int dl1(int x) {
  // Return the absolute value of x

  int mask = x >> 31;
  return (mask + x)^ mask; 
  
}
/*
 *
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *  
 *
 *
 *   int test_dl20(int x)
 *   {
 *      return (x*3)/4;
 *   }
 *
 *   Legal ops: ! ~ & + << >>
 *   Max ops: 10
 *   Rating: 3
 */
int dl20(int x) {
  // Just like dividing by a power of two like above but using a specific case. Pg 106 

  int isNegative;
  int bias;
  int negativeOne;

  negativeOne = ~0x0;
  x = x + (x << 1);
  isNegative = x >> 31; 
  bias = ((1 << 2) + negativeOne) & isNegative;
  
  x += bias;
  x = x >> 2;
  return x; 
}
/* 
 * Reproduce the functionality of the following C function
 * unsigned test_dl21(unsigned uf) {
 * float f = u2f(uf);
 * float hf = 0.5*f;
 * if (isnan(f))
 *   return uf;
 * else
 *   return f2u(hf);
 * }
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned dl21(unsigned uf) {
  // Cover your cases. If we return .5*f, then we need to decrement the exponent by one. If the exponent is only one, we need to get rid of the hidden one in the frac
  
  unsigned exponent = (uf & 0x7F800000) >> 23;
  unsigned sign = uf & 0x80000000;
  unsigned frac = uf & 0x7FFFFF;

  if (exponent == 0xFF)
    return uf;
  else if (exponent > 1){
    exponent--;
    return sign | (exponent << 23) | frac;
  }
  else{
    if (exponent == 1)
      frac = frac | 1 << 23;
    if ((frac & 0x3) == 3)
      frac = frac + 1;
    frac = frac >> 1;
    return sign | frac;
  }
}
/* 
 * reproduce the functionality of the following C function
 * unsigned test_dl22(int x) {
 * float f = (float) x;
 * return f2u(f);
 * }
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned dl22(int x) {
  // Manually cast an integer into a float. Using pg. 119 as reference to see where comminalities lie between the float and integer representation.
  // If x is negative, turn into positive. Also need to check for the bits lost to see if rounding needs to occur to keep correct value.
  
  int sign;
  int exponent = 31;
  unsigned int result;
  int frac;
  int rounder = 0;
  if (x == 0)
    return 0;
  sign = x & 0x80000000;
  
  if (sign)
    x = -x;
  while((x & (0x80000000)) == 0){
    x <<= 1;
    exponent--;
  }
  frac = (x >> 8) & 0x7FFFFF;
  exponent = 127 + exponent;
  if (((x & 0x100) && (x & 0x80)) || ((x & 0x7F) && (x & 0x80)))
    rounder = 1;
  result = sign | (exponent << 23)  | frac;
  return result += rounder;
}
/* 
 * reproduce the functionality of the following C function
 * unsigned test_dl23(unsigned uf) {
 *  float f = u2f(uf);
 *  float tf = 2*f;
 *  if (isnan(f))
 *    return uf;
 *  else
 *    return f2u(tf);
 * }
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned dl23(unsigned uf) {
  // Like dl21, we increment our exponent field by one and return. But with special cases, if exponent is 0, we just shift frac left by one to multiply by 2.

  unsigned sign = uf & 0x80000000;
  unsigned exponent = (uf >> 23) & 0xFF;
  unsigned frac = uf & 0x7FFFFF;
  if (uf == 0)
    return 0;
  if (exponent == 0xFF)
    return uf;
  else if (exponent == 0x0){
    return sign | (frac << 1);
  }
  else{
  exponent++;
  return sign | (exponent << 23) | frac;
  }
}
/*dl24 - return the minimum number of bits required to represent x in
 *             two's complement
 *  Examples:dl24(12) = 5
 *           dl24(298) = 10
 *           dl24(-5) = 4
 *           dl24(0)  = 1
 *           dl24(-1) = 1
 *           dl24(0x80000000) = 32
 * Here is a C function that accomplishes this. Reproduce the functionality
 * of this function using only the legal operations described below. 
 * int test_dl24(int x) {
 *   unsigned int a, cnt;
 *   
 *   x = x<0 ? -x-1 : x;
 *   a = (unsigned int)x;
 *   for (cnt=0; a; a>>=1, cnt++)
 *       ;
 *   return (int)(cnt + 1);
 * }
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 90
 *  Rating: 4
 */
int dl24(int x) {
  // Uses a log base 2 type style to find the required number of bits to represent a number. Uses a divide and conquer method to check if a bit is turned on in portions of the int.
  // Sum up the total number needed into totalBits and returns the sum of them plus the 32 spot that doesn't get checked and an additional one for the sign extension.

  int signMask = x >> 31;
  int totalBits = 0;
  int top16;
  int top8;
  int top4;
  int top2; 
  int top1;
  x = (signMask & ~x) | (~signMask & x);

  top16 = (!!(x >> 16)) << 4;
  x = x >> top16;
  top8 = (!!(x >> 8)) << 3;
  x = x >> top8;
  top4 = (!!(x >> 4)) << 2;
  x = x >> top4;
  top2 = (!!(x >> 2)) << 1;
  x = x >> top2;
  top1 = (!!(x >> 1));
  x = x >> top1;
  totalBits = top16 + top8 + top4 + top2 + top1 + x + 1;
 return totalBits; 
}
/* 
 *
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl2(int x, int y)
 *   { long long lsum = (long long) x + y;
 *    return lsum == (int) lsum;}
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3
 */
int dl2(int x, int y) {
  // Checks for overflow of addition between x and y. Returns 0 for overFlow and 1 otherwise.  
  int sum = x + y;
  int xCard = x >> 31;
  int yCard = y >> 31;
  int sumCard = sum >> 31;
  return !(!(xCard ^ yCard)) | (!(xCard ^ sumCard) & !(yCard ^ sumCard)); 
}

/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl3(int x) {
 *    int i;
 *    for (i = 0; i < 32; i+=2)
 *       if ((x & (1<<i)) == 0)
 *	  return 0;
 *    return 1; }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int dl3(int x) {
  // Checks to logically see if x has the bit pattern ...01010101... returns 1 if true, 0 if false
  
  int bitMask = 0x55;
  bitMask |= bitMask << 8;
  bitMask |= bitMask << 16;
  return !(bitMask & ~x); 
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl4(int x) {
 *   int i;
 *   for (i = 1; i < 32; i+=2)
 *     if ((x & (1<<i)) == 0)
 *	  return 0;
 *    return 1; }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int dl4(int x) {
  // Checks to logically see if x has the bit pattern ...10101010... returns 1 if true, 0 if false
  
  int bitMask = 0xAA;
  bitMask |= bitMask << 8;
  bitMask |= bitMask << 16;
  return !(bitMask & ~x);
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl5(int x) {
 *   int i;
 *   for (i = 0; i < 32; i+=2)
 *     if (x & (1<<i))
 *	  return 1;
 *   return 0; }
 *
 *   Legal ops: ! ~ &  | + << >>
 *   Max ops: 10
 *   Rating: 2
 */
int dl5(int x) {
  // Checks to logically see if x has the bit pattern ...01010101... returns 0 if true, 1 if false
  
  int bitMask = 0x55;
  bitMask |= bitMask << 8;
  bitMask |= bitMask << 16;
  return !(!(bitMask & x));
}
/* 
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *  int test_dl6(int x) {
 *   int i;
 *   for (i = 1; i < 32; i+=2)
 *       if (x & (1<<i))
 *	     return 1;
 *   return 0; }
 *
 *   Legal ops: ! &  | << 
 *   Max ops: 10
 *   Rating: 2
 */
int dl6(int x) {
  // Checks to logically see if x has the bit pattern ...10101010... returns 0 if true, 1 if false
  
  int bitMask = 0xAA;
  bitMask |= bitMask << 8;
  bitMask |= bitMask << 16;
  return !(!(bitMask & x));
}
/* 
* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl7(int x)
 *   {
 *      return !x;
 *   }
 *
 *   Legal ops: ~ & ^ | + >>
 *   Max ops: 10
 *   Rating: 4 
 */
int dl7(int x) {
  // Return the !x by adding oring x with its negative self pushed right by 31. It will be all ones or all zeroes. We then need to return a one or zero so we 'and' 1 with the 'not' of the result
  
  int check;
  int negativeX;
  negativeX = ~x + 1;
  check = (negativeX | x) >> 31;
 
 return 0x1 & ~check;
 
}
/* 
 *
 *  
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl8(int x, int y)
 *   {
 *      return x&y;
 *   }
 *
 *   Legal ops: ~ |
 *   Max ops: 6
 *   Rating: 1
 */
int dl8(int x, int y) {
  // Using deMorgan's Law's to make an equivelent of x&y
   
  return ~(~x | ~y);
}
/*
 *   
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl9(int x) {
 *   int result = 0;
 *   int i;
 *   for (i = 0; i < 32; i++)
 *   result +=  (x >> i) & 0x1;
 *       return result;
 *   }
 *
 *   Legal ops: & | + << >>
 *   Max ops: 38
 *   Rating: 4
 */
int dl9(int x) {
  // Using a divide and conquer method to add up the total number of bits that are turned on. 
  
  int total;
  int mask1;
  int mask2;
  int mask3;
  int mask4;
  int mask5;

  total = 0;
  mask1 = 0x55;
  mask1 = mask1 | (mask1 << 8);
  mask1 = mask1 | (mask1 << 16);
  mask2 = 0x33;
  mask2 = mask2 | (mask2 << 8);
  mask2 = mask2 | (mask2 <<16);
  mask3 = 0x0F;
  mask3 = mask3 | (mask3 << 8);
  mask3 = mask3 | (mask3 << 16);
  mask4 = 0xFF;
  mask4 = mask4 | (mask4 <<16);
  mask5 = 0xFF;
  mask5 = mask5 | (mask5 << 8);
  
  total = (x & mask1) + ((x >> 1) & mask1);
  total = (total & mask2) + ((total >> 2) & mask2);
  total = (total & mask3) + ((total >> 4) & mask3);
  total = (total & mask4) + ((total >> 8) & mask4);
  total = (total & mask5) + ((total >> 16) & mask5);
  return total;
}
