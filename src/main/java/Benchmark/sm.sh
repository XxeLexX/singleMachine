#!/bin/bash


#Number of repeats
ROUNDS=""
#Input Path (will be converted to hdfs path)
IN=""
#Output Path (will be converted to hdfs path)
OUT=""
#CSV output path (directory must be existing)
CSV=""
#Used jar file
JAR_FILE=""


while read LINE
do
[[ "$LINE" =~ ^#.*$ ]] && continue
LINE="$(echo -e "${LINE}" | tr -d '[[:space:]]')"
IFS=':' read -ra LINE <<< "$LINE"
KEY=${LINE[0]}

case ${KEY} in
    jar)            JAR_FILE="${LINE[1]}";;
    rounds)         ROUNDS="${LINE[1]}";;
    input)          IN="${LINE[1]}";;
    output)         OUT="${LINE[1]}";;
    type)           TYPE="${LINE[1]}";;
    csv)            CSV="${LINE[1]}";
esac

done < sm.conf

IFS=','
read -ra IN <<< "$IN"
read -ra TYPE <<< "$TYPE"

unset IFS


  for I in "${IN[@]}"
  do
    for T in "${TYPE[@]}"
    do
      for ((R=1; R<=$ROUNDS; R++))
      do
        echo "Benchmark Temporal Vertex Degree of Single Machine"
        echo "=========================="
        echo "Input: ${I}"
        echo "======================="
        INPUT="${I}"
        OUTPUT="${OUT}"
        ARGS="-c ${CSV} -t VALID_TIME -d ${T}"
        echo "The following will be executed: java -jar ${JAR_FILE} -i ${INPUT} -o ${OUTPUT} ${ARGS}"

	java -jar ${JAR_FILE} -i ${INPUT} -o ${OUTPUT} ${ARGS}
        echo " "
      done
    done
  done