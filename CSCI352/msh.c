/* CS 352 -- Mini Shell!  
 *
 *   Sept 21, 2000,  Phil Nelson
 *   Modified April 8, 2001 
 *   Modified January 6, 2003
 *   
 *   Project of: Scott Waldron
 *
 */

#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>


/* Constants */ 

#define LINELEN 1024

/* Prototypes */

void processline (char *line);
char ** arg_parse (char *line);

/* Shell main */

int main (void){
    
    char   buffer [LINELEN];
    int    len;

    while (1) {

        /* prompt and get line */
	fprintf (stderr, "%% ");
	if (fgets (buffer, LINELEN, stdin) != buffer)
	  break;

        /* Get rid of \n at end of buffer. */
	len = strlen(buffer);
	if (buffer[len-1] == '\n')
	    buffer[len-1] = 0;

	/* Run it ... */
	processline (buffer);

    }

    if (!feof(stdin))
        perror ("read");

    return 0;		/* Also known as exit (0); */
}

char ** arg_parse(char *line){
  
  int i = 0;
  int j = 0;
  int k = 0;
  int argc = 0;
  
  while (line[i] != '\0'){
    if (line[i] == ' '){
      i++;
    }
    else{
      argc++;
      while(line[i] != ' '){
	if (line[i] == '\0')
	  break;
	else
	  i++;
     }
    }
  }
  char **argv = malloc((argc+1) * sizeof(char*));
  
  while (line[j] != '\0'){
    if (line[j] == ' ')
      j++;
    else{
      argv[k] = &line[j];
      k++;
      while(line[j] != ' '){
	if (line[j] == '\0'){
	  argv[k+1] = '\0';
	  break; 
	}
	else{
	  j++;
	}
      }
      line[j] = '\0';	
    }
  }
  printf("%d\n", argc);

  return argv;
  
}
   
void processline (char *line){
    
    pid_t  cpid;
    int    status;
    
    /* Parse the line into seperate fields */
    char **argv = arg_parse(line);

    /* Start a new process to do the job. */
    cpid = fork();
    if (cpid < 0) {
      perror ("fork");
      return;
    }
    
    /* Check for who we are! */
    if (cpid == 0) {
      /* We are the child! */
      execvp (line, argv);
      perror ("exec");
      exit (127);
    }
    
    /* Have the parent wait for child to complete */
    if (wait (&status) < 0)
      perror ("wait");
}
