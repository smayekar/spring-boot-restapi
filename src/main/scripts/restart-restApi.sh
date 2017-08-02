#!/bin/bash
DIR=`dirname $0`
${DIR}/stop-restApi.sh
sleep 5
${DIR}/start-restApi.sh