#!/bin/bash

if [[ $# -gt 0 ]]
then

  badges=$(ls "$1")

  cp README.md READMECopy.txt

  truncate -s 0 README.md

  FirstLine=$(head -n 1 READMECopy.txt)

  touch tmp.txt

  sed '/<img src=*>/d' READMECopy.txt > tmp.txt

  RestOfTheFile=$(tail -n +2 tmp.txt)

  echo "$FirstLine" >> README.md

  for i in $badges
  do
      echo -n "<img src=\"$1/$i\"> " >> README.md
  done
      printf "\n" >> README.md
      echo "$RestOfTheFile" >> README.md
fi