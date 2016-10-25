#!/bin/bash
input_dir=$1
name=$2

jar cfvM ${CATALINA_HOME}/webapps/${name}.war -C ${input_dir} .
rm -r ${input_dir}/WEB-INF