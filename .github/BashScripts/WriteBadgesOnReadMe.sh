#!/bin/bash

if [[ $# -gt 0 ]]
then

  cp README.md READMECopy.txt

  truncate -s 0 README.md

  BadgeDirectory=$(find -type d -name "badges*")

  FirstLine=$(head -n 1 READMECopy.txt)

  RestOfTheFile=$(tail -n +2 READMECopy.txt)

  echo "$FirstLine" >> README.md

  for i in "$@"
  do
      echo "<img src=\"$BadgeDirectory/$i\"> " >> README.md
  done
      printf "\n" >> README.md
      echo "$RestOfTheFile" >> README.md
fi