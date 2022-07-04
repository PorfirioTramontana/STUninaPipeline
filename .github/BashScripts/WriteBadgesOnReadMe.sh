#!/bin/bash

if [[ $# -gt 1 ]]
then

  cp README.md READMECopy.txt

  truncate -s 0 README.md

  FirstLine=$(head -n 1 READMECopy.txt)

  RestOfTheFile=$(tail -n +2 READMECopy.txt)

  echo "$FirstLine" >> README.md

  sed -i '/<img src=*>/d' READMECopy.txt

  for i in "$@"
  do
      if [[ "$i" = "$1" ]]
      then
        continue
      fi
      echo -n "<img src=\"$1/$i\"> " >> README.md
  done
      printf "\n" >> README.md
      echo "$RestOfTheFile" >> README.md
fi