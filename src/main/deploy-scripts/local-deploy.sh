#!/bin/bash
input_dir=$1
name=$2

rm -r ${CATALINA_HOME}/webapps/${name}
rm ${CATALINA_HOME}/webapps/${name}.war
jar cfvM ${CATALINA_HOME}/webapps/${name}.war -C ${input_dir} .