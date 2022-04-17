#!/bin/bash

set -xe

# Si connette alla VM attraverso SSH ed esegue i seguenti passi:
# - effettua il login all'interno del 'GitLab Container Registry'
# - rimuove il container precedentemente in esecuzione (se esiste)
# - effettua il pull dell'immagine più recente
# - esegue il container dell'app
# - esce
ssh -o PreferredAuthentications=publickey -p22 $AWS_VM_USER@$AWS_VM_URL \
"sudo docker login -u gitlab-ci-token -p $CI_JOB_TOKEN $CI_REGISTRY \
&& sudo docker rm --force uninatestpipeline || true \
&& sudo docker pull $CONTAINER_IMAGE:$CI_COMMIT_REF_NAME \
&& sudo docker run -d --name uninatestpipeline -p 8080:8080 $CONTAINER_IMAGE:$CI_COMMIT_REF_NAME \
&& exit"
