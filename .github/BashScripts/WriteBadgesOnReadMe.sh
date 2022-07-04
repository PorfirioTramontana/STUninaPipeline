#!/bin/bash

if [[ $# -gt 0 ]]
then

  cp README.md READMECopy.txt

  truncate -s 0 README.md

  FirstLine=$(head -n 1 READMECopy.txt)

  RestOfTheFile=$(tail -n +2 READMECopy.txt)

  echo "$FirstLine" >> README.md

  for i in "$@"
  do
      echo "<img src=\"badges_ver_v0.0.2-test1.5/$i\"> " >> README.md
  done
      printf "\n" >> README.md
      echo "$RestOfTheFile" >> README.md
fi