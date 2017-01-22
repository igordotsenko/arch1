#!/usr/bin/env bash

input_directory=$1 #working directory where all needed files will be copied and manipulated
remote_path=$2
war_name=$3
resources_input_path=$4 #where to find project resources
classes_input_path=$5 #where to find compiled project classes
lib_input_path=$6

temp_dir="${input_directory}/tmp"
output_path=${temp_dir}/${war_name}.war #where to put jar
rm -r "${input_directory}/" #remove all previous files
mkdir "${input_directory}"
mkdir "${input_directory}/WEB-INF/"
classes_output_path="${input_directory}/WEB-INF/classes" #where to put compiled classes
libs_output_path="${input_directory}/WEB-INF/lib" #where to put compiled libs

cp -R ${resources_input_path} ${input_directory}
cp -R ${classes_input_path} ${classes_output_path}
cp -R ${lib_input_path} ${libs_output_path}
mkdir ${temp_dir} 
jar cfvM ${output_path} -C ${input_directory} .
scp ${output_path} ${remote_path}
rm -r ${temp_dir}

