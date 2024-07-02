#!/bin/bash

# 定义目录和项目名称数组
PROJECTS=("java-jdbc" "java-springboot-misc" "java-filesystem-operator")

# 遍历数组
for PROJECT in "${PROJECTS[@]}"; do
    # 执行 YAK 静态安全分析和安全固定
    echo "Running YAK for project $PROJECT"
    yak ssa -t ./$PROJECT -p $PROJECT && yak sf -p $PROJECT $PROJECT
    if [ $? -ne 0 ]; then
        echo "Error processing $PROJECT"
    else
        echo "Completed processing $PROJECT"
    fi
done