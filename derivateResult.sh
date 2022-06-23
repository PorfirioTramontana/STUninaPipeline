#!/bin/bash

if [[ $# -eq 1 ]]
then

  if [[ $1 -gt 0 ]]
  then
      echo "Failed"
      exit 0
  fi

  echo "Passed"
  exit 0

fi

if [[ $# -eq 2 ]]
then

  if [[ $1 -gt 0 ]] || [[ $2 -gt 0 ]]
  then
      echo "Failed"
      exit 0
  fi

  echo "Passed"
  exit 0

fi


