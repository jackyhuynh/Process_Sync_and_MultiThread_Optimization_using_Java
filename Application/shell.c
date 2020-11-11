/**
   @author Truc Huynh
   @name shell.c
   @date 9/22/2020
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <readline/readline.h>
#include <unistd.h>
#include <sys/wait.h>

//Function
char **parse_user_input(char*);
void lsh_Loop(void);
void allocation_check(char **command);
int cd(char *);

/**
   @brief main function call the lshLoop.
   @return 0.
 */
int main()
{
    // Run command loop.
    lsh_Loop();
    return 0;
}

/**
   @brief 	get user input
			validate if user input is validate
			create child process
   @return exit if no input.
 */
void lsh_Loop(void)
{
    char **command;
    char *input;
    pid_t child_pid;
    int stat_loc;

    while (1) {
        input = readline("TrucShell> ");	//USing readline to read in the user input             
        command = parse_user_input(input);	//Get input by calling get user input

        
        if (!command[0]){			/*if there is no command*/ 
            free(input);
            free(command);
            continue;				/* Skip the fork */
        }
		
		//if user input the cd command
		if (strcmp(command[0], "cd") == 0) {
			if (cd(command[1]) < 0) {
				perror(command[1]);
			}
			continue;				/* Skip the fork */
		}
				
        child_pid = fork();			/* Create a child process */
        if (child_pid<0){			/* Exit if the child is failed */
            perror("Fork failed");	
            exit(EXIT_FAILURE);
        }
				
		if(child_pid == 0){
        
            if (execvp(command[0], command) < 0){ // Never return if the call is scucessful
                perror(command[0]);
                exit(EXIT_FAILURE);
            }
        } else {
            waitpid(child_pid, &stat_loc, WUNTRACED);
        }
        free(input);
        free(command);
    }
}

#define LSH_RL_BUFSIZE 1024
/**
   @brief Read a line of input from stdin.
   @return The command from stdin.
 */
char **parse_user_input(char *input) {
    // Define variable
	char *separator = " ";
    char *stringBuffer;
    int index = 0;
    int bufsize= LSH_RL_BUFSIZE;
    char **command = malloc(sizeof(char)*bufsize);

    allocation_check(command);    /*check if there is no memeory allocation*/
    stringBuffer = strtok(input, separator); /* Assign the user input to char array*/
	
    while (stringBuffer != NULL) {
        command[index] = stringBuffer;
        index++;
				
        stringBuffer = strtok(NULL, separator); /*Empty the string buffer*/
		// Reallocate if exceed buffer size	
		if (index >= bufsize) {
			bufsize += LSH_RL_BUFSIZE;
			command = realloc(command, bufsize);
			allocation_check(command);    /*check if there is no memeory allocation*/
		}
    }
    command[index]=NULL;
    return command;
}

/**
   @brief if there is no input from stdin.
   @return exit if no input.
 */
void allocation_check(char **command)
{
	if (!command) {
		perror("allocation error!");
		exit(EXIT_FAILURE);
	}
}

int cd(char *path) {
    return chdir(path);
}
