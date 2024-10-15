with open("input.txt",'r') as fileR:
    with open("output.txt",'w') as fileW:
        lines = fileR.readlines()
        for i in range(0,len(lines),8):
            question = lines[i].strip()
            opt1 = lines[i+2].strip()
            opt2= lines[i+3].strip()
            opt3 = lines[i+4].strip()
            opt4 = lines[i+5].strip()
            answer = lines[i+6].strip()
            sql_query = f"INSERT INTO question (id,question_title,option1,option2,option3,option4,right_answer) VALUES ('{question}','{opt1}','{opt2}','{opt3}','{opt4}','{answer}')\n" 
            fileW.write(sql_query)