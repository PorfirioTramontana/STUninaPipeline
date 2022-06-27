#!/bin/bash

if [[ $# -eq 1 ]]
then
  Files=$(ls *"$1"*)
  res=0

  for File in $Files
  do
      res=$((res + $(grep -o -w "Tests run: [0-9]*" "$File" | cut -d' ' -f 3)))
  done

  echo $res
fi