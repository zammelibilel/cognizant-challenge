DELETE FROM task;

INSERT INTO task (task_name,description,input,output) VALUES (
  "Count the words",
  "You are asked create a method to count the number of words in the given input sentence. \n the script should return print the number of words",
  "count the number of words in the given input sentence",
  "10"
); 

INSERT INTO task (task_name,description,input,output) VALUES (
  "Palindrome",
  "If the string is similar from the starting as well as the ending, then we can say that the string is a palindrome. \nFor example. madam, radar, etc. \n the script should return print yes if the input string is palindrome",
  "madam",
  "yes"
);