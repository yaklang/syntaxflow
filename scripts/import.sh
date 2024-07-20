#!/bin/bash

# 读取项目列表文件，每行一个项目名
PROJECTS_FILE="verified.txt"

# 检查项目列表文件是否存在
if [ ! -f "$PROJECTS_FILE" ]; then
    echo "Error: Projects file '$PROJECTS_FILE' not found."
    exit 1
fi

# 读取项目名并执行相应的操作
while IFS= read -r PROJECT; do
    echo "Running Save SyntaxFlow for project $PROJECT"
    yak ssf -f "./$PROJECT/sample" -r "$PROJECT"
    if [ $? -ne 0 ]; then
        echo "Error running YAK SSA for $PROJECT"
        exit 1
    fi

    echo "Completed processing $PROJECT"
done < "$PROJECTS_FILE"
