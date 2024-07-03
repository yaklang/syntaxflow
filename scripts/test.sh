#!/bin/bash

PROJECTS=(
  # "java-security-config"
  "java-filesystem-operator"
  "java-jdbc"
  "java-springboot-misc"
)

for PROJECT in "${PROJECTS[@]}"; do
    echo "Running YAK SSA for project $PROJECT"
    yak ssa -t ./$PROJECT -p $PROJECT
    if [ $? -ne 0 ]; then
        echo "Error running YAK SSA for $PROJECT"
        exit 1  # 退出状态1表示错误
    fi

    echo "Running YAK SF for project $PROJECT"
    yak sf -p $PROJECT $PROJECT
    if [ $? -ne 0 ]; then
        echo "Error running YAK SF for $PROJECT"
        exit 1  # 退出状态1表示错误
    fi

    echo "Completed processing $PROJECT"
done