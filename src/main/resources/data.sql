DELETE FROM TASK;
DELETE FROM TEST_CASE;

INSERT INTO TASK (task_name,description) VALUES (
  "Count the words",
  "You are asked create a method to count the number of words in the given input sentence. \n the script should return print the number of words"
); 

INSERT INTO TASK (task_name,description) VALUES (
  "Palindrome",
  "If the string is similar from the starting as well as the ending, then we can say that the string is a palindrome. \nFor example. madam, radar, etc. \n the script should return print 'yes' if the input string is palindrome otherwise print 'no'"
);

INSERT INTO TEST_CASE (task_id,input,output) VALUES (
  1,
  "count the number of words in the given input sentence",
  "10"
);

INSERT INTO TEST_CASE (task_id,input,output) VALUES (
  1,
  "count the number of words",
  "5"
);


INSERT INTO TEST_CASE (task_id,input,output) VALUES (
  2,
  "madam",
  "yes"
);

INSERT INTO TEST_CASE (task_id,input,output) VALUES (
  2,
  "test",
  "no"
);