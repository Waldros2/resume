#include <stdio.h>
#include <string.h>
int main(){

  char text1[100], text2[100], text3[100];
  char *ta, *tb;
  int i;
  char message[] = "Hello, I am a string; what are you?";

  printf("Original message: %s\n", message);
  i = 0;
  while ( (text1[i] = message[i]) != '\0' )
  i++;
  printf("Text1: %s\n", text1);
  ta = message;
  tb= text2;
  while ( ( *tb++ = *ta++) != '\0' )
  ;
  printf("Text2: %s\n", text2);



  char line[100], *subText;
  strcpy(line,"hello I am a string;");
  printf("Line : %s\n", line);
  strcat(line," what are you?");
  printf("Line: %s\n", line);
  printf("Length of line: %d\n", (int)strlen(line));

  if( (subText = strchr ( line, 'W' ) ) != NULL )
    printf("String starting with \"W\" ->%s\n", subText);

  if( (subText = strchr (line, 'w' ) ) != NULL )
    printf("String starting with \"w\" ->%s\n", subText);

  if( (subText = strchr(subText, 'u' ) ) != NULL )
    printf("String starting with \"u\" ->%s\n", subText);
}
